package com.example.demo.toggle;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@RequiredArgsConstructor
public class ToggleAspect {

    private final FeatureToggleProvider featureToggleProvider;
    //private final FallbackExecutor fallbackExecutor;
    //TODO fallbackExecutor 구현 필요 #1

    @Pointcut(value = "@within(toggle) || @annotation(toggle)", argNames = "toggle")
    public void matchAnnotation(Toggle toggle) {}

    @Around(value = "matchAnnotation(toggle)", argNames = "joinPoint, toggle" )
    public Object controlMethodByToggle(ProceedingJoinPoint joinPoint, Toggle toggle) throws Throwable {
        if (featureToggleProvider.isFeatureEnabled(toggle.value())) {
            return joinPoint.proceed();
        }
        //TODO fallbackExecutor.execute 구현 필요. #1
        // fallbackExecutor.execute(joinPoint, toggle.value());
        return joinPoint.proceed();
    }
}
