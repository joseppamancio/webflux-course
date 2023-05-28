package com.joseppamancio.webfluxcourse.controller;

import com.joseppamancio.webfluxcourse.model.request.UserRequest;
import com.joseppamancio.webfluxcourse.model.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserController {
    @PostMapping
    ResponseEntity<Mono<Void>> save(@RequestBody UserRequest request);

    @GetMapping("/{id}")
    ResponseEntity<Mono<UserResponse>> find(@PathVariable String id);

    @GetMapping
    ResponseEntity<Flux<UserResponse>> findAll();

    @PatchMapping("/{id}")
    ResponseEntity<Mono<UserResponse>> update(@PathVariable String id, @RequestBody UserRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Mono<Void>> delet(@PathVariable String id);
}
