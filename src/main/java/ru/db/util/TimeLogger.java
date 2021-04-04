package ru.db.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeLogger {

    @Pointcut("execution(* ru.db.repos.*.*(..))")
    public void execute() {};

    @Around(value = "execute()")
    public Object printTime(ProceedingJoinPoint pjp) throws Throwable {
        double time = System.currentTimeMillis();

        Object proceed = pjp.proceed();

        time = System.currentTimeMillis() - time;
        System.out.println("Время выполнения - " + time );
        return proceed;
    }
}
