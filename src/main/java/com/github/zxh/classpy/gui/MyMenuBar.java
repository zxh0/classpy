package com.github.zxh.classpy.gui;

import java.net.URL;
import java.util.List;
import java.util.function.Consumer;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Classpy menu bar.
 * 
 * File             Window        Help
 *  |-Open File...   |-New Window  |-About
 *  |-Open Recent
 * 
 * @author zxh
 */
public class MyMenuBar extends MenuBar {

    private Menu recentMenu;
    private MenuItem openMenuItem;
    private MenuItem newWinMenuItem;
    private MenuItem aboutMenuItem;
    
    public MyMenuBar() {
        getMenus().add(createFileMenu());
        getMenus().add(createWindowMenu());
        getMenus().add(createHelpMenu());
    }
    
    private Menu createFileMenu() {
        openMenuItem = new MenuItem("Open File...");
        
        Menu fileMenu = new Menu("File");
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
    public MenuItem getNewWinMenuItem() {return newWinMenuItem;}
    public MenuItem getAboutMenuItem() {return aboutMenuItem;}
    
    public void updateRecentFiles(List<URL> files, Consumer<URL> onOpenRecentFileAction) {
        recentMenu.getItems().clear();
        files.stream().forEach((file) -> {
            MenuItem menuItem = new MenuItem(file.toString());
            recentMenu.getItems().add(menuItem);
            menuItem.setOnAction(e -> {
                onOpenRecentFileAction.accept(file);
            });
        });
    }
    
}
