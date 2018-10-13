package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.gui.support.FileType;
import com.github.zxh.classpy.gui.support.ImageHelper;
import com.github.zxh.classpy.gui.support.RecentFile;
import com.github.zxh.classpy.gui.support.RecentFiles;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * Classpy menu bar.
 * 
 * File              Window        Help
 *  |-Open >         |-New Window  |-About
 *    |-Java Class...
 *    |-Java Jar...
 *    |-Luac Out...
 *  |-Open Recent >
 */
public final class MyMenuBar extends MenuBar {

    private BiConsumer<FileType, URL> onOpenFile;
    private Runnable onNewWindow;

    public MyMenuBar() {
        createFileMenu();
        createWindowMenu();
        createHelpMenu();
    }
    
    private void createFileMenu() {
        Menu fileMenu = new Menu("_File");
        fileMenu.getItems().add(createOpenMenu());
        fileMenu.getItems().add(createRecentMenu());
        fileMenu.setMnemonicParsing(true);
        getMenus().add(fileMenu);
    }

    private Menu createOpenMenu() {
        Menu openMenu = new Menu("_Open", ImageHelper.createImageView("/open.png"));
        openMenu.getItems().add(createOpenMenuItem(FileType.JAVA_JAR));
        openMenu.getItems().add(createOpenMenuItem(FileType.JAVA_CLASS));
        openMenu.getItems().add(createOpenMenuItem(FileType.LUA_BC));
        openMenu.getItems().add(createOpenMenuItem(FileType.WASM));
        openMenu.getItems().add(createOpenBcBlockMenuItem());
        openMenu.setMnemonicParsing(true);
        return openMenu;
    }

    private MenuItem createOpenMenuItem(FileType ft) {
        String text = ft.filter.getDescription() + " ...";
        ImageView icon = new ImageView(ft.icon);
        MenuItem item = new MenuItem(text, icon);
        item.setOnAction(e -> onOpenFile.accept(ft, null));
        return item;
    }

    private Menu createRecentMenu() {
        Menu recentMenu = new Menu("Open _Recent", ImageHelper.createImageView("/clock.png"));
        for (RecentFile rf : RecentFiles.INSTANCE.getAll()) {
            ImageView icon = new ImageView(rf.type.icon);
            MenuItem menuItem = new MenuItem(rf.url.toString(), icon);
            menuItem.setOnAction(e -> onOpenFile.accept(rf.type, rf.url));
            recentMenu.getItems().add(menuItem);
        }
        recentMenu.setMnemonicParsing(true);
        return recentMenu;
    }
    
    private void createWindowMenu() {
        MenuItem newWinMenuItem = new MenuItem("New Window");
        newWinMenuItem.setOnAction(e -> onNewWindow.run());
        
        Menu winMenu = new Menu("_Window");
        winMenu.getItems().add(newWinMenuItem);
        winMenu.setMnemonicParsing(true);

        getMenus().add(winMenu);
    }
    
    private void createHelpMenu() {
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(e -> AboutDialog.showDialog());
        aboutMenuItem.setMnemonicParsing(true);

        Menu helpMenu = new Menu("_Help");
        helpMenu.getItems().add(aboutMenuItem);
        helpMenu.setMnemonicParsing(true);

        getMenus().add(helpMenu);
    }

    private MenuItem createOpenBcBlockMenuItem() {
        String apiUrl = "https://blockchain.info/rawblock/<hash>?format=hex";
        String genesisBlockHash = "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f";

        MenuItem item = createOpenMenuItem(FileType.BITCOIN_BLOCK);
        item.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog(genesisBlockHash);
            dialog.setTitle("Block Hash Input Dialog");
            dialog.setHeaderText("API: " + apiUrl);
            dialog.setContentText("hash: ");
            dialog.setResizable(true);

            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                try {
                    String url = apiUrl.replace("<hash>", result.get());
                    onOpenFile.accept(FileType.BITCOIN_BLOCK, new URL(url));
                } catch (MalformedURLException ignored) {}
            }
        });
        return item;
    }

    public void setOnOpenFile(BiConsumer<FileType, URL> onOpenFile) {
        this.onOpenFile = onOpenFile;
    }

    public void setOnNewWindow(Runnable onNewWindow) {
        this.onNewWindow = onNewWindow;
    }

    public void updateRecentFiles() {
        Menu fileMenu = getMenus().get(0);
        fileMenu.getItems().set(1, createRecentMenu());
    }

}
