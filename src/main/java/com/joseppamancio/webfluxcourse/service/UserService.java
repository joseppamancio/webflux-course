package com.joseppamancio.webfluxcourse.service;

import com.joseppamancio.webfluxcourse.entity.User;
import com.joseppamancio.webfluxcourse.mapper.UserMapper;
import com.joseppamancio.webfluxcourse.model.request.UserRequest;
import com.joseppamancio.webfluxcourse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    public Mono<User> save(final UserRequest request){
        return repository.save(mapper.toEntity(request));
    }

    public Mono<User> findById(final String id){
        return repository.findById(id);
    }
}
