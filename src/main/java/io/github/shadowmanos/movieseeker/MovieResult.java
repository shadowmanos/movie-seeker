package io.github.shadowmanos.movieseeker;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MovieResult {

    private final String title;
    private final String year;
    private final String director;

    @JsonCreator
    public MovieResult(@JsonProperty String title, @JsonProperty String year, @JsonProperty String director) {
        this.title = title;
        this.year = year;
        this.director = director;
    }
}
