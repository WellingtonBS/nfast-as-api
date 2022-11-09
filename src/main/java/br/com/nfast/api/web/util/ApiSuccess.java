package br.com.nfast.api.web.util;

public class ApiSuccess {
    private String message;

    public ApiSuccess() {

    }

    public ApiSuccess(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
