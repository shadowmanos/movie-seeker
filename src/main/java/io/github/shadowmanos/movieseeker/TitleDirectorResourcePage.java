package io.github.shadowmanos.movieseeker;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TitleDirectorResourcePage {
    private final int page;
    private final int pageSize;
    private final List<TitleDirectorResource> results;
}
