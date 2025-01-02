package com.dilip.springaidemo.controller;

import com.dilip.springaidemo.service.ImageGenerationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/image")
@RequiredArgsConstructor
public class ImageGenerationController {

    private final ImageGenerationService imageGenerationService;

    @Operation(
            summary = "Generate Image",
            description = "Generate an AI-generated image based on the provided text prompt.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Request body containing the prompt to generate an image.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @io.swagger.v3.oas.annotations.media.Schema(
                                    type = "object",
                                    example = "{ \"prompt\": \"Generate an image of a sunset over the ocean\" }"
                            )
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully generated image",
                            content = @Content(mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE) // byte[] response type
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid input",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    )
            }
    )
    @PostMapping("/generate")
    public Mono<ResponseEntity<byte[]>> generateImage(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");

        return imageGenerationService.generateImage(prompt)
                .map(imageBytes -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"generated-image.png\"")
                        .contentType(MediaType.IMAGE_PNG)
                        .body(imageBytes));
    }
}
