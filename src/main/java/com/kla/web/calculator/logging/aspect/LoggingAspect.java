package com.kla.web.calculator.logging.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;



@Aspect
public class LoggingAspect {
	
	//Logger Variable
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Around("within(com.kla.web.calculator..*)")
	@Order(1)
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
		long startTime = System.currentTimeMillis();
		long endTime;
		LOGGER.trace("LogAroundBefore for class {}, in method {}, with argumenets {}, startTime {} ", 
				joinPoint.getTarget(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), startTime);
	    try
	    {
	        Object result = joinPoint.proceed();
			endTime = System.currentTimeMillis();
			LOGGER.trace("LogAroundAfter for class {}, in method {}, with argumenets {}, endTime {} ", 
					joinPoint.getTarget(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), endTime);
			LOGGER.info("LogAroundAfter for class {}, in method {}, with argumenets {}, totalTime {} ", 
					joinPoint.getTarget(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), (endTime - startTime));
	        return result;
	    }
	    catch (IllegalArgumentException e)
	    {
			endTime = System.currentTimeMillis();
			LOGGER.error("LogAroundException for class {}, in method {}, with argumenets {}, endTime {}, exception {} ", 
					joinPoint.getTarget(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), endTime, e);
			LOGGER.error("LogAroundException for class {}, in method {}, with argumenets {}, totalTime {}, exception {}", 
					joinPoint.getTarget(), joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()), (endTime - startTime), e);
	        throw e;
	    }

	}

}

