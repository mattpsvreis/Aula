package br.com.serratec.src.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "cpf", nullable = false, unique = true)
	private String cpf;

	@Size(min = 4, max = 26, message = "Username must be between 4 and 26 characters")
	@Column(name = "username", nullable = false, unique = false)
	private String username;

	@Column(name = "password", nullable = false, unique = false)
	private String password;
}
