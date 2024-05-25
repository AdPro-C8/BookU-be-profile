package id.ac.ui.cs.advprog.bookubeprofile.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PutProfileDto {
    private String firstName;

    private String lastName;

    private String profilePicture;

    private String bio;

    private String gender;

    private LocalDate birthdate;
}