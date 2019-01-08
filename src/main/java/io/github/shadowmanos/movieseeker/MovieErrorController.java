package io.github.shadowmanos.movieseeker;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MovieErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
