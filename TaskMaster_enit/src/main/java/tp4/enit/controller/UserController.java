package tp4.enit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tp4.enit.entity.User;
import tp4.enit.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")  // This is the base URL for your endpoints
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        User userDTO = new User(user.getFullName(), user.getUsername(), user.getEmail(), user.getPhoneNumber(), null, null);
        return ResponseEntity.ok(userDTO);
    }
}

