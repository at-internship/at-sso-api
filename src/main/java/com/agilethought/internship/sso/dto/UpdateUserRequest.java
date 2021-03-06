package com.agilethought.internship.sso.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    private String id;
    private Integer type;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer status;

}
