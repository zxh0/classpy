package com.github.zxh.classpy.gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author zxh
 */
public class AboutDialog {
    
    public static void showDialog() {
        BorderPane pane = new BorderPane();
        // todo
        //return pane;
        
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        
        dialogStage.setScene(new Scene(pane, 300, 400));
        dialogStage.show();
    }
    
}
