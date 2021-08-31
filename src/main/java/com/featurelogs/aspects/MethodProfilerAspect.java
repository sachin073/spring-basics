package com.featurelogs.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MethodProfilerAspect {

    //@Around(" @annotation(MethodProfiler)") if this class is in same package of annotation MethodProfiler
    @Around(" @annotation(com.featurelogs.annotations.MethodProfiler)")
    public Object printProfile(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTimeMs = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();
        System.out.println("Method class/name : "+ joinPoint.getSignature().toShortString() + " | Args : "+ Arrays.toString(joinPoint.getArgs())+ " |  executed in " + (System.currentTimeMillis() - startTimeMs) + "ms");

        return proceed;
    }
}
