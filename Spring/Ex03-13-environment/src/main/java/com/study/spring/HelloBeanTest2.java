package com.study.spring;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.io.support.ResourcePropertySource;

public class HelloBeanTest2 {
	
	public static void main(String[] args) {

		
		
		String configLocation = "classpath:beans.xml";
		
		// 1.IoC 컨테이너 생성
		ApplicationContext context = new GenericXmlApplicationContext(configLocation);
		ConfigurableEnvironment env = (ConfigurableEnvironment) context.getEnvironment();
		MutablePropertySources propertySources = env.getPropertySources();
		
		try {
			propertySources.addLast(new ResourcePropertySource("classpath:config/database.properties"));
			System.out.println(env.getProperty("db.username"));
			System.out.println(env.getProperty("db.password"));
		} catch (IOException e) {
			 
		}
		
		// 2.Hello Bean 가져오기
		Hello hello = (Hello) context.getBean("hello");
		
		System.out.println(hello.getDriverClass());
		System.out.println(hello.getUrl());
		System.out.println(hello.getUsername());
		System.out.println(hello.getPassword());
		
		
		System.out.println("---------------------");
		// beans.xml에서만 프로퍼티를 사용하는 게 아니고
		// 프로그램 내부에서도 사용하고 싶을 때 사용
		
		System.out.println(env.getProperty("db.username"));
		System.out.println(env.getProperty("db.password"));
		
		
	}
}
