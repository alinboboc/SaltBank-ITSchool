package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.time.Year;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {

    private String newUserRegistration;
    private String newUserRegistrationPassword;
    private String newUserRegistrationPasswordConfirmation;
    private String newUserFirstName;
    private String newUserLastName;
    private String newUserEmailAddress;
    private int newUserBirthYear;

    private String getNewUserFirstName() {
        return newUserFirstName;
    }

    private void setNewUserFirstName(String newUserFirstName) {
        this.newUserFirstName = newUserFirstName;
    }

    private int getNewUserBirthYear() {
        return newUserBirthYear;
    }

    private void setNewUserBirthYear(int newUserBirthYear) {
        this.newUserBirthYear = newUserBirthYear;
    }

    private String getNewUserEmailAddress() {
        return newUserEmailAddress;
    }

    private void setNewUserEmailAddress(String newUserEmailAddress) {
        this.newUserEmailAddress = newUserEmailAddress;
    }

    private String getNewUserLastName() {
        return newUserLastName;
    }

    private void setNewUserLastName(String newUserLastName) {
        this.newUserLastName = newUserLastName;
    }

    private String getNewUserRegistration() {
        return newUserRegistration;
    }

    private void setNewUserRegistration(String newUserRegistration) {
        this.newUserRegistration = newUserRegistration;
    }

    private String getNewUserRegistrationPassword() {
        return newUserRegistrationPassword;
    }

    private void setNewUserRegistrationPassword(String newUserRegistrationPassword) {
        this.newUserRegistrationPassword = newUserRegistrationPassword;
    }

    private String getNewUserRegistrationPasswordConfirmation() {
        return newUserRegistrationPasswordConfirmation;
    }

    private void setNewUserRegistrationPasswordConfirmation(String newUserRegistrationPasswordConfirmation) {
        this.newUserRegistrationPasswordConfirmation = newUserRegistrationPasswordConfirmation;
    }

    Scanner scanner = new Scanner(System.in);

    void registerWelcome() throws IOException {
        System.out.println("Welcome to SaltBank!");
        System.out.println("If you want to go back to the main menu, please insert 9 and press the Enter key.");
        System.out.println("To register, please choose an username:");
        registerUsername();
    }

    private void registerUsername() throws IOException {
        System.out.println("Username:");
        setNewUserRegistration(scanner.next());
        if (getNewUserRegistration().equals("9")) {
            Application application = new Application();
            application.navigationWelcome();
        }
        if (getNewUserRegistration().length() < 6) {
            System.out.println("The username must contain at last 6 characters.");
            registerUsername();
        }
        if (getNewUserRegistration().length() >= 6) {
            //needs to be moved to the next step
            registerFirstName();
        }
    }

    private void registerPassword() {
        System.out.println("Password: ");
        setNewUserRegistrationPassword(scanner.next());
        if (getNewUserRegistrationPassword().length() < 8) {
            System.out.println("The password needs to contain at last 8 characters. Please insert the password again.");
            registerPassword();
        } else {
            System.out.println("Confirm the password:");
            setNewUserRegistrationPasswordConfirmation(scanner.next());
            if (!Objects.equals(getNewUserRegistrationPasswordConfirmation(), getNewUserRegistrationPassword())) {
                System.out.println("The password is not the same with the previous one, please try again.");
                registerPassword();
            } else {
                User newUser = new User(getNewUserRegistration(),getNewUserRegistrationPasswordConfirmation(),getNewUserEmailAddress(),getNewUserFirstName(),getNewUserLastName(),getNewUserBirthYear(),ibanGenerator(),0);
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    FileOutputStream outputStream = new FileOutputStream("src/main/database/db.json");
                    mapper.writeValue(outputStream, newUser);
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println("An error occurred, please try again later.");
                }
            }
        }
    }

    private void registerEmail() {
        System.out.println("Email address:");
        setNewUserEmailAddress(scanner.next());
        if (!getNewUserEmailAddress().contains("@")) {
            System.out.println("The email address does not contain @ symbol, please try again.");
            registerEmail();
        } else {
            registerBirthYear();
        }
    }

    private void registerFirstName() {
        System.out.println("First name:");
        setNewUserFirstName(scanner.next());
        if (regexChecker(getNewUserFirstName())) {
            System.out.println("The name you entered contains invalid characters, please try again.");
            registerFirstName();
        } else {
            registerLastName();
        }
    }

    private void registerLastName() {
        System.out.println("Last name:");
        setNewUserLastName(scanner.next());
        if (regexChecker(getNewUserLastName())) {
            System.out.println("The name you entered contains invalid characters, please try again.");
            registerLastName();
        } else {
            registerEmail();
        }
    }

    private void registerBirthYear() {
        System.out.println("Birth year:");
        setNewUserBirthYear(Integer.parseInt(scanner.next()));
        int year = Year.now().getValue();
        if (year - getNewUserBirthYear() < 16) {
            System.out.println("You need to be at least 16 years old to create an account at SaltBank.");
        } else {
            registerPassword();
        }
    }

    private String ibanGenerator() {
        String fullIBAN = "RO10SLTB";
        while (fullIBAN.length() < 24) {
            int ibanRandomNumbers = (int) Math.floor(Math.random() * 10000);
            fullIBAN = fullIBAN.concat(String.valueOf(ibanRandomNumbers));
        }
        return fullIBAN;
    }

    private boolean regexChecker(String input) {
        String regex = "[^a-zA-Z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }
}
