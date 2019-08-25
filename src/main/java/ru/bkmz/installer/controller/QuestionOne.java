package ru.bkmz.installer.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
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
        File file = new File(url.getText());
        if (file.exists()) {
            urls = url.getText() + "\\drizzle";
            installer = new Installer(desctop.isSelected(), pysk.isSelected(),urls);
            new PaneNext(rootPane, "fxml/installer/installer.fxml");
        } else {
            notification("Ошибка", "Дериктория " + file + " ненайдена");
        }
    }

    public void onFailList(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(Main.stage);
        url.setText(file + "");


    }

    public static void notification(String name, String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(name);
        alert.setHeaderText(null);
        alert.setContentText(info);
        alert.showAndWait();
    }
}
