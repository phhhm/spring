package ir.dotin.spring.util;

import ir.dotin.spring.dto.ActivityLogRequest;
import ir.dotin.spring.dto.PrintRequestRequest;
import ir.dotin.spring.service.ActivityLoggerService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ActivityLogger {
    @Value("${configuration.type}")
    private String applicationType;

    private final ActivityLoggerService activityLoggerService;

    public ActivityLogger(ActivityLoggerService activityLoggerService) {
        this.activityLoggerService = activityLoggerService;
    }


    @Around("@annotation(ir.dotin.spring.util.LogActivity)")
    public Object addControllerActivityLog(ProceedingJoinPoint joinPoint) throws Throwable {
        PrintRequestRequest printRequestReq = (PrintRequestRequest) joinPoint.getArgs()[1];
        String name = joinPoint.getSignature().getName();
        ActivityLogRequest activityLogReq = new ActivityLogRequest()
                .setCardPAN(printRequestReq.getCardPAN())
                .setPersonalCode(printRequestReq.getPersonalCode())
                .setName(name)
                .setApplicationType(applicationType);

        activityLoggerService.createActivityLogger(activityLogReq);

        return joinPoint.proceed();
    }

}
