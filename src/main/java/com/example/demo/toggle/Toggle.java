package com.example.demo.toggle;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 런타임에도 어노테이션을 참조 가능하도록 설정
@Target(ElementType.METHOD) //
public @interface Toggle {
    String featureId();
    boolean enabled() default true;
    String fallbackMethod() default "default";
}
