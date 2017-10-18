package test;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Question;
import entities.Answer;

public class AnswerTest {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	Answer a;
	
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
	public void test_Answer_Connected_to_DB() {
		a = em.find(Answer.class, 1);
		assertEquals(a.getId(), 1);
		assertEquals(a.getAnswerText(), "Deleware");
	}
	//Finds the Answer Entity then retrieves its Question field to test on
	@Test
	public void test_Many_To_One_With_Question() {
		a = em.find(Answer.class, 1);
		assertEquals(a.getQuestion().getQuestionText(), "What is the smallest state in the US");
		assertEquals(a.getQuestion().getAnswers().size(), 4);
	}
}
