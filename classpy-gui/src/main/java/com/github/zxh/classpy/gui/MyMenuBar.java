package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.gui.support.FileType;
import com.github.zxh.classpy.gui.support.ImageHelper;
import com.github.zxh.classpy.gui.support.RecentFile;
import com.github.zxh.classpy.gui.support.RecentFiles;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Classpy menu bar.
 * 
 * File               Window          Help
 *  |-Open >          |-New Window    |-About
 *    |-Java Class... |-Class All Tabs
 *    |-Java Jar...
 *    |-Luac Out...
 *  |-Open Recent >
 */
final class MyMenuBar extends MenuBar {

    private BiConsumer<FileType, String> onOpenFile;
    private Runnable onCloseAllTabs;
    private Runnable onNewWindow;
    private Consumer<String> onOpenURL;

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
        ImageView folderIcon = ImageHelper.createImageView("/folder.png");
        Menu openMenu = new Menu("_Open", folderIcon);
        openMenu.getItems().add(createOpenFolderItem(folderIcon));
        openMenu.getItems().add(createOpenMenuItem(FileType.JAVA_JAR));
        openMenu.getItems().add(createOpenMenuItem(FileType.JAVA_JMOD));
        openMenu.getItems().add(createOpenMenuItem(FileType.JAVA_CLASS));
        openMenu.getItems().add(createOpenMenuItem(FileType.LUA_BC));
        openMenu.getItems().add(createOpenMenuItem(FileType.WASM));
        openMenu.getItems().add(createOpenMenuItem(FileType.BITCODE));
        openMenu.getItems().add(createOpenMenuItem(FileType.BITCOIN_BLOCK));
        openMenu.getItems().add(createOpenMenuItem(FileType.BITCOIN_TX));
        openMenu.setMnemonicParsing(true);
        return openMenu;
    }

    private MenuItem createOpenFolderItem(ImageView icon) {
        MenuItem item = new MenuItem("Folder ... ", icon);
        item.setOnAction(e -> onOpenFile.accept(FileType.FOLDER, null));
        return item;
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
            MenuItem menuItem = new MenuItem(rf.url, icon);
            menuItem.setOnAction(e -> onOpenFile.accept(rf.type, rf.url));
            recentMenu.getItems().add(menuItem);
        }
        recentMenu.setMnemonicParsing(true);
        return recentMenu;
    }
    
    private void createWindowMenu() {
        MenuItem newWinMenuItem = new MenuItem("New Window");
        newWinMenuItem.setOnAction(e -> onNewWindow.run());
        MenuItem closeTabsMenuItem = new MenuItem("Class All Tabs");
        closeTabsMenuItem.setOnAction(e -> onCloseAllTabs.run());
        
        Menu winMenu = new Menu("_Window");
        winMenu.getItems().add(newWinMenuItem);
        winMenu.getItems().add( closeTabsMenuItem);
        winMenu.setMnemonicParsing(true);

        getMenus().add(winMenu);
    }
    
    private void createHelpMenu() {
        MenuItem aboutMenuItem = new MenuItem("_About");
        aboutMenuItem.setOnAction(e -> AboutDialog.showDialog(onOpenURL));
        aboutMenuItem.setMnemonicParsing(true);

        Menu helpMenu = new Menu("_Help");
        helpMenu.getItems().add(aboutMenuItem);
        helpMenu.setMnemonicParsing(true);

        getMenus().add(helpMenu);
    }

    public void setOnOpenFile(BiConsumer<FileType, String> onOpenFile) {
        this.onOpenFile = onOpenFile;
    }

    public void setOnNewWindow(Runnable onNewWindow) {
        this.onNewWindow = onNewWindow;
    }

    public void setOnCloseAllTabs(Runnable onCloseAllTabs) {
        this.onCloseAllTabs = onCloseAllTabs;
    }

    public void setOnOpenURL(Consumer<String> onOpenURL) {
        this.onOpenURL = onOpenURL;
    }

    public void updateRecentFiles() {
        Menu fileMenu = getMenus().get(0);
        fileMenu.getItems().set(1, createRecentMenu());
    }

}
