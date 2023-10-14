package br.com.serratec.src.model.mapper;

import br.com.serratec.src.model.User;
import br.com.serratec.src.model.dto.UserDTO;

public class UserMapper {

	public UserDTO toDTO(User user) {
		UserDTO userDTO = new UserDTO(user.getId(), user.getUsername(), user.getCpf(), user.getEmail());

		return userDTO;
	}

	public User toEntity(UserDTO userDTO, String password) {
		User user = new User(userDTO.getId(), userDTO.getCpf(), userDTO.getUsername(), userDTO.getEmail(), password);

		return user;
	}
}
