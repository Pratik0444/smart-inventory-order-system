package org.self.controller;

import org.self.dto.UserDto;
import org.self.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {
   private final AuthService authService;
   
   @PostMapping("/login")
   public ResponseEntity<String> login(@RequestBody UserDto request){
	   return ResponseEntity.ok(authService.login(request));
   }
}
