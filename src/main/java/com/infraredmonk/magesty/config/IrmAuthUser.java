package com.infraredmonk.magesty.config;

import lombok.Data;

@Data
public class IrmAuthUser {

    private String name;
    private String role;
    private String password;
}
