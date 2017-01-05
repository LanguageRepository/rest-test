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
public class TestResult {
	private Long id;
	private User user;
	private Test test;
	private Integer totalQuestions;
	private Integer rightAnswers;
	private List<TestResultQuestion> testResultQuestions;
	
	public TestResult() {
		
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
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	@Column
	public Integer getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(Integer totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

	@Column
	public Integer getRightAnswers() {
		return rightAnswers;
	}

	public void setRightAnswers(Integer rightAnswers) {
		this.rightAnswers = rightAnswers;
	}

	@OneToMany(mappedBy="testResult")
	public List<TestResultQuestion> getTestResultDetails() {
		return testResultQuestions;
	}

	public void setTestResultDetails(List<TestResultQuestion> testResultQuestions) {
		this.testResultQuestions = testResultQuestions;
	}
	
}
