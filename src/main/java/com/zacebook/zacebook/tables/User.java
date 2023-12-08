package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="users")
public class User {
    // -- Fields Section --
    // PK
    @Id
    @Column(name="user_name")
    private String userName;

    //Unique
    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String password;
    private LocalDate dob;
    private String address;
    private LocalDate creationDate;

    // -- Constructors Section --
    public User() {
    }

    public User(UserBuilder builder) {
        this.userName = builder.userName;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.password = builder.password;
        this.dob = builder.dob;
        this.address = builder.address;
        this.creationDate = builder.creationDate;
    }

    // -- Getter & Setters Section --

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    // -- Builder Section --

    public static class UserBuilder {
        private final String userName;
        private final String email;
        private final String firstName;
        private final String lastName;
        private final String password;
        private LocalDate dob;
        private String address;
        private LocalDate creationDate;

        public UserBuilder(String userName,
                             String email,
                             String firstName,
                             String lastName,
                             String password,
                             LocalDate creationDate) {
            this.userName = userName;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
            this.creationDate = creationDate;
        }

        public UserBuilder setDob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            User user = new User(this);
            validUser(user);
            return user;
        }

        private void validUser(User user) {
            // some validation logic of user object before initiate it
        }
    }
}