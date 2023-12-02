package com.zacebook.zacebook.tables;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="PERSON")
public class Person {
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

    // FK
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_profile_picture_id", referencedColumnName = "id")
    private ProfilePicture activeProfilePicture;

    // -- Constructors Section --
    public Person() {
    }

    public Person(PersonBuilder builder) {
        this.userName = builder.userName;
        this.email = builder.email;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.password = builder.password;
        this.dob = builder.dob;
        this.address = builder.address;
        this.activeProfilePicture = builder.activeProfilePicture;
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

    public ProfilePicture getActiveProfilePicture() {
        return activeProfilePicture;
    }

    public void setActiveProfilePicture(ProfilePicture activeProfilePicture) {
        this.activeProfilePicture = activeProfilePicture;
    }

    // -- Builder Section --

    public static class PersonBuilder {
        private final String userName;
        private final String email;
        private final String firstName;
        private final String lastName;
        private final String password;
        private LocalDate dob;
        private String address;
        private ProfilePicture activeProfilePicture;

        public PersonBuilder(String userName, String email, String firstName, String lastName, String password) {
            this.userName = userName;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.password = password;
        }

        public PersonBuilder setDob(LocalDate dob) {
            this.dob = dob;
            return this;
        }

        public PersonBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder setActiveProfilePicture(ProfilePicture activeProfilePicture) {
            this.activeProfilePicture = activeProfilePicture;
            return this;
        }

        public Person build() {
            Person person = new Person(this);
            validPerson(person);
            return person;
        }

        private void validPerson(Person person) {
            // some validation logic of person object before initiate it
        }
    }
}
