package org.big.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Slf4j
public class LoggerAspect {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Around("execution(* org.big..controller.*Controller.*(..)) or execution(* org.big..service.*Tmpl.*(..)) or execution(* org.big..mapper.*Mapper.*(..))")
	
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable{
		String type="";
	      String name = joinPoint.getSignature().getDeclaringTypeName();
	      if(name.indexOf("Controller") > -1) {
	         type = "Controller \t: ";
	      }
	      else if(name.indexOf("Service") > -1) {
	         type = "ServiceImpl \t: ";
	      }
	      else if(name.indexOf("Mapper") > -1) {
	         type = "Mapper \t: ";
	      }
	      log.info(type + name + "." + joinPoint.getSignature().getName() + "()");
	      return joinPoint.proceed();

	}
}
