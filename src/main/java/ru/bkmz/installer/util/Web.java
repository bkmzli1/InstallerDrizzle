package ru.bkmz.installer.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import ru.bkmz.installer.Main;

public class Web {
    public boolean error = false;
    String v;
    public static  String lA="";

    public static void main(String[] args) {
        Web web = new Web();
        System.out.println(web.licenseAgreement());
    }
    public String parsV() {
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
    public String licenseAgreement(){
        Document document = null;
        try {
            document = Jsoup.connect("https://github.com/bkmzli1/InstallerDrizzle/blob/master/license%20agreement.txt").get();
        } catch (Exception e) {
            error = true;
        }
        Elements elements = document.getElementsByAttributeValue("class", "blob-code blob-code-inner js-file-line");
        elements.forEach(element -> {
            lA += element.text()+"\n";
        });
        return lA;
    }
}
