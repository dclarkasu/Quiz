package data;

import java.util.List;
import java.util.Set;

import entities.Quiz;

public interface QuizDAO {
	public List<Quiz> index();
	public Quiz show(int id);
	public Quiz create(Quiz q);
	public Quiz update(int id, Quiz q);
	public boolean destroy(int id);
//	public Set<Question> showQuestions(int id);
//	public Question createQuestion(int id, Question quest);
//	public boolean destroyQuestion(int id, int questid);

}