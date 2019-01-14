package io.github.shadowmanos.movieseeker.themoviedb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AllMoviesSearchResultItem {

    private final String title;
    private final String year;
    private final String id;

    @JsonCreator
    public AllMoviesSearchResultItem(@JsonProperty String title, @JsonProperty("release_date") String date, @JsonProperty String id) {
        this.title = title;
        this.year = date.substring(0, 4);
        this.id = id;
    }
}
