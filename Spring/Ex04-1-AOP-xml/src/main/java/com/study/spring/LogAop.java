package com.study.spring;

import org.aspectj.lang.ProceedingJoinPoint;
// ASPECT : 공통기능
// ADVICE : 기능자체 - 경과시간 출력
public class LogAop {

	
	public LogAop() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start.");
		
		long st = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 경과시간 : " + (et - st));
			
		}
		
	}
}
