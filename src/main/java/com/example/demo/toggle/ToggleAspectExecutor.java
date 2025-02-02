package com.example.demo.toggle;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class ToggleAspectExecutor {


    public boolean canHandleReturnType(Object returnType) {
        //TODO returnType이 method 실행 시 반환하는 type 에 casting 가능한지 구현
        return returnType instanceof Flux<?> || returnType instanceof Mono<?>;
    }

    public Object handle(ProceedingJoinPoint joinPoint, String fallbackMethodName, Class<?> returnType) {
        //TODO
        Method method = null;
        try {
            Class<?> aClass = joinPoint.getThis().getClass();
            method = aClass.getMethod(fallbackMethodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        if (method == null) {
            throw new RuntimeException("Method not found: " + fallbackMethodName);
        }
        try {
            return method.invoke(joinPoint.getThis(), joinPoint.getArgs());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
