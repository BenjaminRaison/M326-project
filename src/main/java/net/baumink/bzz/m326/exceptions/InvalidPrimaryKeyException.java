package net.baumink.bzz.m326.exceptions;

/**
 * The type Invalid primary key exception.
 */
public class InvalidPrimaryKeyException extends RuntimeException {
    /**
     * Instantiates a new Invalid primary key exception.
     *
     * @param table  the table
     * @param method the method
     */
    public InvalidPrimaryKeyException(String table, String method) {
        super(String.format("Invalid PK on table '%s' with method '%s'", table, method));
    }
}
