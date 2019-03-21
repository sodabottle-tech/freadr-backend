package com.sodabottle.freadr.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(value = "notificationConfigurationLoader")
public class ConfigurationLoader {

    @Autowired
    @Qualifier(value = "notificationPropertyLoader")
    PropertyLoader propertyLoader;

    public Map<String, String> getWay2SMSConfig() {
        Map<String, String> gupshupConfig = new HashMap<>();

        try {
            gupshupConfig = propertyLoader.getPropertyValuesAsMap(RESTConstants.WAY2SMS_CONFIG_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return gupshupConfig;
    }

}