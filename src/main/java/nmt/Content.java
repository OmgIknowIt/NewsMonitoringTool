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

@Entity
@Table(name = "News_Collector")
public class Content {
	@Id
	@GeneratedValue
	private Integer id;

	private String URL;
	private String Title;
	private String Source;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public Content(StringBuffer content, String source) throws IOException {
		getContent(content);
		this.Source = source;
	}

	private void getContent(StringBuffer content) throws IOException {
		Document doc = Jsoup.parse(content.toString());
		Elements links = doc.select("a.list-article__url");
		for (Element link : links) {
			Elements title = link.select("span.list-article__headline");
			title.select(".list-article__comment").remove();
			print(" * a: <%s>  (%s)", link.attr("abs:href"), title.text());
			//TODO: send to DB
			
		}
	}

	private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}

	@PrePersist
	protected void onPrePersist() {
		date = new Date();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getSource() {
		return Source;
	}

	public void setSource(String source) {
		Source = source;
	}
}
