package com.resttest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TestResultQuestion {
	private Long id;
	private TestResult testResult;
	private Question question;
	private List<TestResultAnswer> answers;
	private Integer totalAnswers;
	private Integer rightAnswers;
	
	public TestResultQuestion() {
		
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
	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

	@ManyToOne
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@OneToMany(mappedBy="testResultQuestion")
	public List<TestResultAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<TestResultAnswer> answers) {
		this.answers = answers;
	}

	@Column
	public Integer getTotalAnswers() {
		return totalAnswers;
	}

	public void setTotalAnswers(Integer totalAnswers) {
		this.totalAnswers = totalAnswers;
	}

	@Column
	public Integer getRightAnswers() {
		return rightAnswers;
	}

	public void setRightAnswers(Integer rightAnswers) {
		this.rightAnswers = rightAnswers;
	}
	
}
