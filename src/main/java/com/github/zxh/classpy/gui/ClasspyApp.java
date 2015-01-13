package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileHex;
import java.io.File;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
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

    private static final String TITLE = "Classpy";
    
    private FileChooser fileChooser;
    private Stage stage;
    private BorderPane root;
    private MyMenuBar menuBar;
    
    private File lastOpenFile;
    private final LinkedList<File> recentFiles = new LinkedList<>();
    
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        root = new BorderPane();
        root.setTop(createMenuBar());
        
        stage.setScene(new Scene(root, 960, 540));
        stage.setTitle(TITLE);
        stage.show();
    }
    
    private MenuBar createMenuBar() {
        menuBar = new MyMenuBar();
        
        menuBar.getOpenMenuItem().setOnAction(e -> showFileChooser());
        menuBar.getNewWinMenuItem().setOnAction(e -> openNewWindow());
        menuBar.getAboutMenuItem().setOnAction(e -> AboutDialog.showDialog());
        
        return menuBar;
    }
    
    private void showFileChooser() {
        if (fileChooser == null) {
            initFileChooser();
        } else {
            if (lastOpenFile != null && lastOpenFile.getParentFile().isDirectory()) {
                fileChooser.setInitialDirectory(lastOpenFile.getParentFile());
            }
        }
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            if (file.getName().endsWith(".jar") || file.getName().endsWith(".JAR")) {
                try {
                    openJar(file);
                } catch (Exception e) {
                    // todo
                    e.printStackTrace(System.err);
                }
            } else {
                openFile(file);
            }
        }
    }
    
    private void initFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JAR", "*.jar"),
            new FileChooser.ExtensionFilter("CLASS", "*.class"),
            new FileChooser.ExtensionFilter("DEX", "*.dex")
        );
    }
    
    private void openJar(File jar) throws Exception {
        JarDialog.showDialog(jar);
    }
    
    private void openFile(File file) {
        root.setCenter(new ProgressBar());
        
        OpenFileTask task = new OpenFileTask(file.toPath());
        
        task.setOnSucceeded((FileComponent fc, FileHex hex) -> {
            MainPane mainPane = new MainPane(fc, hex);
            root.setCenter(mainPane);
            stage.setTitle(TITLE + " - " + file.getAbsolutePath());
            
            // todo
            lastOpenFile = file;
            addRecentFile(file);
        });
        
        task.setOnFailed((Throwable err) -> {
            Text errMsg = new Text(err.toString());
            root.setCenter(errMsg);
        });

        task.startInNewThread();
    }
    
    private void addRecentFile(File newFile) {
        recentFiles.remove(newFile);
        recentFiles.addFirst(newFile);
        menuBar.updateRecentFiles(recentFiles, file -> {
            openFile(file);
        });
    }
    
    private void openNewWindow() {
        // is this correct?
        new ClasspyApp().start(new Stage());
    }
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
