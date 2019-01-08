package io.github.shadowmanos.movieseeker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@WebFluxTest
public class MovieSearchControllerInvalidInputTest {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void invalidApiName() {
        testClient.get()
                .uri(ub -> ub.path("/movies/movieTitle").queryParam("api", "abc").build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().jsonPath("message", "movieTitle can't be blank");
    }

    @Test
    public void invalidMovieTitle() {
        testClient.get()
                .uri(ub -> ub.path("/movies/ ").queryParam("api", "OMDb").build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest();
    }
}