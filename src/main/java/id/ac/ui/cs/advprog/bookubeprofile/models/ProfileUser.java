package id.ac.ui.cs.advprog.bookubeprofile.models;

import id.ac.ui.cs.advprog.bookubeprofile.enums.Gender;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "profile_user")
public class ProfileUser {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private Integer id;

  @Column(unique = true)
  @Setter
  private String email;

  @Column(nullable = false)
  @Setter
  @Getter
  private String firstName;

  @Column(nullable = false)
  @Setter
  @Getter
  private String lastName;

  @Column
  @Setter
  @Getter
  private String profilePicture;

  @Column
  @Setter
  @Getter
  private String bio;

  @Column(nullable = false)
  @Setter
  @Getter
  private Gender gender;

  @Column(nullable = false)
  @Setter
  @Getter
  private LocalDate birthdate;
}