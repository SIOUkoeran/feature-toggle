package com.example.demo.toggle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import static org.springframework.aop.support.AopUtils.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class ToggleInitializer implements ApplicationRunner {

    private final RedisTemplate<String, String> redisTemplate;
    private final ApplicationContext applicationContext;


    public void init() {
        for (String beanName : applicationContext.getBeanDefinitionNames()) {
            Object obj = applicationContext.getBean(beanName);
            Class<?> objClz = obj.getClass();
            if (org.springframework.aop.support.AopUtils.isAopProxy(obj)) {
                objClz = org.springframework.aop.support.AopUtils.getTargetClass(obj);
            }
            if (objClz.isAnnotationPresent(Service.class)) {
                updateNewToggle(objClz);
            }
        }
    }

    void updateNewToggle(Class<?> clazz) {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Toggle.class)) {
                Toggle toggle = method.getAnnotation(Toggle.class);
                String featureId = toggle.featureId();
                if (redisTemplate.opsForValue().get(featureId) == null) {
                    redisTemplate.opsForValue().set(featureId, String.valueOf(toggle.enabled()));
                }
            }
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        init();
    }
}
