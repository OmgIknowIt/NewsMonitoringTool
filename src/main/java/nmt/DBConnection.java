package nmt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DBConnection {
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction entityTransaction;
	
	public DBConnection() {
		emf = Persistence.createEntityManagerFactory("db_news");
		em = emf.createEntityManager();
		entityTransaction = em.getTransaction();
	}
	
	public void createEntry(String URL, String Title, String Source) {
		Content newsEntry = new Content();
		newsEntry.setURL(URL);
		newsEntry.setTitle(Title);
		newsEntry.setSource(Source);
		em.getTransaction().begin();
		em.persist(newsEntry);
		em.getTransaction().commit();
	}
	

	public static void main (String[] args){
		DBConnection conn = new DBConnection();
		conn.createEntry("https://tvnet.lv/", "Jeļena kļuva par miljonāru", "tvnet.lv");
	}
	
}
