package com.avocado.tatevik.retail.common.exception.handler;

import com.avocado.tatevik.retail.common.exception.enums.ExceptionCode;
import com.avocado.tatevik.retail.common.exception.exceptions.DomainNotValidException;
import com.avocado.tatevik.retail.common.exception.response.ErrorResponseDto;
import com.avocado.tatevik.retail.common.exception.response.ErrorResponseListDto;
import com.avocado.tatevik.retail.common.exception.response.GenericResponse;
import com.avocado.tatevik.retail.controller.address.AddressCRUDController;
import com.avocado.tatevik.retail.controller.customer.CustomerCRUDController;
import com.avocado.tatevik.retail.controller.order.OrderCRUDController;
import com.avocado.tatevik.retail.controller.order.OrdersSearchController;
import com.avocado.tatevik.retail.controller.orderproduct.OrderProductCRUDController;
import com.avocado.tatevik.retail.controller.product.ProductCRUDController;
import com.avocado.tatevik.retail.controller.shop.ShopCRUDController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice(basePackageClasses = {
        AddressCRUDController.class,
        CustomerCRUDController.class,
        OrderCRUDController.class,
        OrderProductCRUDController.class,
        ProductCRUDController.class,
        OrdersSearchController.class,
        ShopCRUDController.class})
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse<Object> handleDomainNotValidException(DomainNotValidException ex) {
        ErrorResponseListDto errorResponses = new ErrorResponseListDto();
        String message = ex.getMessage();
        ExceptionCode code = ex.getCode();
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(message, code);
        errorResponses.add(errorResponseDto);
        return new GenericResponse<>(null, errorResponses);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        ErrorResponseListDto errorResponse = new ErrorResponseListDto();
        errorResponse.add(new ErrorResponseDto(ex.getMessage(), ExceptionCode.UUTI_45));
        return new GenericResponse<>(null, errorResponse);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponseListDto errorResponse = new ErrorResponseListDto();
        errorResponse.add(new ErrorResponseDto(ex.getMessage(), ExceptionCode.UUTI_45));
        return new GenericResponse<>(null, errorResponse);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericResponse<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        ErrorResponseListDto errorResponse = new ErrorResponseListDto();
        errorResponse.add(new ErrorResponseDto(ex.getMessage(), ExceptionCode.UUTI_45));
        return new GenericResponse<>(null, errorResponse);
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse<Object> handleBindException(BindException ex) {
        ErrorResponseListDto errorResponse = new ErrorResponseListDto();
        for (ObjectError e : ex.getBindingResult().getAllErrors()) {
            errorResponse.add(new ErrorResponseDto(e.getDefaultMessage(), ExceptionCode.UUTI_45));
        }
        return new GenericResponse<>(null, errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse<Object> handleConstraintViolation(ConstraintViolationException ex) {
        ErrorResponseListDto errorResponse = new ErrorResponseListDto();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errorResponse.add(new ErrorResponseDto(
                    (violation.getRootBeanClass().getName() + " ; " + violation.getPropertyPath() + ": " + violation.getMessage()),
                    ExceptionCode.UUTI_45));
        }
        return new GenericResponse<>(null, errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponseListDto errorResponse = new ErrorResponseListDto();
        errorResponse.add(new ErrorResponseDto(ex.getMessage(), ExceptionCode.UUTI_45));
        return new GenericResponse<>(null, errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericResponse<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorResponseListDto errorResponse = new ErrorResponseListDto();
        errorResponse.add(new ErrorResponseDto(ex.getMessage(), ExceptionCode.UUTI_45));
        return new GenericResponse<>(null, errorResponse);
    }
}