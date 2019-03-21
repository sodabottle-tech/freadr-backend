package com.sodabottle.freadr.services.helpers;

import com.sodabottle.freadr.utils.ConfigurationLoader;
import com.sodabottle.freadr.utils.LogUtil;
import com.sodabottle.freadr.utils.RESTConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

@Component(value = "way2SMSManager")
@Slf4j
public class Way2SMSManager {

    @Autowired
    ConfigurationLoader configurationLoader;

    // required variables
    private String url = "http://www.way2sms.com";

    public String send(String number, String message) {

        Map<String, String> way2SmsConfig = null;
        try {
            way2SmsConfig = configurationLoader.getWay2SMSConfig();
        } catch (Exception e) {
            LogUtil.logErrorMessage("Error While loading Way2SMS Map from " + RESTConstants.WAY2SMS_CONFIG_FILE, null, log);
        }

        StringBuilder content = null;
        try {
            // construct data
            JSONObject urlParameters = new JSONObject();
            urlParameters.put("apikey", way2SmsConfig.get("apiKey"));
            urlParameters.put("secret", way2SmsConfig.get("secretKey"));
            urlParameters.put("usetype", way2SmsConfig.get("useType"));
            urlParameters.put("phone", number);
            urlParameters.put("message", URLEncoder.encode(message, "UTF-8"));
            urlParameters.put("senderid", way2SmsConfig.get("senderId"));

            URL obj = new URL(url + "/api/v1/sendCampaign");
            // send data
            HttpURLConnection httpConnection = (HttpURLConnection) obj.openConnection();
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream());
            wr.write(urlParameters.toString().getBytes());
            // get the response
            BufferedReader bufferedReader = null;
            if (httpConnection.getResponseCode() == 200) {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
            }
            content = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (Exception ex) {
            System.out.println("Exception at:" + ex);
            LogUtil.logErrorMessage(content.toString(), null, log);

            return content.toString();
        }

        LogUtil.logMessage(content.toString(), null, log);
        return content.toString();
    }
}
