package ru.bkmz.installer.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class PaneNext {
    public PaneNext(AnchorPane rootPane, String name) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getClassLoader().getResource(name));
            rootPane.getChildren().setAll(pane);
        } catch (Exception e) {
        }

    }
}
