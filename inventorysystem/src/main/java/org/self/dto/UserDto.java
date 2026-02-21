package org.self.dto;

import org.self.entity.Role;

import lombok.Data;

@Data
public class UserDto {
	 private String username;
	   private String password;
	   private String email;
	   private String role;
}
