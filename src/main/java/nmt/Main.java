package nmt;
import web.WebApp;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws Exception {
		WebApp.main(args);
		new Connection(new Reader().getJsonData());
		new DBConnection().deleteOldEntry();
		
	}

}
