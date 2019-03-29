package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import nmt.Content;
import nmt.DBConnection;
import nmt.DBReader;
import nmt.Reader;

public class DBConnUnitTest {

	public DBConnection conn;
	public Content newsentry;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDBConnection() {

	}

	@Test
	public void testCreateEntry() {
		DBReader dbreader = new DBReader();
		Reader reader = new Reader();
		String url = "www.mixnews.lv";
		String title = "maza taka";
		String source = "cnnsport.lv";

		conn = new DBConnection();
		newsentry = new Content();
		conn.createEntry(url, title, source);

		List<String> sources = new ArrayList();
		sources.add("tvnet");
		sources.add("delfi");
		sources.add("rus.tvnet");
		sources.add("rus.delfi");

		assertNotNull(dbreader.searchContent(url, sources));
		assertNotNull(dbreader.searchContent(title, sources));
		assertNotNull(dbreader.searchContent(source, sources));
	}

	@Test
	public void testDeleteOldEntry() {

		conn = new DBConnection();

		conn.deleteOldEntry();
		DBReader reader = new DBReader();

		List<String> sources = new ArrayList();
		sources.add("tvnet");
		sources.add("delfi");
		sources.add("rus.tvnet");
		sources.add("CNN");
	}

}
