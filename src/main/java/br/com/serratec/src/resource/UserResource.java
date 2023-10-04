package br.com.serratec.src.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("/users")
  public ResponseEntity<List<User>> findAll() {
    return ResponseEntity.ok().body(service.findAll());
  }

  @PostMapping("/user")
  public User create(@RequestBody User user) {
    return service.create(user);
  }
}
