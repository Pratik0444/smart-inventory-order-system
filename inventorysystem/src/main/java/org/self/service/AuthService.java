package org.self.service;

import org.self.dto.UserDto;
import org.self.entity.User;
import org.self.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	 private final AuthenticationManager authenticationManager;
	    private final JWTService jwtService;
   private final UserRepository userRepository;
	    public String login(UserDto request) {

	        Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                        request.getEmail(),
	                        request.getPassword()
	                )
	        );

	        if (authentication.isAuthenticated()) {

	            User user = userRepository.findByEmail(request.getEmail())
	                    .orElseThrow(() -> new RuntimeException("User not found"));

	          
	            return jwtService.generateToken(
	                    user.getEmail(),
	                    user.getRole().name()
	            );
	        }
	        throw new RuntimeException("Invalid credentials");
	    }
}
