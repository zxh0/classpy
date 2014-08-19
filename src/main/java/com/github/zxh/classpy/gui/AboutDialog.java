package com.github.zxh.classpy.gui;

import java.io.IOException;
import java.net.URI;
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
    
    private static final String HOME_URL = "https://github.com/zxh0/classpy";
    
    public static void showDialog() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        
        BorderPane aboutPane = createAboutPane(dialogStage);
        Scene scene = new Scene(aboutPane, 300, 200);
        
        dialogStage.setScene(scene);
        dialogStage.show();
    }
    
    private static BorderPane createAboutPane(Stage dialogStage) {
        Hyperlink link = new Hyperlink(HOME_URL);
        link.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(URI.create(HOME_URL));
            } catch (IOException x) {
                System.out.println(x);
            }
        });
        
        BorderPane pane = new BorderPane();
        pane.setCenter(link);
        pane.setOnMouseClicked(e -> {
            dialogStage.close();
        });
        
        return pane;
    }
    
}
