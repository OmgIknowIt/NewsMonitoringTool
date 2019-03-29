package nmt;

import web.WebApp;

public class Main {

	public static void main(String[] args) throws Exception {
		WebApp.main(args);
		while (true) {
			new Connection(new Reader().getJsonData());
			new DBConnection().deleteOldEntry();
			Thread.sleep(300000);
		}
	}
}
