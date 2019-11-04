package com.study.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 검색된 클래스를 빈으로 등록할 때, 클래스의 첫 글자를 소문자로 바꾼
// 이름을 빈 이름으로 사용한다.
@Component
public class Hello {

	@Value("홍길동")
	private String name;
	@Value("전우치")
	private String nickname;
	
/*
 	@Autowired : 자동 주입
	@Qualifier : 자동 주입 가능한 빈이 두 개 이상일 때 특정한 한 개 지정하기
*/
	@Autowired
	@Qualifier("printerA")
	private Printer printer;

	public Hello() {
		super();
	}

	public Hello(String name, String nickname, Printer printer) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.printer = printer;
	}


	// Setter 메서드가 없어도 된다.

/*
	public void setName(String name) {
		this.name = name;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
*/

	public void setPrinter(Printer printer) {
		this.printer = printer;
	}
	public String sayHello() {
		return "Hello " + name + " : " + nickname;
	}
	public void print() {
		printer.print(sayHello());
	}

	
	
}
