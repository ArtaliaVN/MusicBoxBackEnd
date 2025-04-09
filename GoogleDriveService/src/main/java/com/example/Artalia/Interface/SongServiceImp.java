package com.example.Artalia.Interface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Artalia.Dto.SongEventDto;

@FeignClient(name = "song-service")
public interface SongServiceImp {

    @GetMapping("/song/{id}/item")
    public SongEventDto getById(@PathVariable("id") int id);
}
