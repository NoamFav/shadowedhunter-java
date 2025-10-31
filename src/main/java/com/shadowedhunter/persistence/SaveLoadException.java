package com.shadowedhunter.persistence;

public class SaveLoadException extends Exception {
    public SaveLoadException(String message) {
        super(message);
    }

    public SaveLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
