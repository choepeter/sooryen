package com.sooryen.swchoe;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sooryen.swchoe.model.Item;

public class CraigsListProject {

	public static void main(String[] args) throws IOException, SolrServerException {
		// TODO Auto-generated method stub
		for (int i = 0; i < 10; i++) {
			Document doc = CraigsListExtractor
					.getDocument("http://newyork.craigslist.org/search/bka?s="
							+ (i * 100));
			Elements items = CraigsListExtractor.getElementsByClass(doc, "row");
			for(Element item : items){
				Item it = new Item(CraigsListExtractor.getText(item, "hdrlnk"), CraigsListExtractor.getText(item, "price"));
				CraigsListIndexer.indexItem(it);
				//CraigsListIndexer.insertToDatabase(it);
			}
		}

	}

}
