package data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Quiz;

@Transactional
@Repository
public class QuizDAOImpl implements QuizDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Quiz> index() {
		String query = "SELECT q FROM Quiz q";
		return em.createQuery(query, Quiz.class).getResultList();
	}

	@Override
	public Quiz show(int id) {
		return em.find(Quiz.class, id);
	}

	@Override
	public Quiz create(String quizJSON) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Quiz mapQuiz = mapper.readValue(quizJSON, Quiz.class);
			//persist in DB
			em.persist(mapQuiz);
			//ensure updated local copy
			em.flush();
			return mapQuiz;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Quiz update(int id, String quizJSON) {
		ObjectMapper mapper = new ObjectMapper();
		Quiz mappedQuiz = null;
		//make object out of sent String
		try {
			mappedQuiz = mapper.readValue(quizJSON, Quiz.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Find the Quiz obj to update
		Quiz q = em.find(Quiz.class, id);
		//Update that Quiz obj with the values passed in
		q.setName(mappedQuiz.getName());
		return q;
	}

	@Override
	public boolean destroy(int id) {
		//Find obj to delete
		Quiz q = em.find(Quiz.class, id);
		if (q != null && id > 0) {
			em.remove(q);
			return true;
		} else {
			return false;
		}
	}

}