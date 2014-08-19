package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassFile;
import com.github.zxh.classpy.classfile.ClassParser;
import java.io.File;
import java.nio.file.Files;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Main class.
 * 
 * @author zxh
 */
public class ClasspyApp extends Application {

    private FileChooser fileChooser;
    private Stage stage;
    private BorderPane root;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        root = new BorderPane();
        root.setTop(createMenuBar());
        
        stage.setScene(new Scene(root, 800, 600));
        stage.setTitle("Classpy 8");
        stage.show();
    }
    
    private MenuBar createMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem openMenuItem = new MenuItem("Open...");
        openMenuItem.setOnAction(e -> {
            showFileChooser();
        });
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(fileMenu);
        fileMenu.getItems().add(openMenuItem);
        
        return menuBar;
    }
    
    private void showFileChooser() {
        if (fileChooser == null) {
            initFileChooser();
        }

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            // todo
            openClass(file, root);
        }
    }
    
    private void initFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open .class or .jar File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("CLASS", "*.class"),
            new FileChooser.ExtensionFilter("JAR", "*.jar")
        );
    }
    
    private void openClass(File file, BorderPane root) {
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
            SplitPane sp = UiBuilder.buildMainPane(cf);
            root.setCenter(sp);
        });

        task.setOnFailed(e -> {
            System.out.println(e.getSource().getException());
        });

        new Thread(task).start();
    }
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
