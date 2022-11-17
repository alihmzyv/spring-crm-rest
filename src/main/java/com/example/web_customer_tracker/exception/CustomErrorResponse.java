package com.example.web_customer_tracker.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CustomErrorResponse {
    private long timeStamp = Instant.now().toEpochMilli();
    private List<String> messages;
    private int statusCode;

    public CustomErrorResponse(String message, int statusCode) {
        this.messages = new ArrayList<>();
        this.messages.add(message);
        this.statusCode = statusCode;
    }

    public CustomErrorResponse(List<String> messages, int statusCode) {
        this.messages = messages;
        this.statusCode = statusCode;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
