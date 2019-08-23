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
import static ru.bkmz.installer.controllerD.MainD.*;

public class Delete {
    static String s = "";
    static String[] files = {"DD\\language", "DD", "media\\Acid.wav", "media\\electric.wav",
            "media\\Hard_kick_drum.wav", "media\\Shield.wav", "media\\sine.mp3", "media\\star.wav",
            "media", ""};
    static float setap = 0;
    static float up = 0;

    public static void run(Text procent, ProgressBar progress, TextArea text) {
        text.setText("");

        if (bDGame) {
            setap += 15 - 4 + 1;
        }
        if (bDSave) {
            setap += 2;
        }
        if (bDDekctop) {
            setap += 2;
        }
        if (bDGame && bDSave) {
            setap += 1;
        }


        if (bDGame) {
            deleteG(procent, progress, text);
        }
        if (bDSave) {
            deleteS(procent, progress, text);
        }
        if (bDDekctop) {
            deleteD(procent, progress, text);
        }
        if (bDGame && bDSave) {
            deleteGS(procent, progress, text);
        }


    }

    static void scet(Text procent, ProgressBar progress, TextArea text) {

        float procents = up / setap;
        procent.setText(procents * 100 + "%");
        progress.setProgress(procents);
        System.out.println(procents);
        up++;

    }

    private static void deleteGS(Text procent, ProgressBar progress, TextArea text) {
        try {
            Files.delete(Paths.get(appdata));
            s += "удалено " + appdata + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
            scet(procent, progress, text);
        } catch (Exception e) {
            s += e + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
            scet(procent, progress, text);
        }
    }

    private static void deleteD(Text procent, ProgressBar progress, TextArea text) {
        try {
            Files.delete(Paths.get(getCurrentUserDesktopPath().replace("à ¡®ç¨© áâ®®«", "рабочий стоол") + "\\drizzle.lnk"));
            s += "удалено " + appdata + "\\drizzle.inf" + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
            scet(procent, progress, text);
        } catch (Exception e) {
            s += e + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
        }
        try {
            Files.delete(Paths.get("C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\drizzle.lnk"));
            s += "удалено " + appdata + "drizzle.inf" + "\n";
            scet(procent, progress, text);
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
        } catch (Exception e) {
            s += e + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
        }
    }

    private static void deleteS(Text procent, ProgressBar progress, TextArea text) {
        String url = appdata;
        try {
            scet(procent, progress, text);
            Files.delete(Paths.get(url + "color.ser"));

        } catch (Exception e) {
            s += e + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
        }
        try {
            scet(procent, progress, text);
            Files.delete(Paths.get(url + "playdata.ser"));

        } catch (Exception e) {
            s += e + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
        }
    }

    static void deleteG(Text procent, ProgressBar progress, TextArea text) {
        String url = null;
        try {
            url = readUsingBufferedReader(appdata + "drizzle.inf");
        } catch (Exception e) {
            s += e + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
            e.printStackTrace();
        }
        try {
            Files.delete(Paths.get(url + "\\drizzle.exe"));
            scet(procent, progress, text);
        } catch (Exception e) {
            s += e + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
        }
        try {
            Files.delete(Paths.get(url));
            scet(procent, progress, text);
        } catch (Exception e) {
            s += e + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
        }
        for (String file :
                files) {
            try {
                Files.delete(Paths.get(appdata + "res\\" + file));
                scet(procent, progress, text);
                s += "удалено " + appdata + "res\\" + file + "\n";
                text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
            } catch (Exception e) {
                s += e + "\n";
                text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
            }
        }
        try {
            Files.delete(Paths.get(appdata + "drizzle.inf"));
            scet(procent, progress, text);
            s += "удалено " + appdata + "drizzle.inf" + "\n";
            text.setText(s.replace("java.nio.file.NoSuchFileException", "не найден"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String readUsingBufferedReader(String fileName) throws Exception {
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
    }
}
