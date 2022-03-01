package com.dtw.betTracker.error;

import javax.validation.ConstraintViolation;

import org.springframework.validation.FieldError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiValidationError extends ApiSubError {

	private String field;
	private String targetType;
	private Object rejectedValue;
	private String message;

	public ApiValidationError(String targetType, String message) {
		this.targetType = targetType;
		this.message = message;
	}

	public ApiValidationError(Class<?> clazz, FieldError fieldError) {
		this.targetType = clazz.getSimpleName();
		this.field = fieldError.getField();
		this.rejectedValue = fieldError.getRejectedValue();
		this.message = fieldError.getDefaultMessage();
	}

	public ApiValidationError(ConstraintViolation<?> violation) {

		this.targetType = violation.getRootBeanClass().getSimpleName();
		this.field = violation.getPropertyPath().toString();
		this.rejectedValue = violation.getInvalidValue();
		this.message = violation.getMessage();
	}
}