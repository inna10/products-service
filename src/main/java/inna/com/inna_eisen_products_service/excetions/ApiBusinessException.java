package inna.com.inna_eisen_products_service.excetions;

import org.springframework.http.HttpStatus;

public abstract class ApiBusinessException extends Exception {
    public ApiBusinessException(String message) {
        super(message);
    }
    abstract public HttpStatus getHttpStatus();
}
