package com.github.zxh.classpy.gui;

import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author zxh
 */
public class AboutDialog {
    
    public static void showDialog() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        
        BorderPane aboutPane = createAboutPane(dialogStage);
        Scene scene = new Scene(aboutPane, 300, 200);
        
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    
    private static BorderPane createAboutPane(Stage dialogStage) {
        BorderPane pane = new BorderPane();
        pane.setOnMouseClicked(e -> {
            dialogStage.close();
        });
        
        Hyperlink home = new Hyperlink("https://github.com/zxh0/classpy");
        pane.setCenter(home);
        
        return pane;
    }
    
}
