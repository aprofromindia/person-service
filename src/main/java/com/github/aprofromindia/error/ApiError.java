package com.github.aprofromindia.error;

import lombok.Getter;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Getter
class ApiError {
    private final Error code;
    private final String message;

    ApiError(Error code, Exception ex) {
        this.code = code;
        this.message = ExceptionUtils.getRootCauseMessage(ex);
    }

    @Override
    public String toString() {
        return code.getCode() + " : " + message;
    }
}
