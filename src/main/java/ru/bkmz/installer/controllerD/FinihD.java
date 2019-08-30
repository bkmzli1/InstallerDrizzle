package ru.bkmz.installer.controllerD;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import ru.bkmz.installer.util.ImageLoader;

import java.awt.*;
import java.net.URL;

public class FinihD  {

    public AnchorPane rootPane;
    public ImageView imeg;
    public Button next;
    public Label TL;
    public CheckBox cb;

    public void initialize() {
        imeg.setImage(ImageLoader.IMAGE_LOADER.getImage("img/fon_d_complited"));
        cb.setText("Просмотреть другие версии и\n" +
                "проэты");
    }

    public void next(ActionEvent actionEvent) {
        if (cb.isSelected()) {
            try {
                Desktop.getDesktop().browse(new URL("https://github.com/bkmzli1/InstallerDrizzle/tree/master/versions").toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }
}