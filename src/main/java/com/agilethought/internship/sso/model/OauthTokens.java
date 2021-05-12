package com.agilethought.internship.sso.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Document(collection = "oauthtokens")
@Data
public class OauthTokens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String _id;

    private String oauthId;

    private long createDate;

    private long expireTime;

}
