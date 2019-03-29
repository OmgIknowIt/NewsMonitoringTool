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

public class DBReaderUnitTest {
	private DBReader reader;
	private Content newsentry;
	private DBConnection conn;

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
	public void testDBReader() {

	}

	@Test
	public void testSearchContent() {
		List<Object[]> list = new ArrayList();

		// newsentry.setSource(source);

		reader = new DBReader();
		newsentry = new Content();
		conn = new DBConnection();

		conn.createEntry("www.cnn.com", "Good news", "CNN");

		List<String> sources = new ArrayList();
		sources.add("tvnet");
		sources.add("delfi");
		sources.add("rus.tvnet");
		sources.add("CNN");

		String t = "news";
		list.addAll(reader.searchContent(t, sources));

		int actual = list.size();
		int expect = 1;

		assertEquals(expect, actual);

		assertNotNull(reader.searchContent(t, sources));
		;
	}

}
