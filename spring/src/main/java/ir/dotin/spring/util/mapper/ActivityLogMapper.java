package ir.dotin.spring.util.mapper;

import ir.dotin.spring.dto.ActivityLogRequest;
import ir.dotin.spring.model.ActivityLog;

public class ActivityLogMapper {
    public static ActivityLog mapActivityLogReqToActivityLog(ActivityLogRequest activityLogReq){
        return new ActivityLog()
                .setPersonalCode(activityLogReq.getPersonalCode())
                .setCardPAN(activityLogReq.getCardPAN())
                .setName(activityLogReq.getName())
                .setApplicationType(activityLogReq.getApplicationType());
    }
}
