package nmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Connection {

	public Connection(JSONArray jsonData) throws IOException {
		super();
		httpConnection(jsonData);
	}

	private void httpConnection(JSONArray jsonData) throws IOException {
		StringBuffer htmlCode = null;
		HttpURLConnection con = null;
		BufferedReader in = null;
		InputStreamReader streamReader = null;
		for (int i = 0; i < jsonData.size(); i++) { // we have multiple websites
													// with links
			// let's take links from array
			JSONObject obj = (JSONObject) jsonData.get(i);
			JSONArray urls = (JSONArray) obj.get("url_list");
			Iterator<String> iterator = urls.iterator();
			//
			while (iterator.hasNext()) { // if we need parse more than one link
											// from website
				// connection itself
				URL url = new URL(iterator.next());
				con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				//
				// Reading the Response on Failed Requests
				int status = con.getResponseCode();
				if (status > 299) {
					streamReader = new InputStreamReader(con.getErrorStream());
				} else {
					streamReader = new InputStreamReader(con.getInputStream());
				}
				in = new BufferedReader(streamReader);
				String inputLine;
				htmlCode = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					htmlCode.append(inputLine);
				}
				//
				new Crawler(htmlCode, (String) obj.get("host"), (String) obj.get("url"), (String) obj.get("title"));
			}
			in.close(); // close BufferedReader
			con.disconnect(); // close HttpURLConnection
		}
	}

}
