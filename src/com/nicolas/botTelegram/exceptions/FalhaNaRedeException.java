package com.nicolas.botTelegram.exceptions;

public class FalhaNaRedeException extends RuntimeException {
    public FalhaNaRedeException(String message) {
        super(message);
    }

    public FalhaNaRedeException(String message, Throwable cause) {
        super(message, cause);
    }

}
