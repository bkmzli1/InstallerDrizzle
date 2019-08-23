package ru.bkmz.installer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static ru.bkmz.installer.Installer.fileListD;

public class ShortcutBuilder {
    public static void sBuilder(String urlIn, String urlOut,String temp) throws Exception  {

        String script = "Set sh = CreateObject(\"WScript.Shell\")"
                + "\nSet shortcut = sh.CreateShortcut(\"" + urlOut + "/drizzle.lnk\")"
                + "\nshortcut.TargetPath = \"" + urlIn + "\""
                + "\nshortcut.Save";

        File file = new File(Main.appdata + "/"+temp+".vbs");


            FileOutputStream fo = new FileOutputStream(file);
            fo.write(script.getBytes());
            fo.close();
            fileListD.add(file);
            Runtime.getRuntime().exec("wscript.exe " + file.getAbsolutePath());

    }
}
