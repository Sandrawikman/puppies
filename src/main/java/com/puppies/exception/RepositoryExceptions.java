package com.puppies.exception;

public class RepositoryExceptions extends RuntimeException {

    public RepositoryExceptions() {
    }

    public RepositoryExceptions(String message) {
        super(message);
    }

    public RepositoryExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}
