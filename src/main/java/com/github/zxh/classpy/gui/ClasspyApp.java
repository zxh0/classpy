package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassFile;
import java.io.File;
import java.nio.file.Files;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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
        stage.setTitle("Classpy");
        
        BorderPane root = new BorderPane();
        
        
        root.setTop(createMenuBar(stage));
        
        
        Scene scene = new Scene(root, 300, 250);
//        Group root = new Group();
//        Button btn = new Button();
//        btn.setLayoutX(100);
//        btn.setLayoutY(80);
//        btn.setText("Hello World");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World");
//            }
//        });
//        root.getChildren().add(btn);
        stage.setScene(scene);
        stage.show();
    }
    
    private MenuBar createMenuBar(Stage stage) {
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
                        ClassFile cf = ClassFile.parse(x);
                        return cf;
                    }
                    
                };
                
                task.setOnSucceeded(e -> {
                    System.out.println(e.getSource().getValue());
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
