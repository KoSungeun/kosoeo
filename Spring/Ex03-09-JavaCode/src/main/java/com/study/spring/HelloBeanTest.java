package com.study.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class HelloBeanTest {
	
	public static void main(String[] args) {

		// 의존성이 강한 결합
//		Printer p1 = new PrinterA();
//		Printer p2 = new PrinterB();
//		Hello h1 = new Hello();
//		h1.setName("이순신");
//		h1.setNickname("성웅");
//		h1.setPrinter(p1);
//		h1.print();
		
		

		
		// 1.IoC 컨테이너 생성
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		// 2.Hello Bean 가져오기
		Hello helloA = (Hello) context.getBean("hello");
		helloA.print();
		// 3.Hello Bean 가져오기
		Hello helloB = (Hello) context.getBean("hello1");
		helloB.print();
		// 4.PrinterB Bean 가져오기
		Printer printer = context.getBean("printerB", Printer.class);
		helloA.setPrinter(printer);
		helloA.print();
		
		// 5.싱글톤인지 확인
		
		System.out.println(helloA == helloB);
		
		context.close();

	}
}
