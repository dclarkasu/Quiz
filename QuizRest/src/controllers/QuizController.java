package controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<Quiz> index(HttpServletResponse res) {
		return quizdao.index();
	}
	
	@RequestMapping(path="quizzes/{id}", method=RequestMethod.GET)
	public Quiz show(@PathVariable int id, HttpServletResponse res) {
		if (quizdao.show(id) == null) {
			res.setStatus(501);
		} else {
			res.setStatus(200);
		}
		return quizdao.show(id);
	}
	
	@RequestMapping(path="quizzes", method=RequestMethod.POST)
	public Quiz create(@RequestBody String quizJSON, HttpServletResponse res) {
		return quizdao.create(quizJSON);
	}
	
	@RequestMapping(path="quizzes/{id}", method=RequestMethod.PUT)
	public Quiz update(int id, String quizJSON, HttpServletResponse res) {
		return null;
	}
	
	@RequestMapping(path="quizzes/{id}", method=RequestMethod.DELETE)
	public boolean destroy(int id, HttpServletResponse res) {
		return false;
	}

//	@RequestMapping(path="quizzes/{id}", method=RequestMethod.GET)
//	public Set<Question> showQuestions(int id, HttpServletResponse res) {
//		return null;
//	}

//	@RequestMapping(path="quizzes", method=RequestMethod.POST)
//	public Question createQuestions(int id, String questJSON, HttpServletResponse res) {
//		return null;
//	}
	
	@RequestMapping(path="quizzes", method=RequestMethod.DELETE)
	public boolean destroyQuestions(int id, int questId, HttpServletResponse res) {
		return false;
	}
	
}
