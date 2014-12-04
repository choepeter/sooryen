package com.sooryen.swchoe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sooryen.swchoe.model.Item;

public class CraigsListIndexer {
	private static SessionFactory factory;

	public static void indexItem(Item item) throws SolrServerException,
			IOException {
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

	public static int insertToDatabase(Item item) {
		Transaction tx = null;
		Integer itemID = null;
		Session session = null;
		try {
			factory = new Configuration().configure().buildSessionFactory();
			session = factory.openSession();
			tx = session.beginTransaction();
			itemID = (Integer) session.save(item);
			tx.commit();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		} finally {
			session.close();
		}
		return itemID;
	}
}
