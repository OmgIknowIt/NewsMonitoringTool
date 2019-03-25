package nmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xml.sax.InputSource;

import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

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
		SyndFeed feed = null;
	    InputStream is = null;
		for (int i = 0; i < jsonData.size(); i++) { // we have multiple websites
													// with links
			// let's take links from array
			JSONObject obj = (JSONObject) jsonData.get(i);
			JSONArray urls = (JSONArray) obj.get("url_list");
			Iterator<String> iterator = urls.iterator();
			//
			while (iterator.hasNext()) { // if we need parse more than one link
											// from website
				String[] token = iterator.next().split("::");
				String whereToGo = token[1].trim();
				String link = token[0].trim();
				if (whereToGo.equals("url")) {
					// connection itself
					URL url = new URL(link);
					con = (HttpURLConnection) url.openConnection();
					con.setRequestMethod("GET");
					con.setConnectTimeout(8000);
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
					new Crawler(htmlCode, (String) obj.get("host"), (String) obj.get("url"), (String) obj.get("title"));
				} else if (whereToGo.equals("rss")) {
				    try {
				        URLConnection openConnection = new URL(link).openConnection();
				        is = new URL(link).openConnection().getInputStream();
				        if("gzip".equals(openConnection.getContentEncoding())){
				            is = new GZIPInputStream(is);
				        }
				        InputSource source = new InputSource(is);
				        SyndFeedInput input = new SyndFeedInput();
				        feed = input.build(source);
						new Crawler(feed, (String) obj.get("host"));
				    } catch (Exception e){
				        e.printStackTrace();
				    }
				}
				//
			}
			if (is != null) is.close(); // close InputStream
			if (in != null) in.close(); // close BufferedReader
			if (in != null) con.disconnect(); // close HttpURLConnection
		}
	}

}
