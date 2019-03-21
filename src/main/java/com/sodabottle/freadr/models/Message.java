package com.sodabottle.freadr.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity(name = "messages")
@Data
@NoArgsConstructor
@Audited
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    private String sender;

    @NotEmpty
    private String receiver;

    @NotEmpty
    private String text;

    private String template;

    private Date createdAt = new Date();

    private Boolean sent;

    private Date sentAt;

    private String failureReason;

    public Message(String sender, String receiver, String text, String template) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
    }

}
