package com.api.Controller;

import com.api.Dto.UserDto;
import com.api.Entity.User;
import com.api.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userSerivce;

    public UserController(UserService userService) {
        this.userSerivce = userService;
    }

    @PostMapping("/signup") // userService 의 signup 메소드 호출
    public ResponseEntity<User> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userSerivce.signup(userDto));
    }

    @GetMapping("/user") //
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')") // 두가지 권한 모두 허용
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userSerivce.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 호출 가능
    public ResponseEntity<User> getUserInfo(@PathVariable String username) { // 유저 정보와 권한 정보를 리턴받는 API
        return ResponseEntity.ok(userSerivce.getUserWithAuthorities(username).get());
    }
}
