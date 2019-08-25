package ru.bkmz.installer.util;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.bkmz.installer.Main;

import java.io.IOException;
import java.util.Objects;

import static ru.bkmz.installer.Main.nameStage;


public class StageStandart extends Stage {
    public static Stage stage = new Stage();

    public void stage(String name, String css, boolean follScren) {

        FXMLLoader loader = new FXMLLoader();
        loader(loader, name);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setMaximized(follScren);
        Parent root = loader.getRoot();
        stage.setTitle(nameStage + " " + Main.version);
        Scene scene = new Scene(root, 800, 450);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(css)).toExternalForm());
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.exit(0);
            }
        });
        stage.getIcons().add(ImageLoader.IMAGE_LOADER.getImage("img/icon/icon"));
       stage.setResizable(false);
        stage.show();
    }

    void loader(FXMLLoader loader, String name) {
        loader.setLocation(Objects.requireNonNull(getClass().getClassLoader().getResource(name)));
    }
}
