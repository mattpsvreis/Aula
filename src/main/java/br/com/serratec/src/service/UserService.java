package br.com.serratec.src.service;

import java.util.List;
import java.util.Optional;

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

  public User findByUsername(String username) {
    Optional<User> user = repository.findByUsername(username);
    return user.orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND + username));
  }

  public User create(User user) {
    if (repository.findByCpf(user.getCpf()).isPresent()) {
      throw new ResourceAlreadyExistsException(Constants.CPF_ALREADY_REGISTERED + user.getCpf());
    }

    if (repository.findByUsername(user.getUsername()).isPresent()) {
      throw new ResourceAlreadyExistsException(Constants.USERNAME_ALREADY_REGISTERED + user.getUsername());
    }

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

    return repository.save(user);
  }

  public User update(User user) {
    User updatedUser = repository.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException(Constants.USER_NOT_FOUND + user.getId()));

    Optional<User> checkCpf = repository.findByCpf(user.getCpf());

    if (checkCpf.isPresent() && checkCpf.get().getCpf() != updatedUser.getCpf()) {
      throw new ResourceAlreadyExistsException(Constants.CPF_ALREADY_REGISTERED + user.getCpf());
    }

    if (!user.getUsername().isEmpty()) {
      updatedUser.setUsername(user.getUsername());
    }

    if (!user.getCpf().isEmpty()) {
      updatedUser.setCpf(user.getCpf());
    }

    if (!user.getPassword().isEmpty()) {
      updatedUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }

    return repository.save(updatedUser);
  }

  public List<User> findAll() {
    return repository.findAll();
  }
}
