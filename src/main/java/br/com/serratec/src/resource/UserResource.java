package br.com.serratec.src.resource;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.src.model.User;
import br.com.serratec.src.service.UserService;

@RestController
public class UserResource {

	@Autowired
	UserService service;

	@GetMapping("/user/{username}")
	public User findByUsername(@PathVariable String username) {
		return service.findByUsername(username);
	}

	@GetMapping("/user/{cpf}")
	public User findByCpf(@PathVariable String cpf) {
		return service.findByCpf(cpf);
	}

	@GetMapping("/user/{email}")
	public User findByEmail(@PathVariable String email) {
		return service.findByEmail(email);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@PostMapping("/user")
	public User create(@RequestBody User user) {
		return service.create(user);
	}

	@PutMapping("/user/{id}")
	public User update(@RequestBody User user, @PathVariable UUID id) {
		return service.update(user, id);
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		service.delete(id);

		return ResponseEntity.ok("Resource with ID " + id + " has been deleted.");
	}
}
