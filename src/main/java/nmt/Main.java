package nmt;

import java.io.IOException;

import web.WebApp;

public class Main {

	public static void main(String[] args) throws Exception {
		new Connection(new Reader().getJsonData());
		WebApp.main(args);
	}

}
