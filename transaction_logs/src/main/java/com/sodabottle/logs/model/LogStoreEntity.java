package com.sodabottle.logs.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "log_store")
public class LogStoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private String token;

    private String activity;

    @Column(name = "http_verb")
    private String httpVerb;

    private String url;

    @Lob
    @Column(name = "req_json")
    private String requestJson;

    @Lob
    @Column(name = "res_json")
    private String responseJson;

    @Column(name = "req_ts")
    private Date requestTS;
}
