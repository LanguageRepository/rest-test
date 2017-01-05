package com.resttest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TestResultAnswer {
	private Long id;
	private TestResultQuestion testResultQuestion;
	private Answer answer;
	private String value;
	
	public TestResultAnswer() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
	public TestResultQuestion getTestResultQuestion() {
		return testResultQuestion;
	}

	public void setTestResultQuestion(TestResultQuestion testResultQuestion) {
		this.testResultQuestion = testResultQuestion;
	}

	@ManyToOne
	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	@Column
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
