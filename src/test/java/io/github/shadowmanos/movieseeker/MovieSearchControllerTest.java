package io.github.shadowmanos.movieseeker;

import org.hamcrest.core.StringContains;
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
@AutoConfigureWebTestClient(timeout = "36000")
@AutoConfigureWebFlux
public class MovieSearchControllerTest {

    @Autowired
    private WebTestClient testClient;

    @Test
    public void searchForMovies() {
        testClient.get()
                .uri("/movies/batman?api=omdb")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$[0].title").value(StringContains.containsString("atman"))
                .jsonPath("$[1].title").value(StringContains.containsString("atman"));
    }
}
