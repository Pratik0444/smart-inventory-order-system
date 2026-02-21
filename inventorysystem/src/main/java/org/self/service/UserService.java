package org.self.service;


import org.self.dto.UserDto;
import org.self.entity.User;
import org.self.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
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
		user.setRole("ROLE_USER");
	return userRepository.save(user);
	
	   
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found with email: "+ email));
		return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
				.password(user.getPassword())
				.authorities(user.getRole())
				.build();
	}
	
}
