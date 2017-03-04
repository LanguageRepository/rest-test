package com.resttest.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
	private Long id;
	private String question;
	private Test test;
	private List<Answer> answers;
	private QuestionEnum type;

	public Question() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@ManyToOne
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@OneToMany(mappedBy="question")
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Column
	@Enumerated(EnumType.STRING)
	public QuestionEnum getType() {
		return type;
	}

	public void setType(QuestionEnum type) {
		this.type = type;
	}
}
