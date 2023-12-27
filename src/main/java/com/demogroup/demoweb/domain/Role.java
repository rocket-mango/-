package com.demogroup.demoweb.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public enum Role {
    USER("ROLE_USER");

    private String roleName;
}
