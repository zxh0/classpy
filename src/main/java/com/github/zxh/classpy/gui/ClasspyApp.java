package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileHex;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Main class.
 * 
 * @author zxh
 */
public class ClasspyApp extends Application {

    private static final String TITLE = "Classpy 8";
    
    private FileChooser fileChooser;
    private Stage stage;
    private BorderPane root;
    private File lastOpenFile;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        
        root = new BorderPane();
        root.setTop(createMenuBar());
        root.setBottom(new Label("status"));
        
        stage.setScene(new Scene(root, 900, 600));
        stage.setTitle(TITLE);
        stage.show();
    }
    
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        menuBar.getMenus().add(createFileMenu());
        menuBar.getMenus().add(createHelpMenu());
        
        return menuBar;
    }
    
    private Menu createFileMenu() {
        MenuItem openMenuItem = new MenuItem("Open...");
        openMenuItem.setOnAction(e -> {
            showFileChooser();
        });
        
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(openMenuItem);
        
        return fileMenu;
    }
    
    private Menu createHelpMenu() {
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(e -> {
            AboutDialog.showDialog();
        });
        
        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().add(aboutMenuItem);
        
        return helpMenu;
    }
    
    private void showFileChooser() {
        if (fileChooser == null) {
            initFileChooser();
        } else if (lastOpenFile != null) {
            fileChooser.setInitialDirectory(lastOpenFile.getParentFile());
        }

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            // todo
            openFile(file, () -> {
                // todo
                lastOpenFile = file;
            });
        }
    }
    
    private void initFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open .class file");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("CLASS", "*.class"),
            new FileChooser.ExtensionFilter("DEX", "*.dex")
            //new FileChooser.ExtensionFilter("JAR", "*.jar")
        );
    }
    
    private void openFile(File file, Runnable succeededCallback) {
        ProgressBar pb = new ProgressBar();
        root.setCenter(pb);
        
        OpenFileTask task = new OpenFileTask(file);
        
        task.setOnSucceeded((FileComponent fc, FileHex hex) -> {
            SplitPane sp = UiBuilder.buildSplitPane(fc, hex);
            root.setCenter(sp);
            stage.setTitle(TITLE + " - " + file.getAbsolutePath());
            
            succeededCallback.run();
        });
        
        task.setOnFailed((Throwable err) -> {
            Text errMsg = new Text(err.toString());
            root.setCenter(errMsg);
        });

        task.startInNewThread();
    }
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
