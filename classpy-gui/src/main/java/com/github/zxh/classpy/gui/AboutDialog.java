package com.github.zxh.classpy.gui;

import com.github.zxh.classpy.gui.support.ImageHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class AboutDialog {

    public static void showDialog(Consumer<String> onOpenURL) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        BorderPane aboutPane = createAboutPane(stage, onOpenURL);
        Scene scene = new Scene(aboutPane, 300, 180);

        stage.setScene(scene);
        stage.setTitle("About");
        stage.show();
    }

    private static BorderPane createAboutPane(Stage dialogStage,
                                              Consumer<String> onOpenURL) {
        BorderPane pane = new BorderPane();
        //pane.setTop(new Label("Classpy"));
        pane.setCenter(ImageHelper.createImageView("/spy128.png"));
        pane.setBottom(createHomeLink(onOpenURL));
        pane.setOnMouseClicked(e -> dialogStage.close());

        return pane;
    }

    // https://stackoverflow.com/questions/16604341/how-can-i-open-the-default-system-browser-from-a-java-fx-application
    private static Hyperlink createHomeLink(Consumer<String> onOpenURL) {
        String homeUrl = "https://github.com/zxh0/classpy";
        Hyperlink link = new Hyperlink(homeUrl);
        link.setOnAction(e -> onOpenURL.accept(homeUrl));

        BorderPane.setAlignment(link, Pos.CENTER);
        BorderPane.setMargin(link, new Insets(8));
        return link;
    }

}
