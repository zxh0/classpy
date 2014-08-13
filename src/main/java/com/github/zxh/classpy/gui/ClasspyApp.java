package com.github.zxh.classpy.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
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
        
        
        root.setTop(createMenuBar());
        
        
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
    
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        menuBar.getMenus().add(fileMenu);
        MenuItem openMenuItem = new MenuItem("Open...");
        fileMenu.getItems().add(openMenuItem);
        
        return menuBar;
    }
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
