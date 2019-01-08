package io.github.shadowmanos.themoviedb;

import io.github.shadowmanos.movieseeker.MovieSeeker;
import io.github.shadowmanos.movieseeker.TitleDirectorResource;

import java.util.List;

public class SearchTheMovieDB implements MovieSeeker {

    private static final int API_PAGE_SIZE = 20;

    @Override
    public List<TitleDirectorResource> findMoviesByTitle(String title, int page, int pageSize) {
        return null;
    }
}
