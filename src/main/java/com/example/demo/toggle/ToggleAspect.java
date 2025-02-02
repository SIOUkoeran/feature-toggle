package com.example.demo.toggle;

import com.example.demo.toggle.fallback.FallbackExecutor;
import com.example.demo.toggle.functional.CheckedSupplier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ToggleAspect {

    private final FeatureToggleProvider featureToggleProvider;
    private final ToggleAspectExecutor toggleAspectExecutor;
    private final FallbackExecutor fallbackExecutor;
    //TODO fallbackExecutor 구현 필요 #1

    @Pointcut(value = "@within(toggle) || @annotation(toggle)", argNames = "toggle")
    public void matchAnnotation(Toggle toggle) {
    }

    @Around(value = "matchAnnotation(toggle)", argNames = "joinPoint, toggle")
    public Object controlMethodByToggle(ProceedingJoinPoint joinPoint, Toggle toggle) throws Throwable {
        if (featureToggleProvider.isFeatureEnabled(toggle.featureId())) {
            log.info("toggle enabled : {}", toggle.featureId());
            return joinPoint.proceed();
        }
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        Class<?> returnType = method.getReturnType();
        String methodName = method.getName();

        String fallbackMethodName = toggle.fallbackMethod();
        CheckedSupplier<Object> checkedSupplier = () -> proceed(joinPoint, fallbackMethodName, returnType);
        return fallbackExecutor.execute(joinPoint, toggle.featureId(), checkedSupplier);
    }

    protected Object proceed(ProceedingJoinPoint joinPoint, String methodName, Class<?> returnType) throws Throwable {
            return toggleAspectExecutor.handle(joinPoint, methodName, returnType);
    }
}
