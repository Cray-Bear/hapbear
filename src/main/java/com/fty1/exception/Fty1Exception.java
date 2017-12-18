package com.fty1.exception;

public class Fty1Exception extends RuntimeException {

    private Fty1ErrorCode fty1ErrorCode;

    private String[] formatArgs;

    public Fty1Exception(Fty1ErrorCode fty1ErrorCode, String[] formatArgs) {
        this.fty1ErrorCode = fty1ErrorCode;
        this.formatArgs = formatArgs;
    }

    public Fty1Exception(String message, Fty1ErrorCode fty1ErrorCode, String[] formatArgs) {
        super(message);
        this.fty1ErrorCode = fty1ErrorCode;
        this.formatArgs = formatArgs;
    }

    public Fty1Exception(String message, Throwable cause, Fty1ErrorCode fty1ErrorCode, String[] formatArgs) {
        super(message, cause);
        this.fty1ErrorCode = fty1ErrorCode;
        this.formatArgs = formatArgs;
    }

    public Fty1Exception(Throwable cause, Fty1ErrorCode fty1ErrorCode, String[] formatArgs) {
        super(cause);
        this.fty1ErrorCode = fty1ErrorCode;
        this.formatArgs = formatArgs;
    }

    public Fty1Exception(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Fty1ErrorCode fty1ErrorCode, String[] formatArgs) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.fty1ErrorCode = fty1ErrorCode;
        this.formatArgs = formatArgs;
    }

    public String getMessage() {
        String fty1ErrorCode = this.getErrorCode();
        return this.formatArgs != null ? "[" + fty1ErrorCode + "] " + String.format(this.fty1ErrorCode.getMessage(), (Object[])this.formatArgs) : "[" + fty1ErrorCode + "] " + this.fty1ErrorCode.getMessage();
    }

    public String getErrorCode() {
        return this.fty1ErrorCode.getCode();
    }
}
