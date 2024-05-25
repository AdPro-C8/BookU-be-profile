package id.ac.ui.cs.advprog.bookubeprofile.controllers;

import id.ac.ui.cs.advprog.bookubeprofile.models.ProfileUser;
import id.ac.ui.cs.advprog.bookubeprofile.models.User;
import id.ac.ui.cs.advprog.bookubeprofile.services.ProfileUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/profile")
@RestController
public class ProfileUserController {
    private final ProfileUserService profileUserService;

    public ProfileUserController(ProfileUserService profileUserService) {
        this.profileUserService = profileUserService;
    }

    @GetMapping("")
    public ResponseEntity<ProfileUser> authenticatedUser(@AuthenticationPrincipal User user) {
        String email = user.getEmail();
        ProfileUser currentUser = profileUserService.getUserByEmail(email);

        if (currentUser != null) {
            return ResponseEntity.ok(currentUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}