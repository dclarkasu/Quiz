package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String questionText;
	
	@JsonManagedReference(value="questionToAnswer")
	@OneToMany(mappedBy = "question")
	private Set<Answer> answers;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;

	// Gets and Sets
	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Set<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText + ", answers=" + answers + ", quiz=" + quiz
				+ "]";
	}

}
