package io.github.shadowmanos.movieseeker.omdb;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SearchResult {
    private List<SearchResultItem> search;

    @JsonCreator
    public SearchResult(@JsonProperty List<SearchResultItem> search) {
        this.search = search;
    }
}
