package com.findork.preclinical.exceptions;

import com.findork.preclinical.utils.httpresponse.HttpResponse;
import com.findork.preclinical.utils.httpresponse.HttpResponseUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionHandling {
    private static final String INCORRECT_CREDENTIALS = "Username / password incorrect. Please try again";
    private static final String ACCOUNT_DISABLED = "Your account has been disabled. If this is an error, please contact administration";
    private static final String METHOD_IS_NOT_ALLOWED = "This request method is not allowed on this endpoint. Please send a '%s' request";

    @Autowired
    private HttpResponseUtil httpResponseUtil;

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<HttpResponse> alreadyExistsException(AlreadyExistsException exception) {
        log.error(exception.getMessage());
        return httpResponseUtil.createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<HttpResponse> alreadyExistsException(CustomException exception) {
        log.error(exception.getMessage());
        return httpResponseUtil.createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(InvalidConfirmTokenException.class)
    public ResponseEntity<HttpResponse> alreadyExistsException(InvalidConfirmTokenException exception) {
        log.error(exception.getMessage());
        return httpResponseUtil.createHttpResponse(BAD_REQUEST, exception.getMessage());
    }


    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return httpResponseUtil.createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return httpResponseUtil.createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<HttpResponse> expiredJwtException(ExpiredJwtException exception) {
        log.error(exception.getMessage());
        return httpResponseUtil.createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return httpResponseUtil.createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(MethodArgumentNotValidException exception) {
        return httpResponseUtil.createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(NotFoundException exception) {
        return httpResponseUtil.createHttpResponse(BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<HttpResponse> methodValidationException(ValidationException exception) {
        return httpResponseUtil.createHttpResponse(BAD_REQUEST, exception.getMessage());
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpResponse> methodAuthenticationException(AuthenticationException exception) {
        return httpResponseUtil.createHttpResponse(UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<HttpResponse> methodPermissionDeniedException(PermissionDeniedException exception) {
        return httpResponseUtil.createHttpResponse(UNAUTHORIZED, exception.getMessage());
    }
}
