package nmt;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DBReader {

	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction entityTransaction;
	
	public DBReader() {
		emf = Persistence.createEntityManagerFactory("db_news");
		em = emf.createEntityManager();
		entityTransaction = em.getTransaction();
	}
	
	public List<Object[]> searchContent(String tags) {
		List<Object[]> list = new ArrayList();
		String[] arr = tags.split(" ");    
		
		for (String s:arr) {
			
			entityTransaction.begin();		
			Query query = em.createQuery("Select url, title, source from Content c where title like '%" + s + "%'");
			entityTransaction.commit();			
			list.addAll((query.getResultList()));
			
		}
		return list;
	}
	
	
}
