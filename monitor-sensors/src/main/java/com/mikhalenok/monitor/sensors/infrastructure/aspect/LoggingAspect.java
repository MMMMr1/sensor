package com.mikhalenok.monitor.sensors.infrastructure.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {

    @Pointcut("within(@com.mikhalenok.monitor.sensors.infrastructure.aspect.Log *)")
    public void isClassWithLogAnnotation() {
    }

    @Around("isClassWithLogAnnotation()")
    public Object loggingMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed(joinPoint.getArgs());
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String logMessage = """
                %s{REQUEST : %s : %s}
                {RESPONSE : %s :  %s}""".formatted("\n",
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getName(),
                result);
        if (className.endsWith("Handler")) {
            log.error(logMessage);
        } else {
            log.info(logMessage);
        }
        return result;
    }

}
