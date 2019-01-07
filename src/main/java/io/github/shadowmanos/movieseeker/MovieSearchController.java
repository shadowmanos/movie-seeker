package io.github.shadowmanos.movieseeker;

import io.github.shadowmanos.OMDb.SearchOMDb;
import io.github.shadowmanos.themoviedb.SearchTheMovieDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
public class MovieSearchController {

    private static final Map<String, MovieSeeker> MOVIE_APIS =
            Map.of(
                    "OMDb", new SearchOMDb(),
                    "TheMovieDB", new SearchTheMovieDB()
            );

    @GetMapping(path = "/movies/{movieTitle}?api={apiName}")
    public TitleDirectorResourcePage searchForMovies(
            @PathVariable @NotEmpty String movieTitle,
            @RequestParam @NotEmpty String apiName,
            @RequestParam @NotNull int page,
            @RequestParam @NotNull int pageSize) {

        if (!MOVIE_APIS.containsKey(apiName)) {
            throw new IllegalArgumentException("Unrecognized movie api name");
        }

        List<TitleDirectorResource> movies = MOVIE_APIS.get(apiName).findMoviesByTitle(movieTitle, page, pageSize);
        return new TitleDirectorResourcePage(page, pageSize, movies);
    }
}
