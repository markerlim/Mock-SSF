package com.assessment.ssf.MOCK_SSF.model;

import java.time.LocalDate;

import com.assessment.ssf.MOCK_SSF.validation.ValidAge;
import com.assessment.ssf.MOCK_SSF.validation.ValidDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class User {

    @NotBlank(message = "Fullname is required.")
    @Size(min = 5, max = 25, message = "Name should be between 5 to 25 chracters long.")
    private String fullName;

    @NotNull(message = "Date is required.")
    @ValidDate
    @ValidAge
    @Past(message = "Date must be in the past.")
    private LocalDate dob;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please provide a valid email in the format <emailname>@<domain_name>.")
    @Size(max = 50, message = "Email should not exceed 50 characters.")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[89]\\d{7}$", message = "Mobile number must start with 8 or 9, followed by 7 digits.")
    private String mobile;

    @NotNull(message = "Number of tickets is required.")
    @Min(value = 1, message = "You must buy at least one ticket.")
    @Max(value = 3, message = "You can only buy a maximum of 3 tickets.")
    private Integer tickets;

    public User(){

    }

    public User(
            @NotBlank(message = "Fullname is required.") @Size(min = 5, max = 25, message = "Name should be between 5 to 25 chracters long.") String fullName,
            @NotNull(message = "Date is required.") @Past(message = "Date must be in the past.") LocalDate dob,
            @NotBlank(message = "Email is required.") @Email(message = "Please provide a valid email in the format <emailname>@<domain_name>.") @Size(max = 50, message = "Email should not exceed 50 characters.") String email,
            @NotBlank @Pattern(regexp = "^[89]\\d{7}$", message = "Mobile number must start with 8 or 9, followed by 7 digits.") String mobile,
            @NotNull @Min(1) @Max(3) Integer tickets) {
        this.fullName = fullName;
        this.dob = dob;
        this.email = email;
        this.mobile = mobile;
        this.tickets = tickets;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    

}
