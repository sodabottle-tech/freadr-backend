package com.sodabottle.logs.service;

import com.sodabottle.logs.model.LogStoreRequest;

public interface LogStoreService {
    void logRequest(final LogStoreRequest logStoreRequest);
}
