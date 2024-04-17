package id.ac.ui.cs.advprog.bookubeprofile.models;

import id.ac.ui.cs.advprog.bookubeprofile.enums.Gender;

import java.lang.IllegalArgumentException;
import java.time.LocalDate;

public class ProfileUserBuilder {
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String bio;
    private String gender;
    private LocalDate birthdate;

    public ProfileUserBuilder email(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
        return this;
    }

    public ProfileUserBuilder firstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("First Name cannot be empty");
        }
        if (!firstName.matches("^[a-zA-Z0-9_]*$")) {
            throw new IllegalArgumentException("First Name can only contain letters, numbers, and underscore");
        }
        this.firstName = firstName;
        return this;
    }

    public ProfileUserBuilder lastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Last Name cannot be empty");
        }
        if (!lastName.matches("^[a-zA-Z0-9_]*$")) {
            throw new IllegalArgumentException("Last Name can only contain letters, numbers, and underscore");
        }
        this.lastName = lastName;
        return this;
    }

    public ProfileUserBuilder profilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public ProfileUserBuilder bio(String bio) {
        this.bio = bio;
        return this;
    }

    public ProfileUserBuilder gender(String gender) {
      if (!Gender.contains(gender)) {
        throw new IllegalArgumentException("Gender must be Male, Female, or Other");
      }
      this.gender = gender;
      return this;
    }

    public ProfileUserBuilder birthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    public ProfileUser build() {
        ProfileUser profileUser = new ProfileUser();
        profileUser.setEmail(email);
        profileUser.setFirstName(firstName);
        profileUser.setLastName(lastName);
        profileUser.setProfilePicture(profilePicture);
        profileUser.setBio(bio);
        profileUser.setGender(gender);
        profileUser.setBirthdate(birthdate);

        return profileUser;
    }
}