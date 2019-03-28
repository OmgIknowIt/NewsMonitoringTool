package nmt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Reader {

	private JSONArray hostList;

	public Reader() {
		super();
		read();
	}

	@SuppressWarnings("unchecked")
	private void read() {
		try {
			URL path = Reader.class.getResource("source_urls.json");
			File file = new File(path.getFile());
			JSONParser data = new JSONParser();
			FileReader reader = new FileReader(file);
			Object obj = data.parse(reader);
			hostList = (JSONArray) obj;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public JSONArray getJsonData() {
		return hostList;
	}
}
