package com.dilip.springaidemo.controller;

import com.dilip.springaidemo.service.LLMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/text")
@RequiredArgsConstructor
public class LLMController {

    private final LLMService llmService;

    @PostMapping("/generate")
    public Mono<String> generateText(@RequestBody Map<String, String> request) {
        String prompt = request.get("prompt");
        return llmService.generateText(prompt);
    }
}
