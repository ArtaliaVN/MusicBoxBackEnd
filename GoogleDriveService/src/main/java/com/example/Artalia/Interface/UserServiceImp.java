package com.example.Artalia.Interface;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Artalia.Dto.UserEventDto;

@FeignClient(name = "user-service")
public interface UserServiceImp {

    @GetMapping("/user/{id}/account")
    public UserEventDto getById(@PathVariable("id") int id);
}
