package com.example.demo.Service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class StreamingService {
    private ResourceLoader resourceLoader;

    public StreamingService(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    public Mono<Resource> streamAudio(String url){
        return Mono.fromSupplier(() -> resourceLoader.getResource(url));
    }
}
