package ru.bkmz.installer.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.File;
import java.net.URL;

import static ru.bkmz.installer.Installer.fileListD;

public class Finch {

    public AnchorPane rootPane;
    public ImageView imeg;
    public Button next;
    public Label TL;
    public CheckBox cb;

    public void initialize() {
        cb.setText("Просмотреть другие версии и\n" +
                "Проэты");
    }

    public void next(ActionEvent actionEvent) {
        if (cb.isSelected()) {
            try {
                Desktop.getDesktop().browse(new URL("https://github.com/bkmzli1/InstallerDrizzle/blob/master/versions/Installer%20drizzle.exe").toURI());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (File f : fileListD) {
            f.delete();
        }
        System.exit(0);
    }
}
