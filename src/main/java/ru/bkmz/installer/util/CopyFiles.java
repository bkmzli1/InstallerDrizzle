package ru.bkmz.installer.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static ru.bkmz.installer.Main.appdata;
import static ru.bkmz.installer.controller.QuestionOne.urls;

public class CopyFiles {
    private static String urlout;

    public static void failCopi(String url, String fileName) throws IOException {
        File f = new File(appdata);
        f.mkdir();
        if (!fileName.equals("drizzle.exe")) {
            System.out.println("loading jar:" + url + fileName);
            fileResources(appdata + "res");
            fileResources(appdata + "res/" + url);

            InputStream inpStream = CopyFiles.class.getClassLoader().getResourceAsStream(url + fileName);
            Path target = Paths.get(urlout + fileName);
            System.out.println(urlout + fileName);
            assert inpStream != null;

                Files.copy(inpStream, target, REPLACE_EXISTING);

            try {
                inpStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (fileName.equals("drizzle.exe")) {
            InputStream inpStream = CopyFiles.class.getClassLoader().getResourceAsStream(url + fileName);
            File file = new File(urls);
            System.out.println(file);
            file.mkdir();
            Path target = Paths.get(urls + "\\" + fileName);
            assert inpStream != null;

                Files.copy(inpStream, target, REPLACE_EXISTING);

            try {
                inpStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void fileResources(String name) {
        File file = new File(name);
        urlout = name;
        if (!file.exists()) {
            file.mkdir();
        }
    }


}