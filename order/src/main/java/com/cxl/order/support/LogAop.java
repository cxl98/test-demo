package com.cxl.order.support;

import com.cxl.order.entry.Log;
import com.cxl.order.entry.User;
import com.cxl.order.service.LogService;
import com.cxl.order.util.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MemberSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private LogService logService;
    
    @Pointcut("@annotation(com.cxl.order.annotation.Log)")
    public void controllerLog(){
    }
    @Around("controllerLog()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest httpServletRequest=getHttpServletRequest();
        if (httpServletRequest==null){
            return null;
        }
        Log log=new Log();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        com.cxl.order.annotation.Log systemControllerLog = method.getAnnotation(com.cxl.order.annotation.Log.class);
        Object proceed = joinPoint.proceed();
        
        if (systemControllerLog!=null&&proceed!=null){
            String operation = systemControllerLog.operation();
            /*保存日志类型*/
            log.setLogOp(operation);
            String type = systemControllerLog.type();
            log.setLogType(type);
            String token = httpServletRequest.getHeader("token");
            if (StringUtils.isBlank(token)) {
                return null;
            }
            User user = JWTUtil.unSign(token, User.class);
            if (user == null) {
                return null;
            }
            log.setUserId(user.getUsername());
            log.setUrl(httpServletRequest.getRequestURI());
            log.setCreateTime(new Date());

            logService.insertLog(log);
        }
        return proceed;
    }
    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        return sra != null ? sra.getRequest() : null;
    }
}
