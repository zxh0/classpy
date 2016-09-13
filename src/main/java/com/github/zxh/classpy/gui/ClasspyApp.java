package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.classfile.ClassComponent;
import com.github.zxh.classpy.helper.UrlHelper;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Main class.
 */
public class ClasspyApp extends Application {

    private static final String TITLE = "Classpy";
    
    private FileChooser fileChooser;
    private Stage stage;
    private BorderPane root;
    private MyMenuBar menuBar;
    
    private File lastOpenFile;
    private final LinkedList<URL> recentFiles = new LinkedList<>();
    
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        root = new BorderPane();
        root.setTop(createMenuBar());
        root.setCenter(createTabPane());
        updateRecentFiles();
        
        stage.setScene(new Scene(root, 960, 540));
        stage.setTitle(TITLE);
        stage.show();
    }
    
    private TabPane createTabPane() {
        TabPane tp = new TabPane();
        tp.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) -> {
                    if (newTab != null) {
                        URL url = (URL) newTab.getUserData();
                        stage.setTitle(TITLE + " - " + url);
                    }
        });
        return tp;
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
            try {
                if (file.getName().endsWith(".jar") || file.getName().endsWith(".JAR")) {
                    URL classUrl = JarDialog.showDialog(file);
                    if (classUrl != null) {
                        openFile(classUrl);
                    }
                } else {
                    openFile(file);
                }
            } catch (Exception e) {
                // todo
                e.printStackTrace(System.err);
            }
        }
    }
    
    private void initFileChooser() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("JAR", "*.jar"),
            new FileChooser.ExtensionFilter("CLASS", "*.class")
        );
    }
    
    private void openFile(File file) throws MalformedURLException {
        openFile(file, file.toURI().toURL());
    }
    
    private void openFile(URL url) {
        openFile(null, url);
    }
    
    private void openFile(File file, URL url) {
        Tab tab = createTab(url);
        OpenFileTask task = new OpenFileTask(url);
        
        task.setOnSucceeded((ClassComponent fc, FileHex hex) -> {
            MainPane mainPane = new MainPane(fc, hex);
            tab.setContent(mainPane);
            
            // todo
            addRecentFile(url);
            if (file != null) {
                lastOpenFile = file;
            }
        });
        
        task.setOnFailed((Throwable err) -> {
            Text errMsg = new Text(err.toString());
            tab.setContent(errMsg);
        });

        task.startInNewThread();
    }
    
    private Tab createTab(URL url) {
        Tab tab = new Tab();
        tab.setText(UrlHelper.getFileName(url));
        tab.setUserData(url);
        tab.setContent(new BorderPane(new ProgressBar()));
        ((TabPane) root.getCenter()).getTabs().add(tab);
        return tab;
    }
    
    private void addRecentFile(URL newFile) {
        recentFiles.remove(newFile);
        recentFiles.addFirst(newFile);
        updateRecentFiles();
    }
    
    private void updateRecentFiles() {
        menuBar.updateRecentFiles(recentFiles, file -> {
            openFile(file);
        });
    }
    
    private void openNewWindow() {
        ClasspyApp newApp = new ClasspyApp();
        if (!recentFiles.isEmpty()) {
            newApp.recentFiles.addAll(recentFiles);
        }
        // is this correct?
        newApp.start(new Stage());
    }
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
