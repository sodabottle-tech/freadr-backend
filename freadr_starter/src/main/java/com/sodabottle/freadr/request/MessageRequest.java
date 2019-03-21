package com.sodabottle.freadr.request;

import lombok.Data;

@Data
public class MessageRequest {

    private String sender;

    private String recipient;

    private String message;

    private String template;

    private String[] params;

    public MessageRequest(String sender, String recipient, String template, String[] params) {

        this.sender = sender;
        this.recipient = recipient;
        this.template = template;
        this.params = params;
    }
}
