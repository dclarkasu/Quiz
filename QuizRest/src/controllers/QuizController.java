package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.QuizDAO;
import entities.Quiz;

@RestController
public class QuizController {
	
	@Autowired
	private QuizDAO quizdao;
	
	@RequestMapping(path="ping", method=RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path="quizzes", method=RequestMethod.GET)
	public List<Quiz> index() {
		return quizdao.index();
	}
	
	@RequestMapping(path="quizzes/{id}", method=RequestMethod.GET)
	public Quiz show(@PathVariable int id) {
		return quizdao.show(id);
	}
}

//public List<Quiz> index(HttpServletResponse res);
//public Quiz show(int id, HttpServletResponse res);
//public Quiz create(String quizJSON, HttpServletResponse res);
//public Quiz update(int id, String quizJSON, HttpServletResponse res);
//public boolean destroy(int id, HttpServletResponse res);
//public Set<Question> showQuestions(int id, HttpServletResponse res);
//public Question createQuestions(int id, String questionJson, HttpServletResponse res);
//public  boolean destroyQuestions(int id, int questid, HttpServletResponse res);
