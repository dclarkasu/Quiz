package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Quiz;

public class QuizTest {
	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	Quiz q;
	
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
	public void test_Quiz_Connected_to_DB() {
		q = em.find(Quiz.class, 1);
		assertNotNull(q.getName());
		assertEquals(q.getName(), "Quiz #1");
	}
	

}
