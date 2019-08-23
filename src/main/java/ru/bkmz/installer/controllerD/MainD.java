package ru.bkmz.installer.controllerD;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ru.bkmz.installer.util.ImageLoader;
import ru.bkmz.installer.util.PaneNext;

public class MainD {
    public ImageView imeg;
    public Button next;
    public Label TL;
    public CheckBox dDekctop;
    public CheckBox dGame;
    public CheckBox dSave;
    public CheckBox deleteAll;
    public AnchorPane rootPane;

    public void initialize() {
        imeg.setImage(ImageLoader.IMAGE_LOADER.getImage("img/fon_d"));
    }

    public static boolean bDGame = false;
    public static boolean bDSave = false;
    public static boolean bDDekctop = false;
    public static boolean bDeleteAll = false;

    public void next(ActionEvent actionEvent) {
        bDDekctop = dDekctop.isSelected();
        bDGame = dGame.isSelected();
        bDSave = dSave.isSelected();
        bDeleteAll = deleteAll.isSelected();
        new PaneNext(rootPane, "fxml/dezenstaler/delete.fxml");
    }

    public void OnDDekctop(ActionEvent actionEvent) {
        allOn();
        if (!dDekctop.isSelected()) {
            deleteAll.setSelected(false);
        }
    }

    public void onDGame(ActionEvent actionEvent) {
        allOn();
        if (!dGame.isSelected()) {
            deleteAll.setSelected(false);
        }
    }

    public void onDSave(ActionEvent actionEvent) {
        allOn();
        if (!dSave.isSelected()) {
            deleteAll.setSelected(false);
        }

    }

    public void onDeleteAll(ActionEvent actionEvent) {
        if (!deleteAll.isSelected()) {
            dDekctop.setSelected(false);
            dGame.setSelected(false);
            dSave.setSelected(false);
            deleteAll.setSelected(false);
        } else {
            dDekctop.setSelected(true);
            dGame.setSelected(true);
            dSave.setSelected(true);
            deleteAll.setSelected(true);
        }
    }

    void allOn() {
        if (dDekctop.isSelected() && dGame.isSelected() && dSave.isSelected()) {
            dDekctop.setSelected(true);
            dGame.setSelected(true);
            dSave.setSelected(true);
            deleteAll.setSelected(true);
        }
    }
}
