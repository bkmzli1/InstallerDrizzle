package ru.bkmz.installer;


import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import ru.bkmz.installer.controller.QuestionOne;
import ru.bkmz.installer.util.CopyFiles;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

import static ru.bkmz.installer.util.ShortcutBuilder.sBuilder;

public class Installer implements Runnable {
    boolean desctop = QuestionOne.d;
    boolean pysk = QuestionOne.p;
    String s = "";
    TextArea text;
    float setap;
    float up = 0;
    String urlInnstaller = QuestionOne.urls;
    static boolean isError = false;
    public static ArrayList<File> fileListD = new ArrayList<File>();
    protected final String[] game = {"exe/drizzle.exe", "media/Acid.mp3", "media/Acid.wav", "media/electric.wav",
            "media/Hard_kick_drum.wav", "media/Shield.wav", "media/sine.mp3", "media/star.wav", "DD/language"
    };
    private Text procent;
    private ProgressBar progress;

    public Installer(Text procent, ProgressBar progress, TextArea text) {
        this.text = text;
        this.procent = procent;
        this.progress = progress;

        setap = 28;
        if (desctop) {
            setap++;
        }
        if (pysk) {
            setap++;
        }
    }

    void scet() {
        up++;
        float procents = up / setap;
        procent.setText(procents * 100 + "%");
        progress.setProgress(procents);
        System.out.println(procents);


    }

    public void run() {

        procent.setText("0%");
        addText("Начита подготовка к установке");
        ArrayList<String[]> files = new ArrayList<String[]>();
        for (String f :
                game) {
            files.add(f.split("/"));
        }
        ArrayList<String> file = new ArrayList<String>();
        for (String[] sm :
                files) {
            for (String name :
                    sm) {
                scet();
                file.add(name);
            }
        }

        addText("Подготовка завершина");

        addText("Начето извлечение файлов");
        for (int i = 0; i < file.size() - 1; i = i + 2) {

            scet();
            try {
                try {
                    CopyFiles.failCopi(file.get(i) + "/", file.get(i + 1));
                    addText("Извлечено "+file.get(i) + "/" + file.get(i + 1));
                } catch (NoSuchFileException e) {
                    addText("Отказ доступа " + file.get(i) + "/" + file.get(i + 1));
                }

            } catch (IOException e) {
            }

            if (file.get(i + 1).equals("drizzle.exe")) {

                label();

            }

        }


        if (!isError) {
            addText("Установка завершина!");
        } else {
            addText("В процессе установки произошла ошибка");
            addText("При запуске игры будут запущены протоколы их исправления");
            addText("Если протоколы испровление ошибак несмогут их истранить обротитесь по электронной почте blmzlitel@gmail.com");
        }

        scet();
        try {
            FileWriter fileWriter = new FileWriter(Main.appdata + "drizzle.inf");
            fileWriter.write(urlInnstaller);
            fileWriter.close();
            Thread.sleep(10);
        } catch (Exception e) {

        }

    }

    void label() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            addText("ошибка извлечения:" + e);
        }
        if (desctop) {
            try {

                addText("Начето создание ярлыка на рабочий стол");
                sBuilder(urlInnstaller + "\\drizzle.exe", getCurrentUserDesktopPath().replace("à ¡®ç¨© áâ®®«", "рабочий стоол"), "desktop");
                addText("Закончино создание ярлыка на рабочий стол");
                scet();
            } catch (Exception e) {
                isError = true;
                addText("ошибка создания ярлыка:" + e);
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            addText("ошибка извлечения:" + e);
        }
        if (pysk) {
            try {
                addText("Начето создание ярлыка в пуск");
                sBuilder(urlInnstaller + "\\drizzle.exe", "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs", "pysk");
                addText("Закончино создание ярлыка в пуск");
                scet();
            } catch (Exception e) {
                isError = true;
                addText("ошибка создания ярлыка:" + e);
            }
        }
    }

    void addText(String text) {

        s += text + "\n";
        this.text.setText(s);
    }

    private static final String REGQUERY_UTIL = "reg query ";
    private static final String REGSTR_TOKEN = "REG_SZ";
    private static final String DESKTOP_FOLDER_CMD = REGQUERY_UTIL
            + "\"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\"
            + "Explorer\\Shell Folders\" /v DESKTOP";

    public static String getCurrentUserDesktopPath() {
        try {
            Process process = Runtime.getRuntime().exec(DESKTOP_FOLDER_CMD);
            StreamReader reader = new StreamReader(process.getInputStream());

            reader.start();
            process.waitFor();
            reader.join();
            String result = reader.getResult();
            int p = result.indexOf(REGSTR_TOKEN);

            if (p == -1) return null;
            return result.substring(p + REGSTR_TOKEN.length()).trim();
        } catch (Exception e) {
            isError = true;
            return null;
        }
    }
}

class StreamReader extends Thread {
    private InputStream is;
    private StringWriter sw;

    StreamReader(InputStream is) throws Exception {
        this.is = is;
        sw = new StringWriter();
    }

    public void run() {
        try {
            int c;
            while ((c = is.read()) != -1)
                sw.write(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String getResult() throws Exception {
        return sw.toString();
    }
}