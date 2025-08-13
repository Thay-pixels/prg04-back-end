package com.rpg.rpg_app.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Value(value = "${server.error.include-exception}")
    private boolean printStackTrace;

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBusinessException(
            final BusinessException businessException,
            final WebRequest request) {

        final String mensagemErro = businessException.getMessage();

        logger.error( mensagemErro, businessException);

        return construirMensagemDeErro (
                businessException,
                mensagemErro,
                HttpStatus.BAD_REQUEST,
                request);
    }

    private ResponseEntity<Object> construirMensagemDeErro(
            final Exception exception,
            final String message,
            final HttpStatus httpStatus,
            final WebRequest request) {

        ErrorResponse errorResponse =
                new ErrorResponse(httpStatus.value(), message, null);

        if (this.printStackTrace) {
            errorResponse.setStackTrace(
                    ExceptionUtils.getStackTrace(exception));
        }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

}
