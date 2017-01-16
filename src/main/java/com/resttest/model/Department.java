package com.resttest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Department {
	private Long id;
	private String name;
	private String description;
	private Department parent;
	private List<Department> childs;
	private List<User> users;
	private String type;
	
	public Department() {
		
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

	@Column
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy="parent")
	public List<Department> getChilds() {
		return childs;
	}

	public void setChilds(List<Department> childs) {
		this.childs = childs;
	}

	@OneToMany(mappedBy="department")
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Column
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
