package nmt;

import web.WebApp;

public class Main {

	public static void main(String[] args) throws Exception {
		new DBConnection().deleteOldEntry();
		WebApp.main(args);
		while (true) {
			new Connection(new Reader().getJsonData());
			Thread.sleep(300000);
		}
	}
}
