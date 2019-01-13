package io.github.shadowmanos.movieseeker.themoviedb;

import io.github.shadowmanos.movieseeker.MovieResult;
import io.github.shadowmanos.movieseeker.MovieSeeker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class SearchTheMovieDB extends MovieSeeker {

    private final String apiKey;
    private final WebClient client;

    public SearchTheMovieDB(@Value("${themoviedb.api.key}") String apiKey, WebClient.Builder webClientBuilder) {
        this.apiKey = apiKey;
        this.client = webClientBuilder
                .baseUrl("https://api.themoviedb.org/3")
                .defaultHeader(ACCEPT, APPLICATION_JSON_VALUE)
                .build();
    }

    @Override
    public Flux<MovieResult> findMoviesByTitle(String title, int page) {
        var searchResult = searchByMovieTitle(title, page);
        return retrieveAllMovies(searchResult);
    }

    private Flux<AllMoviesSearchResultItem> searchByMovieTitle(String title, int page) {
        return client.get()
                .uri("/search/movie?api_key=" + apiKey + "&query=" + title + "&page=" + page)
                .retrieve()
                .bodyToMono(AllMoviesSearchResult.class)
                .flatMapIterable(AllMoviesSearchResult::getResults);
    }

    private Flux<MovieResult> retrieveAllMovies(Flux<AllMoviesSearchResultItem> searchResults) {
        return searchResults.flatMap(movie -> client.get().uri("/movie/" + movie.getId() + "/credits?api_key=" + apiKey)
                .retrieve()
                .bodyToMono(MovieCrewResult.class)
                .flatMapIterable(MovieCrewResult::getCrew)
                .filter(crewMember -> "Director".equalsIgnoreCase(crewMember.getJob()))
                .map(crewMember -> new MovieResult(movie.getTitle(), crewMember.getName())));
    }
}
