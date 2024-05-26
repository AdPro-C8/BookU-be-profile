package id.ac.ui.cs.advprog.bookubeprofile.dtos;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutProfileDtoTest {

    @Test
    public void testPutProfileDto() {
        // Arrange
        String firstName = "John";
        String lastName = "Doe";
        String profilePicture = "profile.jpg";
        String bio = "Hello, I'm John Doe";
        String gender = "Male";
        LocalDate birthdate = LocalDate.of(1990, 1, 1);

        // Act
        PutProfileDto putProfileDto = new PutProfileDto(firstName, lastName, profilePicture, bio, gender, birthdate);

        // Assert
        assertEquals(firstName, putProfileDto.getFirstName());
        assertEquals(lastName, putProfileDto.getLastName());
        assertEquals(profilePicture, putProfileDto.getProfilePicture());
        assertEquals(bio, putProfileDto.getBio());
        assertEquals(gender, putProfileDto.getGender());
        assertEquals(birthdate, putProfileDto.getBirthdate());
    }
}