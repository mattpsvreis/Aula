package br.com.serratec.src.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;

	@Size(min = 4, max = 26, message = "Username must be between 4 and 26 characters")
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Column(name = "password", nullable = false, unique = false)
	private String password;
	
	public User(UUID id, String cpf,
			@Size(min = 4, max = 26, message = "Username must be between 4 and 26 characters") String username,
			@Email String email, String password) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
