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
public class Paragraph {
	private Long id;
	private String name;
	private Paragraph parent;
	private List<Paragraph> childs;
	private List<Test> tests;
	
	public Paragraph() {
		
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
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	public Paragraph getParent() {
		return parent;
	}

	public void setParent(Paragraph parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy="parent")
	public List<Paragraph> getChilds() {
		return childs;
	}

	public void setChilds(List<Paragraph> childs) {
		this.childs = childs;
	}

	@OneToMany(mappedBy="paragraph")
	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	
}
