package id.ac.ui.cs.advprog.bookubeprofile.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class RestClientConfiguration {

    @Bean
    RestClient restClient() {
        return RestClient.create();
    }
}