package test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Question;
import entities.Quiz;

public class QuestionTest {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	Question q;
	
	@Before
	public void setUp() {
		emf = Persistence.createEntityManagerFactory("Quiz");
		em = emf.createEntityManager();
	}
	
	@After
	public void tearDown() {
		em.close();
		emf.close();

	}
	
	@Test
	public void test_Smoke() {
		boolean pass = true;
		assertEquals(pass, true);
	}
	
	@Test
	public void test_Question_Connected_to_DB() {
		q = em.find(Question.class, 1);
		assertNotNull(q.getQuestionText());
		assertEquals(q.getId(), 1);
		assertEquals(q.getAnswers().size(), 4);
	}
	//Finds Question Entity from id then retrieves Quiz field to test
	@Test
	public void test_Mapping_To_Quiz() {
		q = em.find(Question.class, 1);
//		Quiz quiz = q.getQuiz(); Can method chain like below or create an instance to test on
		assertNotNull(q.getQuiz());
		assertEquals(q.getQuiz().getName(), "States");
	}
}
