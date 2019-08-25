package ru.bkmz.installer.controllerD;


import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ru.bkmz.installer.DeleteRun;
import ru.bkmz.installer.util.PaneNext;

import java.io.File;

import static ru.bkmz.installer.Installer.fileListD;
import static ru.bkmz.installer.controllerD.MainD.*;


public class Delete {
    public AnchorPane rootPane;
    public Button next;
    public ImageView imeg;
    public Label TL;
    public ProgressBar progress;
    public TextArea text;
    public Text procent;
    boolean error = false;


    Thread thread;


    public void initialize() {
        try {
            next.setDisable(true);
            thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    DeleteRun deleteRun = new DeleteRun(procent, progress, text, bDGame, bDSave, bDDekctop);
                    try {
                        deleteRun.run();
                    } catch (Exception e) {
                        text.setText(text.getText() + "\n" + e);
                        next.setDisable(false);
                    }
                    next.setDisable(false);
                }
            });

            thread.start();
        } catch (Exception e) {
            text.setText(text.getText() + "\n" + e);
            next.setDisable(false);
        }


    }

    public void next(ActionEvent actionEvent) {
        for (File f : fileListD) {
            f.delete();
        }
        new PaneNext(rootPane, "fxml/dezenstaler/finih.fxml");

    }
}
