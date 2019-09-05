package ru.bkmz.installer;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.bkmz.installer.util.ImageLoader;
import ru.bkmz.installer.util.StageStandart;
import ru.bkmz.installer.util.Web;

import java.io.File;



public class Main extends Application {

    public static final String nameStage = "InstallerController drizzle".toUpperCase();
    public static final String appdata = System.getenv("APPDATA") + "\\.drizzle\\";
    private static final String[] filesAll = new String[]{""};
    public static Stage stage;
    public static String version = "1.1.1.8";
    public static String versionD = "v3.10.3";
    public static String newVersion;
    public static String newLA;
    private static Web web = new Web();

    @Override
    public void start(Stage stage)  {


        StageStandart stageStandart = new StageStandart(stage);
        File file = new File(appdata + "drizzle.inf");
        if (!file.exists()) {
            newVersion = web.parsV();
            newLA = web.licenseAgreement();
            if (!version.equals(newVersion) && !web.error) {
                stageStandart.stage("fxml/updete/up.fxml", "css/main.css", false);
            } else {
                stageStandart.stage("fxml/installer/main.fxml", "css/main.css", false);
            }

        } else {
            stageStandart.stage("fxml/dezenstaler/main.fxml", "css/main.css", false);
        }

    }


    @Override
    public void init()  {
        ImageLoader.IMAGE_LOADER.preferExternalSources(true);
        ImageLoader.IMAGE_LOADER.setCommonSuffix(".png");
        ImageLoader.IMAGE_LOADER.loading("img/fon");
        ImageLoader.IMAGE_LOADER.loading("img/icon/icon");

    }


}
