package com.github.zxh.classpy.gui.support;

import java.net.URL;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.github.zxh.classpy.gui.AboutDialog;

/**
 *
 */
public class ImageHelper {

    public static ImageView createImageView(String imgName) {
        return new ImageView(loadImage(imgName));
    }

    public static Image loadImage(String imgName) {
        URL imgUrl = AboutDialog.class.getResource(imgName);
        return new Image(imgUrl.toString());
    }

}
