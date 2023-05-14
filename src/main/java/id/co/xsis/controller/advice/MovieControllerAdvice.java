package id.co.xsis.controller.advice;

import id.co.xsis.dto.MessageDto;
import id.co.xsis.dto.ResponseDto;
import id.co.xsis.exception.MovieAlreadyExistsExceptions;
import id.co.xsis.exception.MovieNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.logging.Logger;

@RestControllerAdvice
public class MovieControllerAdvice {

    private final Logger log = Logger.getLogger(MovieControllerAdvice.class.getName());

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        List<MessageDto> errors = getErrors(e.getBindingResult().getFieldErrors());
        return ResponseDto.err(errors);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseDto movieNotFoundExceptionHandler(MovieNotFoundException e) {
        return ResponseDto.err(e.getMessage());
    }

    @ExceptionHandler(MovieAlreadyExistsExceptions.class)
    public ResponseDto movieAlreadyExistsExceptions(MovieAlreadyExistsExceptions e) {
        return ResponseDto.err(e.getMessage());
    }

    private List<MessageDto> getErrors(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(FieldError -> new MessageDto(FieldError.getField(), FieldError.getDefaultMessage()))
                .toList();
    }

}
