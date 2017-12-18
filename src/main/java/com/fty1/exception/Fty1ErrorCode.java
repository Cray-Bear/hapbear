package com.fty1.exception;

public class Fty1ErrorCode {

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public Fty1ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}


