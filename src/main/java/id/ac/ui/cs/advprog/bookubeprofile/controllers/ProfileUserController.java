package id.ac.ui.cs.advprog.bookubeprofile.controllers;

import id.ac.ui.cs.advprog.bookubeprofile.models.ProfileUser;
import id.ac.ui.cs.advprog.bookubeprofile.models.User;
import id.ac.ui.cs.advprog.bookubeprofile.services.ProfileUserService;
import id.ac.ui.cs.advprog.bookubeprofile.dtos.PutProfileDto;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PutMapping("")
    public ResponseEntity<?> putProfile(@AuthenticationPrincipal User user, @RequestBody PutProfileDto profileUser) {
        String email = user.getEmail();
        ProfileUser currentUser = profileUserService.getUserByEmail(email);

        if (currentUser != null) {
            try {
                currentUser = ProfileUser.builder()
                        .email(email)
                        .firstName(profileUser.getFirstName())
                        .lastName(profileUser.getLastName())
                        .profilePicture(profileUser.getProfilePicture())
                        .bio(profileUser.getBio())
                        .gender(profileUser.getGender())
                        .birthdate(profileUser.getBirthdate())
                        .build();

                currentUser = profileUserService.putUser(currentUser.getEmail(), currentUser);
                return ResponseEntity.ok().body(currentUser);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

//            return ResponseEntity.ok().body("amannnn");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}