package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import web.WebController;
import org.springframework.ui.Model;

public class webContrUnittest {

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
	public void testHomePage() {
		String tags = "tag";
		String tvnet = "tvnet";
		String delfi = "delfi";
		String rusTvnet = "rus.tvnet";
		String rusDelfi = "rus.delfi";
		Model model=null;
		
		
			
		
		
		WebController webcontroller = new WebController();
		
				
		webcontroller.homePage(tags, tvnet, delfi, rusTvnet, rusDelfi, model);
	}

}
