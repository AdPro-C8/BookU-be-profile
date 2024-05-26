package id.ac.ui.cs.advprog.bookubeprofile.services;

import id.ac.ui.cs.advprog.bookubeprofile.models.ProfileUser;
import id.ac.ui.cs.advprog.bookubeprofile.repositories.ProfileUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProfileUserServiceTest {

    private ProfileUserService profileUserService;

    @Mock
    private ProfileUserRepository profileUserRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        profileUserService = new ProfileUserService(profileUserRepository);
    }

    @Test
    public void testAllUsers() {
        // Arrange
        List<ProfileUser> users = new ArrayList<>();
        when(profileUserRepository.findAll()).thenReturn(users);

        // Act
        List<ProfileUser> result = profileUserService.allUsers();

        // Assert
        assertEquals(users, result);
        verify(profileUserRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserByEmail() {
        // Arrange
        String email = "john.doe@example.com";
        ProfileUser profileUser = new ProfileUser();
        when(profileUserRepository.findByEmail(email)).thenReturn(Optional.of(profileUser));

        // Act
        ProfileUser result = profileUserService.getUserByEmail(email);

        // Assert
        assertEquals(profileUser, result);
        verify(profileUserRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testGetUserByEmailNotFound() {
        // Arrange
        String email = "john.doe@example.com";
        when(profileUserRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        ProfileUser result = profileUserService.getUserByEmail(email);

        // Assert
        assertNull(result);
        verify(profileUserRepository, times(1)).findByEmail(email);
    }

    @Test
    public void testPutUser() {
        // Arrange
        String email = "john.doe@example.com";
        ProfileUser updatedProfileUser = new ProfileUser();
        ProfileUser profileUser = new ProfileUser();
        when(profileUserRepository.findByEmail(email)).thenReturn(Optional.of(profileUser));
        when(profileUserRepository.save(profileUser)).thenReturn(profileUser);

        // Act
        ProfileUser result = profileUserService.putUser(email, updatedProfileUser);

        // Assert
        assertEquals(profileUser, result);
        assertEquals(updatedProfileUser.getEmail(), profileUser.getEmail());
        assertEquals(updatedProfileUser.getFirstName(), profileUser.getFirstName());
        assertEquals(updatedProfileUser.getLastName(), profileUser.getLastName());
        assertEquals(updatedProfileUser.getProfilePicture(), profileUser.getProfilePicture());
        assertEquals(updatedProfileUser.getBio(), profileUser.getBio());
        assertEquals(updatedProfileUser.getGender(), profileUser.getGender());
        assertEquals(updatedProfileUser.getBirthdate(), profileUser.getBirthdate());
        verify(profileUserRepository, times(1)).findByEmail(email);
        verify(profileUserRepository, times(1)).save(profileUser);
    }

    @Test
    public void testPutUserNotFound() {
        // Arrange
        String email = "john.doe@example.com";
        ProfileUser updatedProfileUser = new ProfileUser();
        when(profileUserRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> profileUserService.putUser(email, updatedProfileUser));
        verify(profileUserRepository, times(1)).findByEmail(email);
        verify(profileUserRepository, never()).save(any(ProfileUser.class));
    }
}