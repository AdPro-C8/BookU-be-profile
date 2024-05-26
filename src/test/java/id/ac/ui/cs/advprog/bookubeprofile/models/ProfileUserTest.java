package id.ac.ui.cs.advprog.bookubeprofile.models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileUserTest {

    @Test
    public void testEmailSetter() {
        ProfileUser profileUser = new ProfileUser();
        profileUser.setEmail("test@example.com");
        assertEquals("test@example.com", profileUser.getEmail());
    }

    @Test
    public void testFirstNameSetter() {
        ProfileUser profileUser = new ProfileUser();
        profileUser.setFirstName("John");
        assertEquals("John", profileUser.getFirstName());
    }

    @Test
    public void testLastNameSetter() {
        ProfileUser profileUser = new ProfileUser();
        profileUser.setLastName("Doe");
        assertEquals("Doe", profileUser.getLastName());
    }

    @Test
    public void testProfilePictureSetter() {
        ProfileUser profileUser = new ProfileUser();
        profileUser.setProfilePicture("profile.jpg");
        assertEquals("profile.jpg", profileUser.getProfilePicture());
    }

    @Test
    public void testBioSetter() {
        ProfileUser profileUser = new ProfileUser();
        profileUser.setBio("Hello, world!");
        assertEquals("Hello, world!", profileUser.getBio());
    }

    @Test
    public void testGenderSetter() {
        ProfileUser profileUser = new ProfileUser();
        profileUser.setGender("Male");
        assertEquals("Male", profileUser.getGender());
    }

    @Test
    public void testBirthdateSetter() {
        ProfileUser profileUser = new ProfileUser();
        LocalDate birthdate = LocalDate.of(1990, 1, 1);
        profileUser.setBirthdate(birthdate);
        assertEquals(birthdate, profileUser.getBirthdate());
    }
}