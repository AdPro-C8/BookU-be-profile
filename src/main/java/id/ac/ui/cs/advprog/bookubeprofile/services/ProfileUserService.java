package id.ac.ui.cs.advprog.bookubeprofile.services;

import id.ac.ui.cs.advprog.bookubeprofile.models.ProfileUser;
import id.ac.ui.cs.advprog.bookubeprofile.repositories.ProfileUserRepository;

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
}