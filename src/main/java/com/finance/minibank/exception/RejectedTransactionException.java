package com.finance.minibank.exception;

public class RejectedTransactionException extends RuntimeException{

    private static  final long serialVersionUID = 1L;

    public RejectedTransactionException(String message) {
        super(message);
    }
}
