package com.springboot.hktss.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	String name;
	Integer age;

	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Employee() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("Employee(");
		sb.append("Id : ");
		sb.append(id);
		sb.append(", Name : ");
		sb.append(name);
		sb.append(", Age : ");
		sb.append(age);
		sb.append(")");
		return sb.toString();
	}

}
