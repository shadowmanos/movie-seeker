package io.github.shadowmanos.movieseeker.omdb;

import io.github.shadowmanos.movieseeker.MovieResult;
import io.github.shadowmanos.movieseeker.MovieSeeker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class SearchOMDb extends MovieSeeker {

    private final String apiKey;
    private final WebClient client;

    public SearchOMDb(@Value("${omdb.api.key}") String apiKey, WebClient.Builder webClientBuilder) {
        this.apiKey = apiKey;
        this.client = webClientBuilder
                .baseUrl("https://www.omdbapi.com")
                .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Flux<MovieResult> findMoviesByTitle(String title, int page) {
        Flux<SearchResultItem> searchResult = searchByMovieTitle(title, page);
        return retrieveAllMovies(searchResult);
    }

    private Flux<SearchResultItem> searchByMovieTitle(String title, int page) {
        String uri = "/?apikey=" + apiKey + "&s=" + title + "&page=" + page + "type=movie";
        return client.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(SearchResult.class)
                .flatMapIterable(SearchResult::getSearch);
    }

    private Flux<MovieResult> retrieveAllMovies(Flux<SearchResultItem> searchResults) {
        return searchResults
                .map(SearchResultItem::getImdbid)
                .flatMap(i -> client.get().uri("/?apikey=" + apiKey + "&i=" + i)
                        .retrieve()
                        .bodyToFlux(MovieResult.class));
    }
}
