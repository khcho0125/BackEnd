package com.elasticsearch.Domain.API;

import com.elasticsearch.Domain.Dto.UserRequest;
import com.elasticsearch.Domain.Dto.UserRequestDto;
import com.elasticsearch.Domain.Dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Void> save(@RequestBody UserRequest userRequest) {
        UserRequestDto userRequestDto = new UserRequestDto(userRequest.getName(), userRequest.getDescription());
        Long id = userService.save(userRequestDto);
        URI uri = URI.create(String.valueOf(id));
        return ResponseEntity.created(uri)
                .build();
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<List<UserResponse>> search(@PathVariable String name, Pageable pageable) {
        List<UserResponse> userResponses = userService.searchByName(name, pageable)
                .stream().map(UserResponse::from).toList();
        return ResponseEntity.ok(userResponses);
    }
}
