package com.github.zxh.classpy.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author zxh
 */
public class MyMenuBar extends MenuBar {

    private MenuItem openMenuItem;
    private MenuItem aboutMenuItem;
    
    public MyMenuBar() {
        getMenus().add(createFileMenu());
        getMenus().add(createHelpMenu());
    }
    
    private Menu createFileMenu() {
        openMenuItem = new MenuItem("Open...");
        
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().add(openMenuItem);
        
        return fileMenu;
    }
    
    private Menu createHelpMenu() {
        aboutMenuItem = new MenuItem("About");
        
        Menu helpMenu = new Menu("Help");
        helpMenu.getItems().add(aboutMenuItem);
        
        return helpMenu;
    }

    public MenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    public MenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }
    
}
