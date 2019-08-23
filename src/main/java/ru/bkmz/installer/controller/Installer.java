package ru.bkmz.installer.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ru.bkmz.installer.util.ImageLoader;
import ru.bkmz.installer.util.PaneNext;

import static ru.bkmz.installer.controller.QuestionOne.installer;

public class Installer {
    public AnchorPane rootPane;
    public Button next;
    public ImageView imeg;
    public Label TL;
    public ProgressBar progress;
    public TextArea text;
    public Text procent;
    boolean error = false;


    public Thread thread = new Thread(() -> {
        procent.setText("0%");
        try {
            installer.run(procent, progress, text);
        } catch (Exception e) {
            String s = text.getText();
            s += "\n" + e.getLocalizedMessage() + "\n";
            text.setText(s);
        }
        next.setDisable(false);
    });


    public void initialize() {
        imeg.setImage(ImageLoader.IMAGE_LOADER.getImage("img/fon"));
        next.setDisable(true);
        try {
            thread.start();
        } catch (Exception e) {
            thread.stop();
            error = true;
            next.setDisable(false);
        }


    }

    public void next(ActionEvent actionEvent) {
        if (error) {
            System.exit(1);
        } else {
            new PaneNext(rootPane, "fxml/installer/finih.fxml");
        }
    }
}
