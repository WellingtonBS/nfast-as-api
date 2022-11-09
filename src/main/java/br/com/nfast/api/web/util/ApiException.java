package br.com.nfast.api.web.util;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private HttpStatus status;

    public ApiException(HttpStatus status, String msg) {
        super(msg);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
