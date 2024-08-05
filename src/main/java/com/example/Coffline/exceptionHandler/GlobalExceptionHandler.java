package com.example.Coffline.exceptionHandler;

import com.example.Coffline.exception.BaseException;
import com.example.Coffline.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        BaseResponse<Void> response = new BaseResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                messageSource.getMessage("coffline.error.validation", null, Locale.getDefault()),
                System.currentTimeMillis()
        );
        response.setErrors(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Void>> handleBaseExceptions(BaseException ex) {
        BaseResponse<Void> response = new BaseResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
