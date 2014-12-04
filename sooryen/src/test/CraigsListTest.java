package test;
import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import com.sooryen.swchoe.CraigsListIndexer;
import com.sooryen.swchoe.model.Item;


public class CraigsListTest extends TestCase{

	@Test
	public void testInsertToDatabase() {
		Item item = new Item("this is a new title", "$55");
		CraigsListIndexer.insertToDatabase(item);
		fail("Not yet implemented");
	}

}
