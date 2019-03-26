package nmt;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import web.WebApp;

public class Main {

	public static void main(String[] args) throws Exception {
		WebApp.main(args);
		new Connection(new Reader().getJsonData());
		//new DBConnection().deleteOldEntry();
	}

}
