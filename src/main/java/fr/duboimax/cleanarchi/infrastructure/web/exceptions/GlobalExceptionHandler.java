package fr.duboimax.cleanarchi.infrastructure.web.exceptions;

import fr.duboimax.cleanarchi.application.dtos.responses.ApiErrorResponse;
import fr.duboimax.cleanarchi.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleEmailAlreadyExists(EmailAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiErrorResponse.error(409, e.getMessage()));
    }

    @ExceptionHandler(LogoAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleLogoAlreadyExists(LogoAlreadyExistsException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiErrorResponse.error(409, e.getMessage()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidCredentials(InvalidCredentialsException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ApiErrorResponse.error(401, e.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiErrorResponse.error(404, e.getMessage()));
    }

    @ExceptionHandler(LogoNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleLogoNotFound(LogoNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiErrorResponse.error(404, e.getMessage()));
    }

    @ExceptionHandler(TierListNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleTierListNotFound(TierListNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiErrorResponse.error(404, e.getMessage()));
    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidEmailFormat(InvalidEmailFormatException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiErrorResponse.error(400, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGenericException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiErrorResponse.error(500, "Internal server error"));
    }
}