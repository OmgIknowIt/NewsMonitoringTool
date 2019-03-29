package nmt;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "News_Collector")
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	

	/*public void setId(Integer id) {
		this.id = id;
	}*/

	

	public void setURL(String url) {
		this.url = url;
	}

	

	public void setTitle(String title) {
		this.title = title;
	}



	public void setSource(String source) {
		this.source = source;
	}
}
