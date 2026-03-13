package org.self.controller;

import org.self.dto.UserDto;
import org.self.entity.User;
import org.self.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
   private final UserService userService;
   
   @PostMapping("/register")
   public ResponseEntity<User> register(@RequestBody UserDto userDto){
	   User savedUser = userService.registerUser(userDto);
	   return ResponseEntity.ok(savedUser);
   }
   
   
   @GetMapping("/profile")
   public ResponseEntity<String> profile(){
	   return ResponseEntity.ok("User profile accessed successfully");
   }
}
