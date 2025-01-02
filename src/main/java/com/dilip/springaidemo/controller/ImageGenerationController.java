package com.dilip.springaidemo.controller;

import com.dilip.springaidemo.service.ImageGenerationService;
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
