package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.gui.tree.UiBuilder;
import com.github.zxh.classpy.classfile.ClassFile;
import com.github.zxh.classpy.classfile.ClassParser;
import com.github.zxh.classpy.gui.hex.ClassHex;
import com.github.zxh.classpy.gui.hex.HexPane;
import java.io.File;
import java.nio.file.Files;
import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author zxh
 */
public class ClasspyApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //System.out.println(Font.getFamilies());
        
        
        stage.setTitle("Classpy");
        
        BorderPane root = new BorderPane();
        
        
        SplitPane mainPane = new SplitPane();
        mainPane.getItems().add(new Button("aaaaaa"));
        mainPane.getItems().add(new Button("aaaaaa"));
        mainPane.getItems().add(new Button("aaaaaa"));
        
        
        root.setTop(createMenuBar(stage, root));
//        root.setCenter(mainPane);
        
        Scene scene = new Scene(root, 500, 600);
        stage.setScene(scene);
        stage.show();
    }
    
    private MenuBar createMenuBar(Stage stage, BorderPane root) {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        menuBar.getMenus().add(fileMenu);
        MenuItem openMenuItem = new MenuItem("Open...");
        fileMenu.getItems().add(openMenuItem);
        
        openMenuItem.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open .class or .jar File");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CLASS", "*.class"),
                new FileChooser.ExtensionFilter("JAR", "*.jar")
            );
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                // todo
                Task<ClassFile> task = new Task<ClassFile>() {

                    @Override
                    protected ClassFile call() throws Exception {
                        byte[] x = Files.readAllBytes(file.toPath());
                        ClassFile cf = ClassParser.parse(x);
                        return cf;
                    }
                    
                };
                
                task.setOnSucceeded(e -> {
                    ClassFile cf = (ClassFile) e.getSource().getValue();
                    System.out.println(cf);
                    TreeView<ClassComponent> tree = UiBuilder.build(cf);
                    tree.selectionModelProperty().addListener((x, old, n) -> {
                        System.out.println(x);
                        System.out.println(old);
                        System.out.println(n);
                    });
                    tree.focusModelProperty().addListener((x, old, n) -> {
                        System.out.println(x);
                        System.out.println(old);
                        System.out.println(n);
                    });
                    tree.getSelectionModel().getSelectedItems().addListener(
                            (ListChangeListener.Change<? extends TreeItem<ClassComponent>> c) -> {
                                //throw new UnsupportedOperationException("Not supported yet.");
                                //System.out.println(c);
                                //System.out.println(c.getClass());
                                if (c.next()) {
//                                    if (c.wasPermutated()) {
//                                        System.out.println("wasPermutated::"+c);
//                                    } else {
//                                        System.out.println("!!!!!!!!!!!!!::"+c);
//                                        Object x = c.getList().get(c.getFrom());
//                                        System.out.println("Vvvvvvv:"+x);
//                                    } 
                                    if (c.wasPermutated()) {
                                        System.out.println("wasPermutated::"+c);
                                    }
                                    if (c.wasReplaced()) {
                                        System.out.println("wasReplaced::"+c);
                                    }
                                    if (c.wasUpdated()) {
                                        System.out.println("wasUpdated::"+c);
                                    }
                                    if (c.wasAdded()) {
                                        System.out.println("wasAdded::"+c);
                                    }
                                    
                                }
//                                
                                //Object x = c.getList().get(c.getTo());
                                //System.out.println(x);
                                //System.out.println(x.getClass());
                    });
//                    tree.getSelectionModel().getSelectedItems().add
                    
                    root.setLeft(tree);
                    
                    ClassHex hex = new ClassHex(cf);
                    HexPane hexPane = new HexPane(hex);
                    root.setRight(hexPane);
                });
                
                task.setOnFailed(e -> {
                    System.out.println(e.getSource().getException());
                });
                
                new Thread(task).start();
            }
        });
        
        return menuBar;
    }
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
