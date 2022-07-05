package com.xlkk.aspect;

import com.xlkk.annotation.PrintLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Retention;

@Aspect
@Component
public class PrintLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(PrintLogAspect.class);

    @Autowired
    HttpServletRequest request;

    @Around(value="@annotation(printLog)")
    public Object aroundMethod(ProceedingJoinPoint point, PrintLog printLog){

        try {
            //获取ip
            String ip = request.getRemoteAddr();
            LOGGER.info(String.format("请求ip=%s,请求接口=%s,用户行为=%s",ip,printLog.value(),printLog.handle().getDesc()));
            Object proceed = point.proceed();
            LOGGER.info(String.format("执行结果为=%s",proceed));
            return proceed;
        } catch (Throwable e) {
            LOGGER.error("发生异常，异常=" + e.getMessage());
        }
        //异常时修改返回结果为1222
        return "1222";
    }



}
