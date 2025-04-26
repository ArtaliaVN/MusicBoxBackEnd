package com.example.Artalia.Interface;

import com.example.Artalia.Dto.UserEventDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserServiceImp {

    @GetMapping("/user")
    public ResponseEntity<?> postUser(@RequestBody UserEventDto userDto);
}
