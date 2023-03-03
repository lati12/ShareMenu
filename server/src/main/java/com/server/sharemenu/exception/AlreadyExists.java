package com.server.sharemenu.exception;

public class AlreadyExists extends Exception {
    public AlreadyExists(String field) {
        super(field);
    }
}
