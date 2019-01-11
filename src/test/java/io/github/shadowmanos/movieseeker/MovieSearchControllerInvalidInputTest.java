package io.github.shadowmanos.movieseeker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebFlux;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
@AutoConfigureWebFlux
public class MovieSearchControllerInvalidInputTest {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void unknownApiName() {
        testClient.get()
                .uri(ub -> ub.path("/movies/movieTitle").queryParam("api", "abc").build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().jsonPath("detail", "unrecognized movie api name");
    }

    @Test
    public void blankApiName() {
        testClient.get()
                .uri(ub -> ub.path("/movies/movieTitle").queryParam("api", " ").build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().jsonPath("detail", "api can't be blank");
    }

    @Test
    public void blankMovieTitle() {
        testClient.get()
                .uri(ub -> ub.path("/movies/ ").queryParam("api", "omdb").build())
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody().jsonPath("detail", "movieTitle can't be blank");
    }
}