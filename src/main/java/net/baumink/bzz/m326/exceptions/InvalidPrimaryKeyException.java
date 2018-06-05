package net.baumink.bzz.m326.exceptions;

public class InvalidPrimaryKeyException extends RuntimeException {
    public InvalidPrimaryKeyException(String table, String method) {
        super(String.format("Invalid PK on table '%s' with method '%s'", table, method));
    }
}
