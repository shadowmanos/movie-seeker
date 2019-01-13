package io.github.shadowmanos.movieseeker.themoviedb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class CrewMember {

    private final String job;
    private final String name;

    @JsonCreator
    public CrewMember(@JsonProperty String job, @JsonProperty String name) {
        this.job = job;
        this.name = name;
    }
}
