package ai.typeface.documentShare.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {DocumentAccessException.class})
    protected ResponseEntity<Object> handleDocumentAccessException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "You don't have access to this link";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED
                , request);
    }

    @ExceptionHandler(value = {DocumentLinkExpiredException.class})
    protected ResponseEntity<Object> handleDocumentLinkExpiredException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Document link got expired";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST
                , request);
    }

    @ExceptionHandler(value = {FileUploadFailureException.class})
    protected ResponseEntity<Object> handleFileUploadFailureException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Upload Failed. Please try again!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
                , request);
    }

    @ExceptionHandler(value = {DocumentNotFoundException.class})
    protected ResponseEntity<Object> handleDocumentNotFoundException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Document not found with given id!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST
                , request);
    }


}
