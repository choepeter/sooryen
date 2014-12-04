package com.sooryen.swchoe;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CraigsListExtractor {
	public static Elements getElementsByClass(Document doc, String className){
		Elements elements = doc.getElementsByClass(className);
		return elements;
	}
	
	public static Document getDocument(String url) throws IOException{
		Document document = Jsoup.connect(url).get();
		return document;
	}
	
	public static String getText(Element element, String className){
		String text = new String();
		Elements elements = element.select("." + className);
		if(elements.size() == 0) return "";
		text = elements.get(0).text();
		return text;
	}
}
