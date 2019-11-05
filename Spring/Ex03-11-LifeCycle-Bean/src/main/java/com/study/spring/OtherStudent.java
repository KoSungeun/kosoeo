package com.study.spring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class OtherStudent {
	private String name;
	private int age;
	
	
	public OtherStudent() {
	}
	public OtherStudent(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@PostConstruct
	public void initMethod() throws Exception {
		System.out.println("OtherStudent : initMethod()");
		
	}
	@PreDestroy
	public void destroyMethod()  {
		System.out.println("OtherStudent : destroyMethod");
		
	}
	
}
