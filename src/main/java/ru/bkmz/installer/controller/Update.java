package ru.bkmz.installer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ru.bkmz.installer.Main;
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
    public Text oldV;
    public Text newV;

    public void initialize() {
        oldV.setText(Main.version);
        newV.setText(Main.newVersion);
    }

    public void onUelB(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL("https://github.com/bkmzli1/InstallerDrizzle/blob/master/versions/Installer%20drizzle.exe").toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public void next(ActionEvent actionEvent) {
        new PaneNext(rootPane, "fxml/installer/main.fxml");
    }
}
