package com.study.spring;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		
		context.load("classpath:beans.xml");
		System.out.println("aaaaa");
		context.refresh();
		System.out.println("bbbbb");
		context.close();
		System.out.println("ccccc");
	}

}
