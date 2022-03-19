package com.example.autopartsmall.common.ui.rest.config;

import com.example.autopartsmall.common.application.Response;
import com.example.autopartsmall.common.ddd.exception.DomainEntityNotFoundException;
import com.example.autopartsmall.common.ddd.exception.DomainException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.security.InvalidParameterException;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("缺少请求参数", e);
        String message = "【缺少请求参数】" + e.getMessage();
        return Response.withError(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("参数解析失败", e);
        String message = "【参数解析失败】" + e.getMessage();
        return Response.withError(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidParameterException.class)
    public Response handleInvalidParameterException(InvalidParameterException e) {
        log.error("非法参数", e);
        String message = "【非法参数】" + e.getMessage();
        return Response.withError(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = "【参数验证失败】" + String.format("%s:%s", field, code);
        return Response.withError(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Response handleBindException(BindException e) {
        log.error("参数绑定失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = "【参数绑定失败】" + String.format("%s:%s", field, code);
        return Response.withError(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Response handleValidationException(ValidationException e) {
        log.error("参数验证失败", e);
        String message = "【参数验证失败】" + e.getMessage();
        return Response.withError(HttpStatus.BAD_REQUEST.value(), message);
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Response noHandlerFoundException(NoHandlerFoundException e) {
        log.error("Not Found", e);
        String message = "【页面不存在】" + e.getMessage();
        return Response.withError(HttpStatus.NOT_FOUND.value(), message);
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Response handleEntityNotFoundException(EntityNotFoundException e) {
        log.error("Not Found", e);
        String message = "【资源不存在】" + e.getMessage();
        return Response.withError(HttpStatus.NOT_FOUND.value(), message);
    }

    /**
     * 409 - Conflict
     */
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DomainException.class)
    public Response handleDomainException(DomainException e) {
        log.error("Conflict", e);
        String message = "【资源不存在】" + e.getMessage();
        return Response.withError(HttpStatus.CONFLICT.value(), message);
    }

    /**
     * 404 - Not Found
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DomainEntityNotFoundException.class)
    public Response noHandlerFoundException(DomainEntityNotFoundException e) {
        log.error("Not Found", e);
        String message = "【资源不存在】" + e.getMessage();
        return Response.withError(HttpStatus.NOT_FOUND.value(), message);
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("不支持当前请求方法", e);
        String message = "【不支持当前请求方法】" + e.getMessage();
        return Response.withError(HttpStatus.METHOD_NOT_ALLOWED.value(), message);
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Response handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error("不支持当前媒体类型", e);
        String message = "【不支持当前媒体类型】" + e.getMessage();
        return Response.withError(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), message);
    }
}
