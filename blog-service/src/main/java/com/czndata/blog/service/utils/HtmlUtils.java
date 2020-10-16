package com.czndata.blog.service.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class HtmlUtils {
    public static List<String> extractDirectory(String html){
        Document document = Jsoup.parse(html);
        return document.select("h3").eachText();
    }
}
