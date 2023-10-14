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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.src.model.User;
import br.com.serratec.src.service.UserService;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
	UserService service;

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable UUID id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable String username) {
		return ResponseEntity.ok(service.findByUsername(username));
	}

	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<User> findByCpf(@PathVariable String cpf) {
		return ResponseEntity.ok(service.findByCpf(cpf));
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable String email) {
		return ResponseEntity.ok(service.findByEmail(email));
	}

	@GetMapping("/all")
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}

	@PostMapping("/create")
	public ResponseEntity<User> create(@RequestBody User user) {
		return ResponseEntity.status(201).body(service.create(user));
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@RequestBody User user, @PathVariable UUID id) {
		return ResponseEntity.ok(service.update(user, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id) {
		service.delete(id);

		return ResponseEntity.ok("Resource with ID " + id + " has been deleted.");
	}
}
