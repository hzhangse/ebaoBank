package com.changcai.bank.pingan.ebao.aop;

import java.lang.reflect.Method;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Conditional(EbaoExceptionCatchAspectCondition.class)  
public class EbaoExceptionCatchAspect {
    public final static String annotationPointcut = "@annotation(com.changcai.bank.pingan.ebao.annotation.Action)";
    protected final Logger      LOGGER             = LoggerFactory.getLogger(this.getClass());

    //  private static final long ONE_MINUTE = 1000; 
    @Pointcut(EbaoExceptionCatchAspect.annotationPointcut)
    public void annotationPointCut() {
    }


    @AfterThrowing(throwing = "ex", pointcut = "annotationPointCut()")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // Action action = method.getAnnotation(Action.class);
        String fullStackTrace = ExceptionUtils.getStackTrace(ex);
        LOGGER.error("系统异常:" + fullStackTrace);
    }


    
}
