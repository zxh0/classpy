package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.MethodInfo;
import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.common.FileHex;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Node;
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
    private File lastOpenFile;
    
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        root = new BorderPane();
        root.setTop(createMenuBar());
        
        stage.setScene(new Scene(root, 900, 600));
        stage.setTitle(TITLE);
        stage.show();
    }
    
    private MenuBar createMenuBar() {
        MyMenuBar menuBar = new MyMenuBar();
        
        menuBar.getOpenMenuItem().setOnAction(e -> showFileChooser());
        menuBar.getReloadMenuItem().setOnAction(e -> reloadFile());
        menuBar.getPlayBytecodeMenuItem().setOnAction(e -> playBytecode());
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
            // todo
            openFile(file, () -> {
                // todo
                lastOpenFile = file;
            });
        }
    }
    
    private void initFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("CLASS", "*.class"),
            new FileChooser.ExtensionFilter("DEX", "*.dex"),
            new FileChooser.ExtensionFilter("PE/COFF", "*.exe")
        );
    }
    
    private void openFile(File file, Runnable succeededCallback) {
        ProgressBar pb = new ProgressBar();
        root.setCenter(pb);
        
        OpenFileTask task = new OpenFileTask(file);
        
        task.setOnSucceeded((FileComponent fc, FileHex hex) -> {
            MainPane mainPane = new MainPane(fc, hex);
            root.setCenter(mainPane);
            stage.setTitle(TITLE + " - " + file.getAbsolutePath());
            
            succeededCallback.run();
        });
        
        task.setOnFailed((Throwable err) -> {
            Text errMsg = new Text(err.toString());
            root.setCenter(errMsg);
        });

        task.startInNewThread();
    }
    
    private void reloadFile() {
        if (lastOpenFile != null) {
            if (lastOpenFile.exists()) {
                openFile(lastOpenFile, () -> {});
            } else {
                // todo
            }
        }
    }
    
    private void playBytecode() {
        Node node = root.getCenter();
        if (node != null) {
            MainPane mainPane = (MainPane) node;
            MethodInfo method = mainPane.getSelectedMethodInfo();
            if (method != null) {
                // todo
                System.out.println(method);
            }
        }
    }
    
    private void openNewWindow() {
        // is this correct?
        new ClasspyApp().start(new Stage());
    }
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
