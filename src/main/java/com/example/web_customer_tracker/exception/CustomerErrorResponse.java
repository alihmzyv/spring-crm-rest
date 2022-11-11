package com.example.web_customer_tracker.exception;

import java.time.Instant;

public class CustomerErrorResponse {
    private int statusCode;
    private String message;
    private long timeStamp = Instant.now().toEpochMilli();

    public CustomerErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public CustomerErrorResponse() {
    }

    //getter and setters
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
