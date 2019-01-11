package io.github.shadowmanos.movieseeker;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.codec.CodecCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.CodecConfigurer.CustomCodecs;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
public class AppConfig {
    @Bean
    public CodecCustomizer jacksonJsonCustomizer(ObjectMapper mapper) {
        mapper.enable(ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return (configurer) -> {
            CustomCodecs customCodecs = configurer.customCodecs();
            customCodecs.decoder(new Jackson2JsonDecoder(mapper, APPLICATION_JSON));
            customCodecs.encoder(new Jackson2JsonEncoder(mapper, APPLICATION_JSON));
        };
    }
}
