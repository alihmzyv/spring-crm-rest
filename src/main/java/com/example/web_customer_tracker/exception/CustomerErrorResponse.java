package com.example.web_customer_tracker.exception;

import java.time.Instant;

public class CustomerErrorResponse {
    private final int statusCode;
    private final String message;
    private final long timeStamp = Instant.now().toEpochMilli();

    public CustomerErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
