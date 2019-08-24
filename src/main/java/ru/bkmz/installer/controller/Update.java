package ru.bkmz.installer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import ru.bkmz.installer.util.PaneNext;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Update {
    @FXML
    public Button urlB;
    public Button next;
    public AnchorPane rootPane;

    public void onUelB(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/bkmzli1/InstallerDrizzle/blob/master/build/launch4j/Installer%20drizzle.exe").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void next(ActionEvent actionEvent) {
        new PaneNext(rootPane, "fxml/installer/main.fxml");
    }
}
