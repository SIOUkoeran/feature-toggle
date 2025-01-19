package com.example.demo.toggle.fallback;

import com.example.demo.toggle.functional.CheckedSupplier;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component
public class FallbackDefaultExecutor implements FallbackExecutor {

    @Override
    public Object execute(ProceedingJoinPoint proceedingJoinPoint, String featureName, CheckedSupplier<Object> checkedSupplier) {
        try {
            return checkedSupplier.get();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
