package nmt;

import java.io.IOException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Crawler {


	public Crawler(StringBuffer content, String source) throws IOException {
		getContent(content, source);
	}

	private void getContent(StringBuffer htmlCode, String source) throws IOException {
		Document doc = Jsoup.parse(htmlCode.toString());
		Elements links = doc.select("a.list-article__url");
		for (Element link : links) {
			Elements title = link.select("span.list-article__headline");
			title.select(".list-article__comment").remove();
			print(" * a: <%s>  (%s)", link.attr("abs:href"), title.text());
			//TODO: send to DB
			DBConnection db = new DBConnection();
			db.createEntry(link.attr("abs:href"), title.text(), source);
		}
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}


}
