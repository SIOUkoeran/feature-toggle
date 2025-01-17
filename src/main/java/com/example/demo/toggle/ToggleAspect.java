package com.example.demo.toggle;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ToggleAspect {

    @Around("@annotation(toggle)")
    public Object controlMethodByToggle(ProceedingJoinPoint joinPoint, Toggle toggle) throws Throwable {
        if (toggle.value()) {
            return joinPoint.proceed();
        }
        else return null;
    }
}
