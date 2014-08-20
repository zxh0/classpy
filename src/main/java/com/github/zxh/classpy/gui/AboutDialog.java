package com.github.zxh.classpy.gui;

import java.io.IOException;
import java.net.URI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author zxh
 */
public class AboutDialog {
    
    private static final String HOME_URL = "https://github.com/zxh0/classpy";
    
    public static void showDialog() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        BorderPane aboutPane = createAboutPane(stage);
        Scene scene = new Scene(aboutPane, 300, 200);
        
        stage.setScene(scene);
        stage.setTitle("About");
        stage.show();
    }
    
    private static BorderPane createAboutPane(Stage dialogStage) {
        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("Classpy"));
        vbox.getChildren().add(new Label(" "));
        vbox.getChildren().add(createHomeLink());
        vbox.setAlignment(Pos.CENTER);
        
        BorderPane pane = new BorderPane();
        pane.setCenter(vbox);
        pane.setOnMouseClicked(e -> {
            dialogStage.close();
        });
        
        return pane;
    }
    
    private static Hyperlink createHomeLink() {
        Hyperlink link = new Hyperlink(HOME_URL);
        link.setOnAction(e -> {
            try {
                java.awt.Desktop.getDesktop().browse(URI.create(HOME_URL));
            } catch (IOException x) {
                System.out.println(x);
            }
        });
        
        return link;
    }
    
}
