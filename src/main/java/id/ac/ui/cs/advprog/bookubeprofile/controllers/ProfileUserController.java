package id.ac.ui.cs.advprog.bookubeprofile.controllers;

import id.ac.ui.cs.advprog.bookubeprofile.models.ProfileUser;
import id.ac.ui.cs.advprog.bookubeprofile.services.ProfileUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/profiles")
@RestController
public class ProfileUserController {
    private final ProfileUserService profileUserService;

    public ProfileUserController(ProfileUserService profileUserService) {
        this.profileUserService = profileUserService;
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileUser> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ProfileUser currentUser = (ProfileUser) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }
}