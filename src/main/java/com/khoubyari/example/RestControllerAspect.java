package com.khoubyari.example;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.khoubyari.example.exception.SPFException;

@Aspect
@Component
public class RestControllerAspect {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());
    
    /**
    @Before("execution(public * com.khoubyari.example.api.rest.*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        log.info(":::::AOP Before REST call:::::" + pjp);
    } */
    
    
	/**
	 * Pointcut definition for controller package so all classes with in package
	 * controller class will be considered.
	 */
	@Pointcut("execution(public * com.khoubyari.example.api.rest.*Controller.*(..))")
	public void controllerClassMethods() {
	}
	
	@Around("controllerClassMethods()")
	public Object logArroundCall(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("--------------------------------------------------");
		System.out.println("Esto es el @Around de aspect");
		System.out.println("--------------------------------------------------");
		long start = System.currentTimeMillis();
		Object output = joinPoint.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		log.debug("Method [{}] execution time: [{}] in milliseconds. ", joinPoint.getSignature().getName(),
				elapsedTime);
		return output;
	}
	
	/**
	 * This will be executed before the call to every method to log name of method
	 * and input parameters.
	 * 
	 * @param joinPoint
	 */
	@Before("controllerClassMethods()")
	public void logBeforeCall(JoinPoint joinPoint) {
		System.out.println("--------------------------------------------------");
		System.out.println("Esto es el @Before de aspect");
		System.out.println("--------------------------------------------------");
		log.info("Attempting to Call [{}]:[{}] with Args, {}",
				joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(),
				joinPoint.getArgs());
	}

	/**
	 * This method will be executed after the end of every call. Showing the name of
	 * Method executed and result.
	 * 
	 * @param joinPoint
	 * @param result
	 */
	@AfterReturning(pointcut = "controllerClassMethods()", returning = "result")
	public void logAfterCall(JoinPoint joinPoint, Object result) {
		System.out.println("--------------------------------------------------");
		System.out.println("Esto es el @AfterReturning de aspect");
		System.out.println("--------------------------------------------------");
		log.info("Successfully Called [{}]:[{}] returned  value, [{}]",
				joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(),
				result);
	}

	/**
	 * This Method will be executed every time a method returns an exception.
	 * 
	 * @param joinPoint
	 * @param exception
	 * @throws Throwable
	 */
	@AfterThrowing(pointcut = "controllerClassMethods()", throwing = "exception")
	public void logAfterThrowingCall(JoinPoint joinPoint, SPFException exception) throws Throwable {
		System.out.println("--------------------------------------------------");
		System.out.println("Esto es el @AfterThrowing de aspect");
		System.out.println("--------------------------------------------------");
		log.error("Error [{}] Calling [{}]:[{}] returned  error message : , [{}]", exception.getHttpStatus(),
				joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
				exception.getMessage());
	}



    
    
    
}
