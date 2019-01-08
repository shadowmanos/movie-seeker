package io.github.shadowmanos.movieseeker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class MovieErrorController implements ErrorController {

    @Getter
    @AllArgsConstructor
    public static class ErrorResponse {
        private final String detail;
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            ConstraintViolationException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidInput(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
