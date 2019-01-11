package io.github.shadowmanos.movieseeker.themoviedb;

import io.github.shadowmanos.movieseeker.MovieResult;
import io.github.shadowmanos.movieseeker.MovieSeeker;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class SearchTheMovieDB extends MovieSeeker {

    @Override
    public Flux<MovieResult> findMoviesByTitle(String title, int page) {
        return null;
    }
}
