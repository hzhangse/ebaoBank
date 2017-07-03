package com.changcai.bank.pingan.ebao.aop;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import com.changcai.bank.pingan.ebao.constant.MessageType;
import com.changcai.bank.pingan.ebao.protocol.struct.BaseMessageBody;
import com.changcai.bank.pingan.ebao.service.IEbaoClientInvoker;


@Aspect
@Component
@Conditional(EbaoTimeCostPrintAspectCondition.class)  
public class EbaoTimeCostPrintAspect {
  
    protected final Logger      LOGGER             = LoggerFactory.getLogger(this.getClass());

    //  private static final long ONE_MINUTE = 1000; 
    @Pointcut(EbaoExceptionCatchAspect.annotationPointcut)
    public void annotationPointCut() {
    }

    @PostConstruct
    public void init() {

    }


    @Around(value = "annotationPointCut()")
    public Object timeAround(ProceedingJoinPoint joinPoint) throws Throwable{
        // 定义返回对象、得到方法需要的参数  
        Object obj = null;
        Object[] args = joinPoint.getArgs();
        Object target = joinPoint.getTarget();
        long startTime = System.currentTimeMillis();

        try {
            obj = joinPoint.proceed(args);
        } catch (Throwable e) {
            throw e;
        }

        // 获取执行的方法名  
        long endTime = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getDeclaringTypeName() + "." + signature.getName();
        String logUUID="";
        if (target instanceof IEbaoClientInvoker){
            logUUID = ((IEbaoClientInvoker)target).getLogUUID();
        }
        StringBuilder argsBuilder = new StringBuilder();
        argsBuilder.append(logUUID);
        argsBuilder.append(methodName);
        
      
        if (args != null) {
          
            argsBuilder.append("(");
            for (Object arg : args) {
                if (arg instanceof BaseMessageBody) {
                    argsBuilder.append(((BaseMessageBody) arg).toJSONString());
                } else {
                    String funcId = arg.toString();
                    argsBuilder.append(funcId);
                    argsBuilder.append(",");
                    argsBuilder.append(MessageType.getMessageType(funcId));
                }
                argsBuilder.append(",");
            }
            argsBuilder.append(")");
        }
        // 打印耗时的信息  
        this.printExecTime(argsBuilder.toString(), startTime, endTime);

        return obj;
    }

   
    /**  
    * 打印方法执行耗时的信息，如果超过了一定的时间，才打印  
    * @param methodName  
    * @param startTime  
    * @param endTime  
    */
    private void printExecTime(String methodName, long startTime, long endTime) {
        long diffTime = endTime - startTime;

        //  if (diffTime > ONE_MINUTE) {  
        LOGGER.warn(methodName + " 方法执行耗时：" + diffTime + " ms");
        //  }  
    }
}
