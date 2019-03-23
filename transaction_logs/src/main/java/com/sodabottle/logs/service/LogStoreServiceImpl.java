package com.sodabottle.logs.service;

import com.sodabottle.logs.model.LogStoreEntity;
import com.sodabottle.logs.model.LogStoreRequest;
import com.sodabottle.logs.repo.LogStoreRepo;
import com.sodabottle.utils.LogUtil;
import com.sodabottle.utils.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Async
@Slf4j
@Service
public class LogStoreServiceImpl implements LogStoreService {
    private LogStoreRepo logStoreRepo;

    @Autowired
    public LogStoreServiceImpl(final LogStoreRepo logStoreRepo) {
        this.logStoreRepo = logStoreRepo;
    }

    @Override
    public void logRequest(final LogStoreRequest logStoreRequest) {
        LogStoreEntity logStoreEntity = ModelMapperUtils.map(logStoreRequest, LogStoreEntity.class).orElseGet(null);
        if (null != logStoreEntity) {
            logStoreRepo.saveAndFlush(logStoreEntity);
            LogUtil.logDebugMessage("Logged Transaction Successfully at %s",
                    new String[]{String.valueOf(System.currentTimeMillis())}, log);
        }
    }
}
