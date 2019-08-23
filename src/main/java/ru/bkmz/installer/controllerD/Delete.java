package ru.bkmz.installer.controllerD;


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


public class Delete {
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
            ru.bkmz.installer.Delete.run(procent, progress, text);
        } catch (Exception e) {
            text.setText(text.getText() +
                    "\n=========================================\n" +
                    e +
                    "\n=========================================");
        }


        next.setDisable(false);
    });


    public void initialize() {
        text.setText(" ");
        imeg.setImage(ImageLoader.IMAGE_LOADER.getImage("img/fon_d"));
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
            new PaneNext(rootPane, "fxml/dezenstaler/finih.fxml");
        }
    }
}
