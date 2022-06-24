package ir.dotin.spring.service;

import ir.dotin.spring.dto.ActivityLogRequest;
import ir.dotin.spring.model.ActivityLog;
import ir.dotin.spring.repository.ActivityLogRepo;
import ir.dotin.spring.util.mapper.ActivityLogMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActivityLoggerService {
    private final ActivityLogRepo activityLogRepo;

    public ActivityLoggerService(ActivityLogRepo activityLoggerRepo) {
        this.activityLogRepo = activityLoggerRepo;
    }

    @Transactional
    public void createActivityLogger(ActivityLogRequest activityLogReq){
        ActivityLog activityLog = ActivityLogMapper.mapActivityLogReqToActivityLog(activityLogReq);
        activityLogRepo.save(activityLog);
    }
}
