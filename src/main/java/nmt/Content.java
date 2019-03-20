package nmt;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "News_Collector")
public class Content {
	@Id
	@GeneratedValue
	private Integer id;
	
	private String url;
	private String title;
	private String source;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

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
		return url;
	}

	public void setURL(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
