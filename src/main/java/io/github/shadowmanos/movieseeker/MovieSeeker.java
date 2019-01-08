package io.github.shadowmanos.movieseeker;

import java.util.List;

public interface MovieSeeker {

    List<TitleDirectorResource> findMoviesByTitle(String title, int page, int pageSize);
}
