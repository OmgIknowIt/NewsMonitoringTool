package nmt;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

public class Crawler {

	public Crawler(StringBuffer content, String source, String selectUrl, String selectTitle) throws IOException {
		getContentHTML(content, source, selectUrl, selectTitle);
	}

	public Crawler(SyndFeed content, String source) throws IOException {
		getContentFeed(content, source);
	}

	private void getContentHTML(StringBuffer htmlCode, String source, String selectUrl, String selectTitle)
			throws IOException {
		Document doc = Jsoup.parse(htmlCode.toString());
		Elements links = null;
		String[] tokens;
		if (selectUrl.contains("::")) {
			tokens = selectUrl.split("::");
			for (String token : tokens) {
				links = doc.select(token);
				if (links.isEmpty()) {
					continue;
				}
			}
		} else {
			links = doc.select(selectUrl);
		}
		for (Element link : links) {
			Elements title = null;
			if (selectTitle.contains("::")) {
				tokens = selectTitle.split("::");
				title = link.select(tokens[0]);
				String selectNot = tokens[1].replace("not,", "");
				title.select(selectNot).remove();
			} else {
				title = link.select(selectTitle);
			}
			DBConnection db = new DBConnection();
			db.createEntry(link.attr("abs:href"), title.text(), source);
			db.em.close();
			db.emf.close();
		}
	}

	private void getContentFeed(SyndFeed feed, String source) {
		try {
			for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {
				DBConnection db = new DBConnection();
				db.createEntry(entry.getLink(), entry.getTitle(), source);
				db.em.close();
				db.emf.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
