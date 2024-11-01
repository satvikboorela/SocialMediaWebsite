package com.satvik.restful_web_services.aopaspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LoggingAspect {
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Before("execution(* com.satvik.restful_web_services.*.*.*(..))")
    public void BeforeLogMethodCall(JoinPoint joinPoint){
        logger.info("Before Aspect- Method call- {}", joinPoint.getArgs());

    }
     @After("execution(* com.satvik.restful_web_services.*.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint){
        logger.info("After Aspect- Method call- {}", joinPoint.getClass());

    }
}
