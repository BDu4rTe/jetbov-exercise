package com.jetbov.exercise;

import com.jetbov.exercise.core.exceptions.CopyFromDto;
import com.jetbov.exercise.core.exceptions.CrowdedArea;
import com.jetbov.exercise.core.exceptions.EntityNotFound;
import com.jetbov.exercise.infra.errors.ApiError;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getHttpStatus());
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        var message = exception.getParameterName() + "parameter is missing";

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, message, exception));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        StringBuilder builder = new StringBuilder();

        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are: ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));

        return buildResponseEntity(
                new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, builder.substring(0, builder.length() - 2), ex));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        var apiError = new ApiError(HttpStatus.BAD_REQUEST);

        apiError.setMessage("Validation Error.");
        apiError.addValidationErrorByFields(ex.getBindingResult().getFieldErrors());
        apiError.addValidationErrorByObjects(ex.getBindingResult().getGlobalErrors());

        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        log.info("{} to {} ", servletWebRequest.getHttpMethod(), servletWebRequest.getRequest().getServletPath());

        String error = "Malformed JSON request.";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
            HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        String error = "Error writing JSON output.";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        var apiError = new ApiError(HttpStatus.BAD_REQUEST);

        apiError.setMessage(
                String.format("Could not find the %s method for URL %s", ex.getHttpMethod(), ex.getRequestURL())
        );
        apiError.setDebugMessage(ex.getMessage());

        return buildResponseEntity(apiError);
    }

    //Additional Default Exception Handler

    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(jakarta.persistence.EntityNotFoundException exception) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, exception));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(
            DataIntegrityViolationException exception, WebRequest request
    ) {
        if (exception.getCause() instanceof ConstraintViolationException) {
            return buildResponseEntity(
                    new ApiError(HttpStatus.CONFLICT, "Database error ", exception.getCause())
            );
        }

        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException exception, WebRequest request
    ) {
        var apiError = new ApiError(HttpStatus.BAD_REQUEST);

        apiError.setMessage(
                String.format(
                        "The parameter '%s' of value '%s' could not be converted to type '%s'",
                        exception.getName(),
                        exception.getValue(),
                        exception.getRequiredType().getSimpleName()
                )
        );
        apiError.setDebugMessage(exception.getMessage());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(jakarta.validation.ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(
            jakarta.validation.ConstraintViolationException exception
    ) {
        var apiError = new ApiError(HttpStatus.BAD_REQUEST);

        apiError.setMessage("Validation Error.");
        apiError.addValidationError(exception.getConstraintViolations());

        return buildResponseEntity(apiError);
    }

    //Custom Exception Handler

    @ExceptionHandler(EntityNotFound.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFound exception) {
        var apiError = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage(), exception.getCause());

        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(CrowdedArea.class)
    protected ResponseEntity<Object> handleCrowdedArea(CrowdedArea exception) {
        var apiError = new ApiError(HttpStatus.CONFLICT, exception.getMessage(), exception.getCause());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(CopyFromDto.class)
    protected ResponseEntity<Object> handleCopyFromDto(CopyFromDto exception) {
        var apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception.getCause());
        return buildResponseEntity(apiError);
    }
}
