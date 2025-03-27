package com.example.demo.Controller;

import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.StreamingService;

import reactor.core.publisher.Mono;

@RestController
public class StreamingController {
    private StreamingService streamingService;

    public StreamingController (StreamingService streamingService){
        this.streamingService = streamingService;
    }

    @GetMapping(value = "/audio/stream", produces = "audio/mpeg")
    public Mono<Resource> audioStream(@RequestParam("url") String url, @RequestHeader("Range") String range){
        return streamingService.streamAudio(url);
    }
}
