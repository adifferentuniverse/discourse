package com.adifferentuniverse.discourse.programme.utils;

import lombok.Data;

import java.security.Principal;

@Data
public class SimplePrincipal implements Principal {
    private String name;

    public SimplePrincipal(String name) {
        this.name = name;
    }
}
