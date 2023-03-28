package training.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class CustomUserController {
    @GetMapping
    public UserDetails getDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }
}
