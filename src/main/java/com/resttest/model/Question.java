package com.resttest.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Question {
	private Long id;
	private String question;
	private Test test;
	private List<Answer> answers;
	private QuestionAnswerType type;
	private Boolean isDeleted;

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
	public QuestionAnswerType getType() {
		return type;
	}

	public void setType(QuestionAnswerType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Question question = (Question) o;

		return id != null ? id.equals(question.id) : question.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Column(name = "deleted")
	public Boolean getDeleted() {
		return isDeleted;
	}

	public void setDeleted(Boolean deleted) {
		isDeleted = deleted;
	}
}
