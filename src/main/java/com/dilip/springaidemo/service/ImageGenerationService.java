package com.dilip.springaidemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
public class ImageGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(ImageGenerationService.class);
    private final WebClient webClient;

    public ImageGenerationService(@Value("${huggingface.image.api.url}") String apiUrl,
                                  @Value("${huggingface.api.key}") String apiKey) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Mono<byte[]> generateImage(String prompt) {
        if (prompt == null || prompt.trim().isEmpty()) {
            return Mono.error(new IllegalArgumentException("Prompt must not be null or empty"));
        }

        Map<String, String> body = Collections.singletonMap("inputs", prompt);

        return webClient.post()
                .bodyValue(body)
                .retrieve()
                .bodyToMono(byte[].class)
                .timeout(Duration.ofSeconds(10)) // Timeout after 10 seconds
                .retryWhen(Retry.backoff(3, Duration.ofMillis(500))) // Retry logic
                .doOnSuccess(response -> logger.info("Image generated successfully for prompt: {}", prompt))
                .doOnError(error -> logger.error("Error generating image for prompt: {}", prompt, error))
                .onErrorResume(WebClientResponseException.class, ex -> {
                    logger.error("HTTP error during image generation: {}", ex.getMessage(), ex);
                    return Mono.error(new RuntimeException("Error generating image: " + ex.getMessage()));
                })
                .onErrorResume(TimeoutException.class, ex -> {
                    logger.error("Timeout while generating image for prompt: {}", prompt);
                    return Mono.error(new RuntimeException("Request timed out"));
                });
    }
}
