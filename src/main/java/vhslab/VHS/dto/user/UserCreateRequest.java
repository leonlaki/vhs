package vhslab.VHS.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class UserCreateRequest {

    @NotBlank(message = "{user.name.required}")
    private String name;
    @NotBlank(message = "{user.surname.required")
    private String surname;
    @Email
    @NotBlank(message = "{user.email.invalid}")
    private String email;

    private LocalDate dateOfBirth;

    private String phoneNumber;

    public UserCreateRequest() {}

    public UserCreateRequest(String name, String surname, String email, LocalDate dateOfBirth, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
