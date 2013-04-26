package org.foodspring.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

/**
 * Created with IntelliJ IDEA.
 * User: azim
 * Date: 4/3/13
 * Time: 4:05 PM
 * To change this template use File | Settings | File Templates.
 */

@Aspect
public class ProfilerAspect {

    private static Logger log = Logger.getLogger(ProfilerAspect.class.getName());

    @Around("execution(* org.foodspring.service.*.*())")
    public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {

        StopWatch stopWatch = new StopWatch(getClass().getSimpleName());
        try {

            stopWatch.start(joinPoint.getSignature().getDeclaringType().getSimpleName() + "." + joinPoint.getSignature().getName());
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            log.info("AspectJ : " + stopWatch.prettyPrint());
        }

    }
}
