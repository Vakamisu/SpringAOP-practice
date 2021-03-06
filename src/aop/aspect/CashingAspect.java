package aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Aspect
@Order(3)
@Component
public class CashingAspect {

    private static Logger logger = Logger.getLogger(CashingAspect.class.getName());
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    @Before("aop.aspect.PointcutAspect.ifMarker()")
    public void cash(JoinPoint joinPoint) {
        logger.info(ANSI_YELLOW + ">>>>>>Cashing " + joinPoint.getSignature() + ". Values: "
                + Arrays.toString(joinPoint.getArgs()) + ANSI_RESET);
    }

    @AfterReturning(pointcut = "aop.aspect.PointcutAspect.getList()", returning = "myData")
    public void processGetList(JoinPoint joinPoint, List<String> myData) {
        logger.info(ANSI_YELLOW + ">>>>>>Cashing " + myData + ANSI_RESET);
        myData.set(1, "Obama");
    }
}
