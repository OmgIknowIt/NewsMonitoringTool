package nmt;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	public Crawler(StringBuffer content, String source, String selectUrl, String selectTitle) throws IOException {
		getContent(content, source, selectUrl, selectTitle);
	}

	private void getContent(StringBuffer htmlCode, String source, String selectUrl, String selectTitle)
			throws IOException {
		Document doc = Jsoup.parse(htmlCode.toString());
		Elements links = doc.select(selectUrl);
		for (Element link : links) {
			Elements title = null;
			if (selectTitle.contains("::")) {
				String[] tokens = selectTitle.split("::");
				title = link.select(tokens[0]);
				String selectNot = tokens[1].replace("not,", "");
				title.select(selectNot).remove();
			} else {
				title = link.select(selectTitle);
			}

			print(" * a: <%s> (%s)", link.attr("abs:href"), title.text());
			DBConnection db = new DBConnection();
			db.createEntry(link.attr("abs:href"), title.text(), source);
		}
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
}
