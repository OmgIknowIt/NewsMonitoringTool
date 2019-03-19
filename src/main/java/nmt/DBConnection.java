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
		emf = Persistence.createEntityManagerFactory("h2-local"); // DB name needs to be included
		em = emf.createEntityManager();
		entityTransaction = em.getTransaction();
	}
	
	public void createEntry(Integer id, String URL, String Title, String Source) {
		Content newsEntry = new Content();
		newsEntry.setId(id);
		newsEntry.setURL(URL);
		newsEntry.setTitle(Title);
		newsEntry.setSource(Source);
		
		em.persist(newsEntry);
	}
	
}
