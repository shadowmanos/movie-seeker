package io.github.shadowmanos.movieseeker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MovieSearchControllerInvalidInputTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void invalidApiName() throws Exception {
        mockMvc.perform(get("/movies/movieTitle")
                .param("api", "abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void invalidMovieTitle() throws Exception {
        mockMvc.perform(get("/movies/%20")
                .param("api", "OMDb"))
                .andExpect(status().isBadRequest());
    }
}