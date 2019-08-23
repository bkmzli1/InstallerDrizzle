package ru.bkmz.installer.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import ru.bkmz.installer.Installer;
import ru.bkmz.installer.Main;
import ru.bkmz.installer.util.ImageLoader;
import ru.bkmz.installer.util.PaneNext;

import java.io.File;

public class QuestionOne {
    public Button next;
    public Label TL;
    public ImageView imeg;
    public AnchorPane rootPane;
    public static Installer installer;
    public CheckBox desctop;
    public CheckBox pysk;
    public Button failList;
    public TextField url;
    public static String urls;


    public void initialize() {
        imeg.setImage(ImageLoader.IMAGE_LOADER.getImage("img/fon"));
    }

    public void next(ActionEvent actionEvent) {
        urls = url.getText();
        installer = new Installer(desctop.isSelected(), pysk.isSelected());
        new PaneNext(rootPane, "fxml/installer/installer.fxml");
    }

    public void onFailList(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(Main.stage);
        url.setText(file + "\\drizzle");


    }
}
