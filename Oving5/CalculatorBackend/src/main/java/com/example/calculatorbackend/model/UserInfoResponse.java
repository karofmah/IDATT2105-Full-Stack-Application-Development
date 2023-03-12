package com.example.calculatorbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfoResponse {

    @JsonIgnore
    private final String name;
    @JsonIgnore
    private final String password;


    public UserInfoResponse(final String name,
                            final String password
    ) {

        this.name = name;
        this.password = password;
    }


    public String getUsername() {return name;}

    public String getPassword() {return password;}

}
