package ninja.backend.web.rest.exception;

import javax.annotation.Nonnull;


public class BadRequestError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;

    private final String errorMessage;

    public BadRequestError(@Nonnull String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}