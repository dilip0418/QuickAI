package com.dilip.springaidemo.controller;

import com.dilip.springaidemo.service.LLMService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/text")
@RequiredArgsConstructor
public class LLMController {

    private final LLMService llmService;


    @Operation(
            summary = "Generate Text",
            description = "Generate AI-powered text based on the provided prompt.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing the prompt for text generation.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    type = "object",
                                    example = "{ \"prompt\": \"Describe the weather today\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully generated text",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    @PostMapping("/generate")
    public Mono<String> generateText(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        return llmService.generateText(prompt);
    }
}
