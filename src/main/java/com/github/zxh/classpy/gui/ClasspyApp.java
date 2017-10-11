package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.common.FileComponent;
import com.github.zxh.classpy.gui.jar.JarTreeView;
import com.github.zxh.classpy.gui.parsed.HexText;
import com.github.zxh.classpy.gui.parsed.ParsedViewerPane;
import com.github.zxh.classpy.gui.support.FileType;
import com.github.zxh.classpy.gui.support.ImageHelper;
import com.github.zxh.classpy.gui.support.OpenFileTask;
import com.github.zxh.classpy.gui.support.RecentFiles;
import com.github.zxh.classpy.helper.UrlHelper;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

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

        Scene scene = new Scene(root, 960, 540);
        //scene.getStylesheets().add("classpy.css");
        enableDragAndDrop(scene);

        stage.setScene(scene);
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
                // TODO
                e.printStackTrace(System.err);
            }
        }
    }

    private void openJar(File jarFile) throws Exception {
        if (JarTreeView.isOpen(jarFile)) {
            // TODO
            System.out.println("jar is already open: " + jarFile);
            return;
        }

        JarTreeView treeView = new JarTreeView(jarFile);
        treeView.setOpenClassHandler(this::openClassInJar);

        Tab tab = createTab(jarFile.toURI().toURL());
        tab.setContent(treeView.getTreeView());

        RecentFiles.INSTANCE.add(FileType.JAVA_JAR, jarFile);
        menuBar.updateRecentFiles();
    }

    private void openClassInJar(String url) {
        try {
            openFile(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace(System.err);
        }
    }

    private void openFile(File file) throws MalformedURLException {
        openFile(file.toURI().toURL());
    }

    private void openFile(URL url) {
        Tab tab = createTab(url);
        OpenFileTask task = new OpenFileTask(url);

        task.setOnSucceeded((FileComponent fc, HexText hex) -> {
            ParsedViewerPane viewerPane = new ParsedViewerPane(fc, hex);
            tab.setContent(viewerPane);

            // TODO
            RecentFiles.INSTANCE.add(FileType.typeOf(fc), url);
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

    // http://www.java2s.com/Code/Java/JavaFX/DraganddropfiletoScene.htm
    private void enableDragAndDrop(Scene scene) {
        scene.setOnDragOver(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY);
            } else {
                event.consume();
            }
        });

        // Dropping over surface
        scene.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                success = true;
                for (File file : db.getFiles()) {
                    //System.out.println(file.getAbsolutePath());
                    try {
                        if (file.getName().endsWith(".jar")) {
                            openJar(file);
                        } else {
                            openFile(file);
                        }
                    } catch (Exception e) {
                        // TODO
                        throw new RuntimeException(e);
                    }
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

}
