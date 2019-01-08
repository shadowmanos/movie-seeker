package io.github.shadowmanos.OMDb;

import io.github.shadowmanos.movieseeker.MovieSeeker;
import io.github.shadowmanos.movieseeker.TitleDirectorResource;

import java.util.List;

public class SearchOMDb implements MovieSeeker {

    private static final int API_PAGE_SIZE = 10;

    @Override
    public List<TitleDirectorResource> findMoviesByTitle(String title, int page, int pageSize) {
        return null;
    }
}
