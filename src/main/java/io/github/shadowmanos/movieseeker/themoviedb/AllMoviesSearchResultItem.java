package io.github.shadowmanos.movieseeker.themoviedb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AllMoviesSearchResultItem {

    private final String title;
    private final String id;

    @JsonCreator
    public AllMoviesSearchResultItem(@JsonProperty String title, @JsonProperty String id) {
        this.title = title;
        this.id = id;
    }
}
