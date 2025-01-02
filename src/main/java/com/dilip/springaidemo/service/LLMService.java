package com.dilip.springaidemo.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;

@Service
public class LLMService {
    private final WebClient webClient;
    private static final Logger logger = LoggerFactory.getLogger(LLMService.class);

    public LLMService(@Value("${huggingface.text.api.url}") String apiUrl,
                      @Value("${huggingface.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Mono<String> generateText(String prompt) {
        if (prompt == null || prompt.trim().isEmpty()) {
            return Mono.error(new IllegalArgumentException("Prompt must not be null or empty"));
        }

        Map<String, String> body = Collections.singletonMap("inputs", prompt);

        return webClient.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> logger.info("Response received: {}", response))
                .doOnError(error -> logger.error("Error during API call", error))
                .retryWhen(Retry.backoff(3, Duration.ofMillis(500)))
                .timeout(Duration.ofSeconds(5))
                .onErrorResume(error -> Mono.just("Fallback response due to error: " + error.getMessage()));
    }
}
