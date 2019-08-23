package ru.bkmz.installer;


import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import ru.bkmz.installer.controller.QuestionOne;
import ru.bkmz.installer.util.CopyFiles;

import java.io.*;
import java.util.ArrayList;

import static ru.bkmz.installer.ShortcutBuilder.sBuilder;
import static ru.bkmz.installer.controller.QuestionOne.urls;

public class Installer {
    boolean desctop = false;
    boolean pysk = false;
    static boolean error = false;
    float setap = 8;
    float up = 0;
    public static ArrayList<File> fileListD = new ArrayList<File>();

    public Installer(boolean desctop, boolean pysk) {
        this.desctop = desctop;
        this.pysk = pysk;

    }

    void scet(Text procent, ProgressBar progress, TextArea text) {

        float procents =  up/setap;
        procent.setText(procents*100+"%");
        progress.setProgress(procents);
        System.out.println(procents);
        up++;

    }

    public void run(Text procent, ProgressBar progress, TextArea text) throws Exception {

        if (desctop) {
            setap++;
        }
        if (pysk) {
            setap++;
        }
        scet(procent, progress, text);
        String s = "Установка запущена...\n";
        text.setText(s);
        s += "Начета извлечение музыки\n";

        text.setText(s);
        try {
            setapOne(procent, progress, text);
            s += "Извлечение музыки завершино\n";
            text.setText(s);
            s += "Начета извлечение БД\n";
            text.setText(s);


        } catch (Exception e) {
            error = true;
            s += e.toString() + "\n";
            text.setText(s);
        }
        try {
            setapTwo(procent, progress, text);
            s += "Извлечение БД завершино\n";
            text.setText(s);

        } catch (Exception e) {
            error = true;
            s += e.toString() + "\n";
            text.setText(s);
        }
        s += "Начета извлечение exe\n";
        text.setText(s);

        try {
            setapThrre(procent, progress, text);
            s += "Извлечение exe завершино\n";
            text.setText(s);
        } catch (Exception e) {
            error = true;
            s += e.toString() + "\n";
            text.setText(s);
        }
        if (desctop) {
            try {
                s += "Начето создание ярлыка на рабочий стол\n";
                text.setText(s);
                sBuilder(urls + "\\drizzle.exe", getCurrentUserDesktopPath().replace("à ¡®ç¨© áâ®®«", "рабочий стоол"), "desktop");
                s += "закончино создание ярлыка на рабочий стол\n";
                text.setText(s);
                scet(procent, progress, text);

            } catch (Exception e) {
                error = true;
                s += e.toString() + "\n";
                text.setText(s);
            }
        }
        if (pysk) {
            try {
                s += "Начето создание ярлыка в пуск\n";
                text.setText(s);
                sBuilder(urls + "\\drizzle.exe", "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs", "pysk");
                s += "закончино создание ярлыка в пуск\n";
                text.setText(s);
                scet(procent, progress, text);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (error) {
            s += "В процессе установки была ошибка!\n" +
                    "При запуске игра попытается автоматически устранить проблемы если проблемы небыли устронены то отправте писмо на почту bkmzlitel@gmail.com";
            text.setText(s);
        }

    }

    private void setapOne(Text procent, ProgressBar progress, TextArea text) throws Exception {
        CopyFiles.failCopi("media/", "sine.mp3");
        scet(procent, progress, text);

        CopyFiles.failCopi("media/", "Acid.wav");
        scet(procent, progress, text);

        CopyFiles.failCopi("media/", "electric.wav");
        scet(procent, progress, text);

        CopyFiles.failCopi("media/", "Shield.wav");
        scet(procent, progress, text);

        CopyFiles.failCopi("media/", "star.wav");
        scet(procent, progress, text);

        CopyFiles.failCopi("media/", "Hard_kick_drum.wav");
        scet(procent, progress, text);

    }

    private void setapTwo(Text procent, ProgressBar progress, TextArea text) throws Exception {
        CopyFiles.failCopi("DD/", "language");
        scet(procent, progress, text);

    }

    private void setapThrre(Text procent, ProgressBar progress, TextArea text) throws Exception {
        CopyFiles.failCopi("exe/", "drizzle.exe");
        scet(procent, progress, text);
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
            error = true;
            return null;
        }
    }
}

class StreamReader extends Thread {
    private InputStream is;
    private StringWriter sw;

    StreamReader(InputStream is) {
        this.is = is;
        sw = new StringWriter();
    }

    public void run() {
        try {
            int c;
            while ((c = is.read()) != -1)
                sw.write(c);
        } catch (IOException e) {
        }
    }

    String getResult() {
        return sw.toString();
    }
}
