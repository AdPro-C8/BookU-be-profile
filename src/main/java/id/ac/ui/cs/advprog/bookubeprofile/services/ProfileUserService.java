package id.ac.ui.cs.advprog.bookubeprofile.services;

import id.ac.ui.cs.advprog.bookubeprofile.models.ProfileUser;
import id.ac.ui.cs.advprog.bookubeprofile.repositories.ProfileUserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileUserService {
    private final ProfileUserRepository profileUserRepository;

    public ProfileUserService(ProfileUserRepository profileUserRepository) {
        this.profileUserRepository = profileUserRepository;
    }

    public List<ProfileUser> allUsers() {
        List<ProfileUser> users = new ArrayList<>();
        profileUserRepository.findAll().forEach(users::add);
        return users;
    }

    public ProfileUser getUserByEmail(String email) {
        return profileUserRepository.findByEmail(email).orElse(null);
    }

    public ProfileUser putUser(String email, ProfileUser updatedProfileUser) {
        return profileUserRepository.findByEmail(email)
        .map(profileUser -> {
            profileUser.setEmail(updatedProfileUser.getEmail());
            profileUser.setFirstName(updatedProfileUser.getFirstName());
            profileUser.setLastName(updatedProfileUser.getLastName());
            profileUser.setProfilePicture(updatedProfileUser.getProfilePicture());
            profileUser.setBio(updatedProfileUser.getBio());
            profileUser.setGender(updatedProfileUser.getGender());
            profileUser.setBirthdate(updatedProfileUser.getBirthdate());

            return profileUserRepository.save(profileUser);
        })
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}