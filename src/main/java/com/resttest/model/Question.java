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
	private List<TestPassage> testPassages;

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

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "test_passage_questions",
				joinColumns = @JoinColumn(name = "question_id"),
				inverseJoinColumns = @JoinColumn(name = "test_passage_id"))
	public List<TestPassage> getTestPassages() {
		return testPassages;
	}

	public void setTestPassages(List<TestPassage> testPassages) {
		this.testPassages = testPassages;
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
}
