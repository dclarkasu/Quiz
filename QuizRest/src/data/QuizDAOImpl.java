package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Question;
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

	@Override
	public Set<Question> showAllQuestions(int quizId) {
		//query gets all properties from Question entity JOINED to Answer table WHERE the Question
		//Entity's Quiz field's id = the id passed in. It returns a list of Questions and their answers
		//thanks to the JOIN Fetch (which prevents lazy loading)
		
		String query = "SELECT q FROM Question q JOIN FETCH q.answers WHERE q.quiz.id = :id";
		List<Question> result = em.createQuery(query, Question.class)
				.setParameter("id", quizId)
				.getResultList();
		
		if (result.size() < 1) {
			query = "SELECT q FROM Question q WHERE q.quiz.id = :id";
			result = em.createQuery(query, Question.class)
					.setParameter("id", quizId)
					.getResultList();
		}
		
		//.getResultList() returns a List but we want a Set.
		//Pass the List into a HashSet constructor which converts it into a HashSet
		
		return new HashSet<Question>(result);
	}

	@Override
	public Question createQuestion(int id, String questJSON) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Question mappedQuestion = mapper.readValue(questJSON, Question.class);
			Quiz quiz = em.find(Quiz.class, id);
			//setting the Quiz object as the non-owning side to the question. Assigning
			//quiz field in Question Class to the found quiz
			mappedQuestion.setQuiz(quiz);
			
			em.persist(mappedQuestion);
			em.flush();
			return mappedQuestion;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean destroyQuestion(int id, int questid) {
		Question quest = em.find(Question.class, questid);
		if (quest.getQuiz().getId() == id) {
			em.remove(quest);
			return true;
		} else {
			return false;
		}
	}

}