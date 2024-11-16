package inna.com.inna_eisen_products_service.excetions;

import org.springframework.http.HttpStatus;

public class ApiResourceNotFoundException extends ApiBusinessException {

    public ApiResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
