package io.github.shadowmanos.movieseeker.omdb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
class SearchResultItem {

    private final String imdbid;

    @JsonCreator
    public SearchResultItem(@JsonProperty String imdbid) {
        this.imdbid = imdbid;
    }
}
