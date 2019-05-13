package com.springboot.mongodb.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LoggingAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.springboot.mongodb.repository.*.*(..))")
	public void before(JoinPoint joinPoint) throws Throwable {
		Object[] args = joinPoint.getArgs();
		StringBuilder inputArgsStr = new StringBuilder("Input = ");
		for (Object o : args) {
			inputArgsStr.append(o);
			inputArgsStr.append(", ");
		}
		logger.info(joinPoint.toShortString() + ", " + inputArgsStr.toString());
	}

	/*
	 * @Around("execution(* com.springboot.mongodb.resource.*.*(..))") public
	 * void around(ProceedingJoinPoint joinPoint) throws Throwable { long
	 * startTime = System.currentTimeMillis();
	 *
	 * joinPoint.proceed();
	 *
	 * long timeTaken = System.currentTimeMillis() - startTime; // logger.info(
	 * "Time Taken by {} is {}", joinPoint, timeTaken/1000d); }
	 */
}
