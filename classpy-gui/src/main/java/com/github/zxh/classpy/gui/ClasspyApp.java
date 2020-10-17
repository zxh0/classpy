package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.gui.fs.DirTreeView;
import com.github.zxh.classpy.gui.parsed.ParsedViewerPane;
import com.github.zxh.classpy.gui.support.*;
import com.github.zxh.classpy.gui.fs.ZipTreeView;
import com.github.zxh.classpy.helper.UrlHelper;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Optional;

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

        // cmd args
        String userDir = System.getProperty("user.dir");
        for (String arg : this.getParameters().getRaw()) {
            String path = arg;
            if (!arg.startsWith("/")) {
                path = userDir + "/" + arg;
            }
            openFile(new File(path));
        }
    }

    private TabPane createTabPane() {
        TabPane tp = new TabPane();
        tp.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) -> {
                    if (newTab != null) {
                        stage.setTitle(TITLE + " - " + newTab.getUserData());
                    }
        });
        return tp;
    }

    private Tab createFileTab(String url) {
        Tab tab = new Tab();
        tab.setText(UrlHelper.getFileName(url));
        tab.setUserData(url);
        tab.setContent(new BorderPane(new ProgressBar()));
        ((TabPane) root.getCenter()).getTabs().add(tab);
        return tab;
    }

    private Tab createDirTab(File dir) {
        Tab tab = new Tab();
        tab.setText(dir.getName() + "/");
        tab.setContent(new BorderPane(new ProgressBar()));
        ((TabPane) root.getCenter()).getTabs().add(tab);
        return tab;
    }

    private MenuBar createMenuBar() {
        menuBar = new MyMenuBar();

        menuBar.setOnOpenFile(this::onOpenFile);
        menuBar.setOnNewWindow(this::openNewWindow);
        menuBar.setOnCloseAllTabs(this::onCloseTabs);
        //menuBar.setUseSystemMenuBar(true);

        return menuBar;
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
                    openFile(file);
                }
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }

    private void openNewWindow() {
        ClasspyApp newApp = new ClasspyApp();
        // is this correct?
        newApp.start(new Stage());
    }

    private void onCloseTabs() {
        ((TabPane) root.getCenter()).getTabs().clear();
    }

    private void onOpenFile(FileType ft, String url) {
        if (ft == FileType.FOLDER) {
            openDir(url);
        } else if (url == null) {
            if (ft == FileType.BITCOIN_BLOCK) {
                showBitcoinBlockDialog();
            } else if (ft == FileType.BITCOIN_TX) {
                showBitcoinTxDialog();
            } else if (ft == FileType.EVM_BYTECODE) {
                showEvmBytecodeDialog();
            } else {
                showFileChooser(ft);
            }
        } else {
            openFile(url);
        }
    }

    private void openDir(String url) {
        File dir = null;
        if (url != null) {
            try {
                dir = new File(new URL(url).toURI());
            } catch (MalformedURLException | URISyntaxException e) {
                e.printStackTrace(System.err);
            }
        } else {
            dir = MyFileChooser.showDirChooser(stage);
        }

        if (dir != null) {
            System.out.println(dir);
            try {
                DirTreeView treeView = DirTreeView.create(dir);
                treeView.setOpenFileHandler(this::openFile);

                Tab tab = createDirTab(dir);
                tab.setContent(treeView.getTreeView());

                RecentFiles.INSTANCE.add(FileType.FOLDER, dir.toURI().toURL().toString());
                menuBar.updateRecentFiles();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    private void showBitcoinBlockDialog() {
        String apiUrl = "https://blockchain.info/rawblock/<hash>?format=hex";
        String genesisBlockHash = "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f";

        TextInputDialog dialog = new TextInputDialog(genesisBlockHash);
        dialog.setTitle("Block Hash Input Dialog");
        dialog.setHeaderText("API: " + apiUrl);
        dialog.setContentText("hash: ");
        dialog.setResizable(true);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            openFile(apiUrl.replace("<hash>", result.get()));
        }
    }

    private void showBitcoinTxDialog() {
        String apiUrl = "https://blockchain.info/rawtx/<hash>?format=hex";

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Transaction Hash Input Dialog");
        dialog.setHeaderText("API: " + apiUrl);
        dialog.setContentText("hash: ");
        dialog.setResizable(true);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            apiUrl.replace("<hash>", result.get());
        }
    }

    private void showEvmBytecodeDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("EVM Bytecode Input Dialog");
        dialog.setHeaderText("EVM Bytecode:");
        //dialog.setContentText("hash: ");
        dialog.setResizable(true);
        dialog.getEditor().setMinWidth(500);

        // TODO
    }

    private void showFileChooser(FileType ft) {
        File file = MyFileChooser.showFileChooser(stage, ft);
        if (file != null) {
            openFile(file);
        }
    }

    private void openFile(File file) {
        try {
            openFile(file.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace(System.err);
        }
    }

    private void openFile(String url) {
        Tab tab = createFileTab(url);
        OpenFileTask task = new OpenFileTask(url);

        task.setOnSucceeded((OpenFileResult ofr) -> {
            if (ofr.fileType.isZip()) {
                ZipTreeView treeView = new ZipTreeView(ofr.url, ofr.zipRootNode);
                treeView.setOpenFileHandler(this::openFile);
                tab.setContent(treeView.getTreeView());
            } else {
                ParsedViewerPane viewerPane = new ParsedViewerPane(ofr.fileRootNode, ofr.hexText);
                tab.setContent(viewerPane);
            }

            RecentFiles.INSTANCE.add(ofr.fileType, url);
            menuBar.updateRecentFiles();
        });

        task.setOnFailed((Throwable err) -> {
            Text errMsg = new Text(err.toString());
            tab.setContent(errMsg);
        });

        task.startInNewThread();
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

}
