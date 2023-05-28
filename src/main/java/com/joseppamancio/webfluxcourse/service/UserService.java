package com.joseppamancio.webfluxcourse.service;

import com.joseppamancio.webfluxcourse.entity.User;
import com.joseppamancio.webfluxcourse.mapper.UserMapper;
import com.joseppamancio.webfluxcourse.model.request.UserRequest;
import com.joseppamancio.webfluxcourse.repository.UserRepository;
import com.joseppamancio.webfluxcourse.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    public Mono<User> save(final UserRequest request){
        return repository.save(mapper.toEntity(request));
    }

    public Mono<User> findById(final String id){
        return repository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ObjectNotFoundException(format("Object not found. Id: %s, Type %s", id, User.class.getSimpleName()))
                ));
    }
}
