package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String answerText;

	private boolean isCorrect;
	
	@JsonBackReference(value="questionToAnswer")
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	// Gets and Sets
	public String getAnswerText() {
		return answerText;
	}

	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", answerText=" + answerText + ", isCorrect=" + isCorrect + ", question=" + question
				+ "]";
	}

}
