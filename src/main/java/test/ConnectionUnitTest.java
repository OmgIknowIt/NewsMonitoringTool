package test;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import nmt.Connection;
import nmt.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ConnectionUnitTest {

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
	public void testConnection() {
		
		StringBuffer htmlCode = null;
		Connection con = null;
		
		JSONObject jo = new JSONObject();
		jo.put("host" , "tvnet.lv");
		jo.put("url", "a.list-article__url");
		jo.put("title", "span.list-article__headline :: not,.list-article__comment");
		jo.put("url_list", "https://www.tvnet.lv/section/4235");
		
		JSONArray jsonData = new JSONArray();
		jsonData.add(jo);
	 
				
				
				
		try {
			con = new Connection(new Reader().getJsonData());
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		
		assertNotNull(con);
		
		
	}

}
