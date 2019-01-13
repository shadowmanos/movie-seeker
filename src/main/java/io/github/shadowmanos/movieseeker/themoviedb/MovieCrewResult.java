package io.github.shadowmanos.movieseeker.themoviedb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class MovieCrewResult {

    private final List<CrewMember> crew;

    @JsonCreator
    public MovieCrewResult(@JsonProperty List<CrewMember> crew) {
        this.crew = crew;
    }
}
