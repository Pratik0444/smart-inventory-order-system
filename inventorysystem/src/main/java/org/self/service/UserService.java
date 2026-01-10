package org.self.service;


import org.self.dto.UserDto;
import org.self.entity.User;
import org.self.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public User registerUser(UserDto userDto) {
		
		  if (userDto.getUsername() == null || userDto.getUsername().trim().isEmpty()) {
	            throw new IllegalArgumentException("Name cannot be empty");
	        }

	        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
	            throw new IllegalArgumentException("Email cannot be empty");
	        }

	        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
	            throw new IllegalArgumentException("Password cannot be empty");
	        }
	        
	        if(userRepository.existsByEmail(userDto.getEmail())) {
				throw new RuntimeException("Email already exists");
			}
	
		User user = new User();
		user.setName(userDto.getUsername());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setEmail(userDto.getEmail());
		user.setRole(userDto.getRole());
	return userRepository.save(user);
		
	   
	}
	
}
