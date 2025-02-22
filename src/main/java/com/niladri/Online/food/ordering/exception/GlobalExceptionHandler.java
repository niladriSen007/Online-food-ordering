package com.niladri.Online.food.ordering.exception;


import com.niladri.Online.food.ordering.dto.response.error.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMyMethodArgumentNotValidException(
			MethodArgumentNotValidException e)
	{
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserAlreadyExists.class)
	public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistException
			(UserAlreadyExists ex, WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				ex.getMessage(),
				HttpStatus.BAD_REQUEST,
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
	};

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException
			(ResourceNotFound ex, WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				ex.getMessage(),
				HttpStatus.NOT_FOUND,
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	};


	@ExceptionHandler(IngredientCategoryAlreadyExists.class)
	public ResponseEntity<ErrorResponseDto> handleIngredientCategoryAlreadyExistsException
			(IngredientCategoryAlreadyExists ex, WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				ex.getMessage(),
				HttpStatus.NOT_FOUND,
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	};

	@ExceptionHandler(ResturantAlreadyExist.class)
	public ResponseEntity<ErrorResponseDto> handleResturantAlreadyExistsException
			(ResturantAlreadyExist ex, WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				ex.getMessage(),
				HttpStatus.NOT_FOUND,
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	};


	@ExceptionHandler(FoodCategoryAlreadyExists.class)
	public ResponseEntity<ErrorResponseDto> handleFoodCategoryAlreadyExistsException
			(FoodCategoryAlreadyExists ex, WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				ex.getMessage(),
				HttpStatus.NOT_FOUND,
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	};

	@ExceptionHandler(IngredientAlreadyExists.class)
	public ResponseEntity<ErrorResponseDto> handleIngredientAlreadyExistsException
			(IngredientAlreadyExists ex, WebRequest webRequest) {
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				ex.getMessage(),
				HttpStatus.NOT_FOUND,
				LocalDateTime.now()
		);

		return new ResponseEntity<>(errorResponseDto, HttpStatus.NOT_FOUND);
	};

}
