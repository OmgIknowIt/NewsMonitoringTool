package nmt;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		new Connection(new Reader().getJsonData());
	}

}
