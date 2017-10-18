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
import entities.Question;
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
			res.setStatus(404);
		} else {
			res.setStatus(200);
		}
		return quizdao.show(id);
	}
	
	@RequestMapping(path="/quizzes", method=RequestMethod.POST)
	public Quiz create(@RequestBody String quizJSON, HttpServletResponse res) {
		Quiz q = quizdao.create(quizJSON);
		if (q == null) {
			res.setStatus(400);
		} else {
			res.setStatus(201);
		}
		return q;
	}
	
	@RequestMapping(path="quizzes/{id}", method=RequestMethod.PUT)
	public Quiz update(@PathVariable int id, @RequestBody String quizJSON, HttpServletResponse res) {
		if (quizdao.update(id, quizJSON) == null) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return quizdao.update(id, quizJSON);
		}
	}
	
	@RequestMapping(path="quizzes/{id}", method=RequestMethod.DELETE)
	public boolean destroy(@PathVariable int id, HttpServletResponse res) {
		if (quizdao.destroy(id) == false) {
			res.setStatus(400);
			return false;
		} else {
			res.setStatus(200);
			return true;
		}
	}

	@RequestMapping(path="quizzes/{id}/questions", method=RequestMethod.GET)
	public Set<Question> showQuestions(@PathVariable int id, HttpServletResponse res) {
		Set<Question> quests = quizdao.showAllQuestions(id);
		if (quests == null) {
			res.setStatus(404);
			return null;
		} else {
			res.setStatus(200);
			return quests;
		}
	}

	@RequestMapping(path="quizzes/{id}/questions", method=RequestMethod.POST)
	public Question createQuestions(@PathVariable int id,@RequestBody String questJSON, HttpServletResponse res) {
		Question quest = quizdao.createQuestion(id, questJSON);
		return quest;
	}
	
	@RequestMapping(path="quizzes/{id}/questions/{questId}", method=RequestMethod.DELETE)
	public boolean destroyQuestions(@PathVariable int id,@RequestBody int questId, HttpServletResponse res) {
		return false;
	}
	
}
