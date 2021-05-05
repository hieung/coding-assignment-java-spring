package com.gfg.product.exception;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException() {
        super("No data found");
    }
}