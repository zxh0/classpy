package com.github.zxh.classpy.gui;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Classpy menu bar.
 * 
 * File       Tools            Window        Help
 *  |-Open...  |-Play bytecode  |-New Window  |-About
 *  |-Reload
 * 
 * @author zxh
 */
public class MyMenuBar extends MenuBar {

    private Menu recentMenu;
    private MenuItem reloadMenuItem;
    private MenuItem openMenuItem;
    private MenuItem newWinMenuItem;
    private MenuItem aboutMenuItem;
    
    public MyMenuBar() {
        getMenus().add(createFileMenu());
        getMenus().add(createWindowMenu());
        getMenus().add(createHelpMenu());
    }
    
    private Menu createFileMenu() {
        reloadMenuItem = new MenuItem("Reload");
        openMenuItem = new MenuItem("Open...");
        
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(reloadMenuItem);
        fileMenu.getItems().add(openMenuItem);
        
        recentMenu = new Menu("Open Recent");
        fileMenu.getItems().add(recentMenu);
        
        return fileMenu;
    }
    
    private Menu createWindowMenu() {
        newWinMenuItem = new MenuItem("New Window");
        
        Menu winMenu = new Menu("Window");
        winMenu.getItems().add(newWinMenuItem);
        
        return winMenu;
    }
    
    private Menu createHelpMenu() {
        aboutMenuItem = new MenuItem("About");
        
        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().add(aboutMenuItem);
        
        return helpMenu;
    }
    
    // Getters
    public MenuItem getOpenMenuItem() {return openMenuItem;}
    public MenuItem getReloadMenuItem() {return reloadMenuItem;}
    public MenuItem getNewWinMenuItem() {return newWinMenuItem;}
    public MenuItem getAboutMenuItem() {return aboutMenuItem;}
    
    public void updateRecentFiles(List<File> files, Consumer<File> onOpenRecentFileAction) {
        recentMenu.getItems().clear();
        files.stream().forEach((file) -> {
            MenuItem menuItem = new MenuItem(file.getAbsolutePath());
            recentMenu.getItems().add(menuItem);
            menuItem.setOnAction(e -> {
                onOpenRecentFileAction.accept(file);
            });
        });
    }
    
}
