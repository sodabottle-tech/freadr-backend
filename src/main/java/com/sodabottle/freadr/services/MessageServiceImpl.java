package com.sodabottle.freadr.services;

import com.sodabottle.freadr.models.Message;
import com.sodabottle.freadr.repositories.MessageRepo;
import com.sodabottle.freadr.request.MessageRequest;
import com.sodabottle.freadr.services.helpers.Way2SMSManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Locale;

@Service
@Async
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepo messageRepo;

    @Autowired
    MessageSource messageSource;

    @Autowired
    Way2SMSManager way2SMSManager;

    @Override
    public void sendMessage(MessageRequest messageRequest) {

        String staticMessage = messageSource.getMessage("message.otp", new Object[]{}, Locale.US);
        String dynamicMessage = String.format(staticMessage, (Object[]) messageRequest.getParams());

        //save messages in DB
        Message message = messageRepo.save(new Message(messageRequest.getSender(), messageRequest.getRecipient(), dynamicMessage, "OTP"));

        //send service
        String response = way2SMSManager.send(messageRequest.getRecipient(), dynamicMessage);
        if (!StringUtils.isEmpty(response) && response.contains("400")) {
            message.setFailureReason(response);
        } else {
            message.setSent(true);
            message.setSentAt(new Date());
        }
        //update message status
        messageRepo.save(message);
    }

}
