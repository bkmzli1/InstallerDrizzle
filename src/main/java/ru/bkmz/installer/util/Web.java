package ru.bkmz.installer.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Web {
    public boolean error = false;
    String v;

    public String pars() {
        Document document = null;
        try {
            document = Jsoup.connect("https://github.com/bkmzli1/InstallerDrizzle/blob/master/version.txt").get();
        } catch (Exception e) {
            error = true;
        }
        Elements elements = document.getElementsByAttributeValue("class", "blob-code blob-code-inner js-file-line");
        elements.forEach(element -> {
            v = element.text();
        });
        return v;
    }
}
