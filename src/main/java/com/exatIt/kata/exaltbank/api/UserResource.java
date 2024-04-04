package com.exatIt.kata.exaltbank.api;

import com.exatIt.kata.exaltbank.api.dto.UserDto;
import com.exatIt.kata.exaltbank.api.mappers.IMapper;
import com.exatIt.kata.exaltbank.domain.user.UserQueries;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserResource {

    private final UserQueries userQueries;
    private final IMapper mapper;

    public UserResource(UserQueries userQueries, IMapper mapper) {
        this.userQueries = userQueries;
        this.mapper = mapper;
    }
    @GetMapping("/{id}")
    public ResponseEntity read(@PathVariable Long id){
        UserDto userDto = mapper.domainToDto(userQueries.read(id));
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/{name}/{surname}")
    public ResponseEntity test(@PathVariable String name, @PathVariable String surname){
        return ResponseEntity.ok("hello "+ name+" , the web service is up");
    }

    @PostMapping("")
    public ResponseEntity create(@RequestBody UserDto userDto, HttpServletRequest request){
        Long newUserId =userQueries.create(mapper.dtoToDomain(userDto));
        if(newUserId==null){
            return ResponseEntity.internalServerError().body("User has not been created !");
        }
        String requestURI = request.getRequestURI();
        String host = request.getRequestURL().toString().replace(requestURI, "");
        URI location = ServletUriComponentsBuilder
                .fromUriString(host)
                .path("/users/" + newUserId)
                .build()
                .toUri();
        return ResponseEntity.created(location).build();

    }
}
