package nmt;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		Reader read = new Reader();
		Connection web = new Connection(read.getJsonData());
		//Content cntnt = new Content(web.getContent(), read.getJsonData());
	}

}
