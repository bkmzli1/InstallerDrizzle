package ru.bkmz.installer.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Web {
    static void pars() throws IOException {
        Document document = Jsoup.connect("").get();
    }
}
