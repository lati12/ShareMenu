package com.server.sharemenu.security;

public enum TokenType {
    ACCESS_TOKEN(0),
    REFRESH_TOKEN(1)
    ;

    private final int value;

    TokenType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
