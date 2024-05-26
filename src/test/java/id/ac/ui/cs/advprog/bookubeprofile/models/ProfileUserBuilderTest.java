package id.ac.ui.cs.advprog.bookubeprofile.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileUserBuilderTest {

    @Test
    public void testEmail() {
        assertDoesNotThrow(() -> new ProfileUserBuilder().email("test@example.com"));
        assertThrows(IllegalArgumentException.class, () -> new ProfileUserBuilder().email("invalid_email"));
        assertDoesNotThrow(() -> new ProfileUserBuilder().email(""));
    }

    @Test
    public void testFirstName() {
        assertDoesNotThrow(() -> new ProfileUserBuilder().firstName("John"));
        assertThrows(IllegalArgumentException.class, () -> new ProfileUserBuilder().firstName("John@"));
        assertThrows(IllegalArgumentException.class, () -> new ProfileUserBuilder().firstName(""));
    }

    @Test
    public void testLastName() {
        assertDoesNotThrow(() -> new ProfileUserBuilder().lastName("Doe"));
        assertThrows(IllegalArgumentException.class, () -> new ProfileUserBuilder().lastName("Doe@"));
        assertThrows(IllegalArgumentException.class, () -> new ProfileUserBuilder().lastName(""));
    }

    @Test
    public void testProfilePicture() {
        assertDoesNotThrow(() -> new ProfileUserBuilder().profilePicture("http://example.com"));
        assertThrows(IllegalArgumentException.class, () -> new ProfileUserBuilder().profilePicture("invalid_url"));
        assertDoesNotThrow(() -> new ProfileUserBuilder().profilePicture(""));
    }

    @Test
    public void testBio() {
        assertDoesNotThrow(() -> new ProfileUserBuilder().bio("This is a bio"));
        assertThrows(IllegalArgumentException.class, () -> new ProfileUserBuilder().bio("This is a very long bio that is more than 200 characters long. This is a very long bio that is more than 200 characters long. This is a very long bio that is more than 200 characters long. This is a very long bio that is more than 200 characters long."));
        assertDoesNotThrow(() -> new ProfileUserBuilder().bio(""));
    }

    @Test
    public void testGender() {
        assertDoesNotThrow(() -> new ProfileUserBuilder().gender("MALE"));
        assertThrows(IllegalArgumentException.class, () -> new ProfileUserBuilder().gender("Invalid"));
    }

    @Test
    public void testBirthdate() {
        assertDoesNotThrow(() -> new ProfileUserBuilder().birthdate(LocalDate.now()));
    }

    @Test
    public void testBuild() {
        ProfileUser profileUser = new ProfileUserBuilder()
            .email("test@example.com")
            .firstName("John")
            .lastName("Doe")
            .profilePicture("http://example.com")
            .bio("This is a bio")
            .gender("MALE")
            .birthdate(LocalDate.now())
            .build();

        assertEquals("test@example.com", profileUser.getEmail());
        assertEquals("John", profileUser.getFirstName());
        assertEquals("Doe", profileUser.getLastName());
        assertEquals("http://example.com", profileUser.getProfilePicture());
        assertEquals("This is a bio", profileUser.getBio());
        assertEquals("MALE", profileUser.getGender());
        assertNotNull(profileUser.getBirthdate());
    }
}