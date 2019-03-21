package nmt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBConnection {
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction entityTransaction;
	
	// initializing entityManager
	public DBConnection() {
		emf = Persistence.createEntityManagerFactory("db_news");
		em = emf.createEntityManager();
		entityTransaction = em.getTransaction();
	}
	
	// Creates entries into DB
	public void createEntry(String URL, String Title, String Source) {
		Content newsEntry = new Content();
		newsEntry.setURL(URL);
		newsEntry.setTitle(Title);
		newsEntry.setSource(Source);
		
		// Check if entry already exists
		entityTransaction.begin();		
		Query query = em.createQuery("Select url from Content c where url = '" + URL + "'");
		entityTransaction.commit();	
		
		// Persist entry if doesn't exist
		if (query.getResultList().size() == 0) {
			entityTransaction.begin();
			em.persist(newsEntry);
			entityTransaction.commit();
		}

	}
	
}
