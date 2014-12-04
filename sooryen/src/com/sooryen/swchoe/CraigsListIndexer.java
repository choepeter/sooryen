package com.sooryen.swchoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

import com.sooryen.swchoe.model.Item;

public class CraigsListIndexer {

	public static void indexItem(Item item) throws SolrServerException, IOException {
		SolrServer server = new HttpSolrServer("http://localhost:8080/solr/");
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", item.hashCode());
		doc.addField("title", item.getTitle());
		doc.addField("price", item.getPrice());
		Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
		docs.add(doc);
		server.add(docs);
		server.commit();
	}
}
