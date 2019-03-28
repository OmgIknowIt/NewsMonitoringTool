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

	@SuppressWarnings("unchecked")
	public List<Object[]> searchContent(String tags, List<String> sources) {
		List<Object[]> list = new ArrayList<Object[]>();
		String[] arr = tags.split(" ");
		
		for (String tag : arr) {

			String queryString = "Select url, title, source from Content c where title like :tag and (";
			for (int i = 0; i < sources.size(); i++) {
				queryString += "source =:source" + i + " or ";
			}			
			queryString = queryString.substring(0, queryString.length() - 4) + ")";
				
			entityTransaction.begin();
			Query query = em.createQuery(queryString);
			query.setParameter("tag", "%" + tag + "%");
			for (int i = 0; i < sources.size(); i++) {
				query.setParameter("source" + i, sources.get(i));
			}
			entityTransaction.commit();
			list.addAll((query.getResultList()));

		}

		return list;
	}

}

