package ru.bkmz.installer;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.bkmz.installer.util.ImageLoader;
import ru.bkmz.installer.util.StageStandart;
import ru.bkmz.installer.util.Web;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static final String nameStage = "Installer drizzle".toUpperCase();
    public static final String appdata = System.getenv("APPDATA") + "\\.drizzle\\";
    private static final String[] filesAll = new String[]{""};
    public static Stage stage;
    private String version = "1.1.1.1";

    @Override
    public void start(Stage primaryStage) throws IOException {
        Web web = new Web();

        StageStandart stageStandart = new StageStandart();
        File file = new File(appdata + "drizzle.inf");
        if (!file.exists()) {
            if (!version.equals(web.pars()) && !web.error) {
                stageStandart.stage("fxml/updete/up.fxml", "css/main.css", false);
            } else {
                stageStandart.stage("fxml/installer/main.fxml", "css/main.css", false);
            }

        } else {
            stageStandart.stage("fxml/dezenstaler/main.fxml", "css/main.css", false);
        }
        stage = StageStandart.stage;
    }


    @Override
    public void init() throws Exception {
        ImageLoader.IMAGE_LOADER.preferExternalSources(true);
        ImageLoader.IMAGE_LOADER.setCommonSuffix(".png");
        ImageLoader.IMAGE_LOADER.loading("img/fon");
        ImageLoader.IMAGE_LOADER.loading("img/icon/icon");

    }


}
