package com.example.demo.toggle.fallback;

import com.example.demo.toggle.functional.CheckedSupplier;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * fallbackExecutor interface
 */
@Component
public interface FallbackExecutor {

    Object execute(ProceedingJoinPoint proceedingJoinPoint, String featureName, CheckedSupplier<Object> checkedSupplier);
}
