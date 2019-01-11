package io.github.shadowmanos.movieseeker;

import io.github.shadowmanos.movieseeker.omdb.SearchOMDb;
import io.github.shadowmanos.movieseeker.themoviedb.SearchTheMovieDB;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
@AllArgsConstructor
public class MovieSearchController {

    private final SearchOMDb searchOMDb;
    private final SearchTheMovieDB searchTheMovieDB;

    @GetMapping(path = "/movies/{movieTitle}")
    public Flux<MovieResult> searchForMovies(
            @PathVariable @NotBlank(message = "movieTitle can't be blank") String movieTitle,
            @RequestParam @NotBlank(message = "api can't be blank") String api,
            @RequestParam(defaultValue = "1") int page) {

        switch (api) {
            case "omdb":
                return searchOMDb.findMoviesByTitle(movieTitle, page);
            case "themoviedb":
                return searchTheMovieDB.findMoviesByTitle(movieTitle, page);
        }
        throw new IllegalArgumentException("unrecognized movie api name");
    }
}
