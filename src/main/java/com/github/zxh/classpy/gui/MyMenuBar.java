package com.github.zxh.classpy.gui;

import java.net.URL;
import java.util.function.BiConsumer;

import com.github.zxh.classpy.gui.support.ImageHelper;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import com.github.zxh.classpy.gui.support.FileType;
import com.github.zxh.classpy.gui.support.RecentFile;
import com.github.zxh.classpy.gui.support.RecentFiles;

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
public class MyMenuBar extends MenuBar {

    private BiConsumer<FileType, URL> onOpenFile;
    private Runnable onNewWindow;

    public MyMenuBar() {
        createFileMenu();
        createWindowMenu();
        createHelpMenu();
    }
    
    private void createFileMenu() {
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(createOpenMenu());
        fileMenu.getItems().add(createRecentMenu());
        getMenus().add(fileMenu);
    }

    private Menu createOpenMenu() {
        Menu openMenu = new Menu("Open", ImageHelper.createImageView("/open.png"));
        openMenu.getItems().add(createOpenMenuItem(FileType.JAVA_JAR));
        openMenu.getItems().add(createOpenMenuItem(FileType.JAVA_CLASS));
        openMenu.getItems().add(createOpenMenuItem(FileType.LUAC_OUT));
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
        Menu recentMenu = new Menu("Open Recent", ImageHelper.createImageView("/clock.png"));
        for (RecentFile rf : RecentFiles.INSTANCE.getAll()) {
            ImageView icon = new ImageView(rf.type.icon);
            MenuItem menuItem = new MenuItem(rf.url.toString(), icon);
            menuItem.setOnAction(e -> onOpenFile.accept(rf.type, rf.url));
            recentMenu.getItems().add(menuItem);
        }
        return recentMenu;
    }
    
    private void createWindowMenu() {
        MenuItem newWinMenuItem = new MenuItem("New Window");
        newWinMenuItem.setOnAction(e -> onNewWindow.run());
        
        Menu winMenu = new Menu("Window");
        winMenu.getItems().add(newWinMenuItem);
        
        getMenus().add(winMenu);
    }
    
    private void createHelpMenu() {
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(e -> AboutDialog.showDialog());
        
        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().add(aboutMenuItem);
        
        getMenus().add(helpMenu);
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
