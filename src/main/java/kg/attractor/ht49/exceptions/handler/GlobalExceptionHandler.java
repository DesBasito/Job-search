package kg.attractor.ht49.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse noSuchElementException(NoSuchElementException e){
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND,e.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse validationHandler(MethodArgumentNotValidException ex){
        return ErrorResponse.builder(ex,HttpStatus.BAD_REQUEST,ex.getMessage()).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse AlreadyExistsException(IllegalArgumentException ex){
        return ErrorResponse.builder(ex,HttpStatus.CONFLICT,ex.getMessage()).build();
    }
}
