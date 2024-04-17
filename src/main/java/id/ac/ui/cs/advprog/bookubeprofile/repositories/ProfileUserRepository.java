package id.ac.ui.cs.advprog.bookubeprofile.repositories;

import id.ac.ui.cs.advprog.bookubeprofile.models.ProfileUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileUserRepository extends CrudRepository<ProfileUser, Integer> {
    Optional<ProfileUser> findByEmail(String email);
}