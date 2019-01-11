package io.github.shadowmanos.movieseeker;

import reactor.core.publisher.Flux;

public abstract class MovieSeeker {
    public abstract Flux<MovieResult> findMoviesByTitle(String title, int page);
}
