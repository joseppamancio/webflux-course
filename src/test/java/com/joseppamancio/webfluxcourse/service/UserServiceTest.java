package com.joseppamancio.webfluxcourse.service;

import com.joseppamancio.webfluxcourse.entity.User;
import com.joseppamancio.webfluxcourse.mapper.UserMapper;
import com.joseppamancio.webfluxcourse.model.request.UserRequest;
import com.joseppamancio.webfluxcourse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    @Test
    void testSave() {
        UserRequest request = new UserRequest("Alex", "alexoliveira", "123");
        User entity = User.builder().build();

        when(mapper.toEntity(any(UserRequest.class))).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(Mono.just(User.builder().build()));

        Mono<User> result = service.save(request); // result é um publisher

        StepVerifier.create(result)         // aciona o publisher e faz as verificações
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testFindById(){
        when(repository.findById(anyString())).thenReturn(Mono.just(
                User.builder().id("1234").build()));

        Mono<User> result = service.findById("1234");

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class
                        && Objects.equals(user.getId(),"1234"))
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findById(anyString());
    }

    @Test
    void testFindAll(){
        when(repository.findAll()).thenReturn(Flux.just().just(
                User.builder().build()));

        Flux<User> result = service.findAll();

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    void testUpdate() {
        UserRequest request = new UserRequest("Alex", "alexoliveira", "123");
        User entity = User.builder().build();

        when(mapper.toEntity(any(UserRequest.class), any(User.class))).thenReturn(entity);
        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
        when(repository.save(any(User.class))).thenReturn(Mono.just(entity));

        Mono<User> result = service.update("123", request); // result é um publisher

        StepVerifier.create(result)         // aciona o publisher e faz as verificações
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).save(any(User.class));
    }
}