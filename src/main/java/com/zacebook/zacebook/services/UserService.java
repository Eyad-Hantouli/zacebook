package com.zacebook.zacebook.services;

import com.zacebook.zacebook.repositories.UserRepository;
import com.zacebook.zacebook.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends ObjectSpecializer{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Map<String, Object> getUserById(String userName) {
        User user = userRepository
                .findById(userName)
                .orElseThrow(() -> new IllegalStateException("User with id = " + userName + " doesn't exist."));

        return user.getAllData();
    }

    public void createUser(Map<String, Object> requestedUser) {
        // -- Fields --
        String userName = getStringValue(requestedUser, "userName");

        String email = getStringValue(requestedUser, "email");

        String firstName = getStringValue(requestedUser, "firstName");

        String lastName = getStringValue(requestedUser, "lastName");

        String password = getStringValue(requestedUser, "password");

        String address = getStringValue(requestedUser, "address");

        LocalDate dob = getLocalDateValue(requestedUser, "dob", "yyyy-MM-dd" );

        LocalDate creationDate = LocalDate.now();

        // -- Validations --

        // userName validation
        if (userName == null) {
            throw new IllegalStateException("user_name mustn't be null.");
        }
        if (userName.length() < 1) {
            throw new IllegalStateException("user_name length must be at least 1 character.");
        }
        if (!userName.matches("^[a-z0-9_]+$")) {
            throw new IllegalStateException("user_name must contain only [a-z], [0-9] or underscore(_).");
        }
        if (userName.charAt(0) >= '0' && userName.charAt(0) <= '9') {
            throw new IllegalStateException("user_name must start with lowercase latin character or underscore(_).");
        }
        boolean userNameExist = userRepository.existsById(userName);
        if (userNameExist) {
            throw new IllegalStateException("user_name = " + userName + " is already taken by another user.");
        }

        // email validation
        if (email == null) {
            throw new IllegalStateException("email mustn't be null.");
        }
        if (email.length() < 1) {
            throw new IllegalStateException("email length must be at least 1 character.");
        }
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")) {
            throw new IllegalStateException("incorrect email format.");
        }
        boolean emailExists = userRepository.existsByEmail(email);
        if (emailExists) {
            throw new IllegalStateException("email = " + email + " is already taken by another user.");
        }

        // firstName validation

        // lastName validation

        // password validation

        // address validation

        // dob validation


        // every thing ok.
        User user = new User.UserBuilder(
                userName,
                email,
                firstName,
                lastName,
                password,
                creationDate
                )
                .setAddress(address)
                .setDob(dob)
                .build();

        userRepository.save(user);

    }

    public void updateUser(Map<String, Object> requestedUser) {
        // -- Fields --
        String userName = getStringValue(requestedUser, "userName");

        String email = getStringValue(requestedUser, "email");

        String firstName = getStringValue(requestedUser, "firstName");

        String lastName = getStringValue(requestedUser, "lastName");

        String password = getStringValue(requestedUser, "password");

        String address = getStringValue(requestedUser, "address");

        LocalDate dob = getLocalDateValue(requestedUser, "dob", "yyyy-MM-dd" );

        // -- Validations --

        // userName validation
        if (userName == null) {
            throw new IllegalStateException("user_name mustn't be null.");
        }
        if (userName.length() < 1) {
            throw new IllegalStateException("user_name length must be at least 1 character.");
        }
        User user = userRepository
                .findById(userName)
                .orElseThrow(() -> new IllegalStateException("User with user_name = " + userName + " doesn't exist."));

        // email validation
        if (email == null) {
            throw new IllegalStateException("email mustn't be null.");
        }
        if (email.length() < 1) {
            throw new IllegalStateException("email length must be at least 1 character.");
        }
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$")) {
            throw new IllegalStateException("incorrect email format.");
        }
        if (!email.equals(user.getEmail())) {
            boolean emailExists = userRepository.existsByEmail(email);
            if (emailExists) {
                throw new IllegalStateException("email = " + email + " is already taken by another user.");
            }
        }
        user.setEmail(email);

        // firstName validation
        user.setFirstName(firstName);

        // lastName validation
        user.setLastName(lastName);

        // password validation
        user.setPassword(password);

        // address validation
        user.setAddress(address);

        // dob validation
        user.setDob(dob);

        userRepository.save(user);
    }

    public void deleteUser(String userName) {
        // userName validation
        if (userName == null) {
            throw new IllegalStateException("user_name mustn't be null.");
        }
        if (userName.length() < 1) {
            throw new IllegalStateException("user_name length must be at least 1 character.");
        }
        boolean userNameExist = userRepository.existsById(userName);
        if (!userNameExist) {
            throw new IllegalStateException("User with user_name = " + userName + " doesn't exist.");
        }

        userRepository.deleteById(userName);
    }
}
