package br.com.serratec.src.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.serratec.src.exceptions.ResourceAlreadyExistsException;
import br.com.serratec.src.exceptions.ResourceNotFoundException;
import br.com.serratec.src.model.User;
import br.com.serratec.src.repository.UserRepository;
import br.com.serratec.src.utils.Constants;

@Service
public class UserService {

  @Autowired
  UserRepository repository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  public List<User> findAll() {
    return repository.findAll();
  }

  public User findById(UUID id) {
    Optional<User> user = repository.findById(id);
    return user.orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND_ID + id));
  }

  public User findByUsername(String username) {
    Optional<User> user = repository.findByUsername(username);
    return user.orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND_USERNAME + username));
  }

  public User findByCpf(String cpf) {
    Optional<User> user = repository.findByCpf(cpf);
    return user.orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND_CPF + cpf));
  }

  public User findByEmail(String email) {
    Optional<User> user = repository.findByEmail(email);
    return user.orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND_EMAIL + email));
  }

  public User create(User user) {
    if (repository.findByUsername(user.getUsername()).isPresent()) {
      throw new ResourceAlreadyExistsException(Constants.ALREADY_REGISTERED_USERNAME + user.getUsername());
    }

    if (repository.findByCpf(user.getCpf()).isPresent()) {
      throw new ResourceAlreadyExistsException(Constants.ALREADY_REGISTERED_CPF + user.getCpf());
    }

    if (repository.findByEmail(user.getEmail()).isPresent()) {
      throw new ResourceAlreadyExistsException(Constants.ALREADY_REGISTERED_EMAIL + user.getEmail());
    }

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    return repository.save(user);
  }

  public User update(User user) {
    User existingUser = repository.findById(user.getId())
        .orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND_ID + user.getId()));

    Optional<User> checkUsername = repository.findByUsername(user.getUsername());

    if (checkUsername.isPresent() && checkUsername.get().getUsername() != existingUser.getUsername()) {
      throw new ResourceAlreadyExistsException(Constants.ALREADY_REGISTERED_USERNAME + user.getUsername());
    }

    Optional<User> checkCpf = repository.findByCpf(user.getCpf());

    if (checkCpf.isPresent() && checkCpf.get().getCpf() != existingUser.getCpf()) {
      throw new ResourceAlreadyExistsException(Constants.ALREADY_REGISTERED_CPF + user.getCpf());
    }

    Optional<User> checkEmail = repository.findByEmail(user.getEmail());

    if (checkEmail.isPresent() && checkEmail.get().getEmail() != existingUser.getEmail()) {
      throw new ResourceAlreadyExistsException(Constants.ALREADY_REGISTERED_EMAIL + user.getEmail());
    }

    if (!user.getUsername().isEmpty()) {
      existingUser.setUsername(user.getUsername());
    }

    if (!user.getCpf().isEmpty()) {
      existingUser.setCpf(user.getCpf());
    }

    if (!user.getEmail().isEmpty()) {
      existingUser.setEmail(user.getEmail());
    }

    if (!user.getPassword().isEmpty()) {
      existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    return repository.save(existingUser);
  }
}
