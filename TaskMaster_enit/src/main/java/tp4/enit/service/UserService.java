package tp4.enit.service;

import jakarta.transaction.Transactional;
import tp4.enit.entity.User;
import tp4.enit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Transactional
    public User registerUser(User user) throws Exception {
        System.out.println("Registering user: " + user);
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new Exception("Username already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public User loginUser(String username, String password) throws Exception {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            if (passwordEncoder.matches(password, existingUser.getPassword())) {
                return existingUser;
            } else {
                throw new Exception("Invalid password");
            }
        } else {
            throw new Exception("User not found");
        }
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUserImage(String username, byte[] imageBytes) {
        // Récupérer l'utilisateur par son nom d'utilisateur
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Mettre à jour l'image de l'utilisateur
        user.setImage(imageBytes);

        // Sauvegarder les modifications dans la base de données
        userRepository.save(user);
    }
}
