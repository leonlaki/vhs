package vhslab.VHS.service;

import org.springframework.stereotype.Service;
import vhslab.VHS.repository.UserRepository;
import vhslab.VHS.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }
}
