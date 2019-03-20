package nmt;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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
		JSONParser data = new JSONParser();
        try {FileReader reader = new FileReader(
        		"/home/student/git/NewsMonitoringTools/src/main/java/nmt/source_urls.json");
        
            //Read JSON file
            Object obj = data.parse(reader);
            
            hostList = (JSONArray) obj;
             
            //hostList.forEach( emp -> parseData( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	public JSONArray getJsonData(){
		return hostList;
	}
	
//    private static void parseData(JSONObject data)
//    {
//    	String host = (String) data.get("host");
//        JSONArray urls = (JSONArray) data.get("url_list");
//
//        System.out.println("Name: " + host);
//        System.out.println("\nUrls:");
//        Iterator<String> iterator = urls.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//    }
}
