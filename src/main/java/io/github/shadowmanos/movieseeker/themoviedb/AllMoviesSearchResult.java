package io.github.shadowmanos.movieseeker.themoviedb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class AllMoviesSearchResult {

    private List<AllMoviesSearchResultItem> results;

    @JsonCreator
    public AllMoviesSearchResult(@JsonProperty List<AllMoviesSearchResultItem> results) {
        this.results = results;
    }
}
