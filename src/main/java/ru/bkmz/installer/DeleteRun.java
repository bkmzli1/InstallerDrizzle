package ru.bkmz.installer;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.bkmz.installer.Installer.getCurrentUserDesktopPath;
import static ru.bkmz.installer.Main.appdata;

public class DeleteRun implements Runnable {
    static String s = "";
    private static String[] filesG, filesS = {"color.ser", "playdata.ser"},
            filesD = {"C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\drizzle.lnk",
                    getCurrentUserDesktopPath().replace("à ¡®ç¨© áâ®®«", "рабочий стоол") + "\\drizzle.lnk"};


    static {
        try {
            filesG = new String[]{"DD\\language", "DD", "media\\Acid.wav", "media\\Acid.mp3", "media\\electric.wav",
                    "media\\Hard_kick_drum.wav", "media\\Shield.wav", "media\\sine.mp3", "media\\star.wav",
                    "media", ""};
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static float setap = 0;
    static float up = 0;
    private final boolean bDDekctop;
    private final boolean bDGame;
    private final boolean bDSave;
    private Text procent;
    private ProgressBar progress;
    private TextArea text;

    public DeleteRun(Text procent, ProgressBar progress, TextArea text, boolean bDGame, boolean bDSave, boolean bDDekctop) {
        this.procent = procent;
        this.progress = progress;
        this.text = text;
        this.bDGame = bDGame;
        this.bDSave = bDSave;
        this.bDDekctop = bDDekctop;
        if (bDSave)
            setap += 2;
        if (bDDekctop)
            setap += 2;
        if (bDGame)
            setap += 14;
        if (bDGame && bDSave)
            setap += 1;
    }

    public void run() {
        if (bDGame) {
            String urlC = readUsingBufferedReader(appdata + "drizzle.inf");
            delete(appdata + "drizzle.inf");
            delete(urlC + "\\drizzle.exe");
            delete(urlC);

            for (String url :
                    filesG) {
                delete(appdata + "res\\" + url);
            }
        }
        if (bDSave) {
            for (String url :
                    filesS) {
                delete(appdata + url);
            }
        }
        if (bDDekctop) {
            for (String url :
                    filesD) {
                delete(url);
            }
        }
        if (bDSave && bDGame) {
            try {
                delete(appdata);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    void addText(String text) {

        s += text + "\n";
        s = s.replace("java.nio.file.NoSuchFileException:","Нет такого файла");
        this.text.setText(s);
    }

    void scet() {
        up++;
        float procents = up / setap;
        procent.setText(procents * 100 + "%");
        progress.setProgress(procents);



    }

    public static String readUsingBufferedReader(String fileName) {
        try {

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = "";
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                ls += line;
            }
            reader.close();
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return ls;
        }catch (Exception e){}
        return null;


    }

    void delete(String url){
        Thread t = new Thread(() -> {
            try {
                Files.delete(Paths.get(url));
                scet();
                addText("удалено: " + url);
            } catch (Exception e) {
                addText("ошибка удоления:" + e);
            }
        }
        );
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            addText("ошибка удоления:" + e);
        }
    }
}
