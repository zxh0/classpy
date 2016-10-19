package com.github.zxh.classpy.gui;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.github.zxh.classpy.common.BytesComponent;
import com.github.zxh.classpy.gui.support.*;
import com.github.zxh.classpy.helper.UrlHelper;

/**
 * Main class.
 */
public class ClasspyApp extends Application {

    private static final String TITLE = "Classpy";


    private Stage stage;
    private BorderPane root;
    private MyMenuBar menuBar;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        root = new BorderPane();
        root.setTop(createMenuBar());
        root.setCenter(createTabPane());

        stage.setScene(new Scene(root, 960, 540));
        stage.setTitle(TITLE);
        stage.getIcons().add(ImageHelper.loadImage("/spy16.png"));
        stage.getIcons().add(ImageHelper.loadImage("/spy32.png"));
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

        menuBar.setOnOpenFile(this::onOpenFile);
        menuBar.setOnNewWindow(this::openNewWindow);

        return menuBar;
    }

    private void onOpenFile(FileType ft, URL url) {
        if (url == null) {
            showFileChooser(ft);
        } else if (ft == FileType.JAVA_JAR) {
            try {
                openJar(new File(url.toURI()));
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        } else {
            openFile(url);
        }
    }

    private void showFileChooser(FileType ft) {
        File file = MyFileChooser.showFileChooser(stage, ft);
        if (file != null) {
            try {
                if (ft == FileType.JAVA_JAR) {
                    openJar(file);
                } else {
                    openFile(file);
                }
            } catch (Exception e) {
                // todo
                e.printStackTrace(System.err);
            }
        }
    }

    private void openJar(File jarFile) throws Exception {
        URL classUrl = JarDialog.showDialog(jarFile);
        RecentFiles.INSTANCE.add(FileType.JAVA_JAR, jarFile.toURI().toURL());
        menuBar.updateRecentFiles();
        if (classUrl != null) {
            openFile(classUrl);
        }
    }
    
    private void openFile(File file) throws MalformedURLException {
        openFile(file.toURI().toURL());
    }
    
    private void openFile(URL url) {
        Tab tab = createTab(url);
        OpenFileTask task = new OpenFileTask(url);
        
        task.setOnSucceeded((BytesComponent bc, HexText hex) -> {
            MainPane mainPane = new MainPane(bc, hex);
            tab.setContent(mainPane);
            
            // todo
            RecentFiles.INSTANCE.add(FileType.typeOf(bc), url);
            menuBar.updateRecentFiles();
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

    private void openNewWindow() {
        ClasspyApp newApp = new ClasspyApp();
        // is this correct?
        newApp.start(new Stage());
    }
    
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
