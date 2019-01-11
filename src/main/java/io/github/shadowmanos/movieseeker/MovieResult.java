package io.github.shadowmanos.movieseeker;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MovieResult {
    private final String title;
    private final String director;

    @JsonCreator
    public MovieResult(@JsonProperty String title, @JsonProperty String director) {
        this.title = title;
        this.director = director;
    }
}
