package br.com.serratec.src.model.dto;

import java.util.UUID;

public class UserDTO {

	private UUID id;
	private String username;
	private String cpf;
	private String email;
	private String token;

	public UserDTO(UUID id, String username, String cpf, String email) {
		this.id = id;
		this.username = username;
		this.cpf = cpf;
		this.email = email;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
