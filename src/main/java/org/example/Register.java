package org.example;

import java.io.*;
import java.time.Year;
import java.util.Objects;
import java.util.Scanner;

import org.json.simple.JSONObject;

public class Register {

    private String newUserRegistration;
    private String newUserRegistrationPassword;
    private String newUserRegistrationPasswordConfirmation;
    private String newUserfirstName;
    private String newUserLastName;
    private String newUserEmailAddress;
    private int newUserBirthYear;

    public String getNewUserfirstName() {
        return newUserfirstName;
    }

    public void setNewUserfirstName(String newUserfirstName) {
        this.newUserfirstName = newUserfirstName;
    }

    public int getNewUserBirthYear() {
        return newUserBirthYear;
    }

    public void setNewUserBirthYear(int newUserBirthYear) {
        this.newUserBirthYear = newUserBirthYear;
    }

    public String getNewUserEmailAddress() {
        return newUserEmailAddress;
    }

    public void setNewUserEmailAddress(String newUserEmailAddress) {
        this.newUserEmailAddress = newUserEmailAddress;
    }

    public String getNewUserLastName() {
        return newUserLastName;
    }

    public void setNewUserLastName(String newUserLastName) {
        this.newUserLastName = newUserLastName;
    }

    public String getNewUserRegistration() {
        return newUserRegistration;
    }

    public void setNewUserRegistration(String newUserRegistration) {
        this.newUserRegistration = newUserRegistration;
    }

    public String getNewUserRegistrationPassword() {
        return newUserRegistrationPassword;
    }

    public void setNewUserRegistrationPassword(String newUserRegistrationPassword) {
        this.newUserRegistrationPassword = newUserRegistrationPassword;
    }

    public String getNewUserRegistrationPasswordConfirmation() {
        return newUserRegistrationPasswordConfirmation;
    }

    public void setNewUserRegistrationPasswordConfirmation(String newUserRegistrationPasswordConfirmation) {
        this.newUserRegistrationPasswordConfirmation = newUserRegistrationPasswordConfirmation;
    }

    Scanner scanner = new Scanner(System.in);

    public void registerWelcome() throws IOException {
        System.out.println("Welcome to SaltBank!");
        System.out.println("If you want to go back to the main menu, please insert 9 and press the Enter key.");
        System.out.println("To register, please choose an username:");
        registerUsername();
    }

    public void registerUsername() throws IOException {
        System.out.println("Username:");
        setNewUserRegistration(scanner.next());
        if (getNewUserRegistration().equals("9")) {
            Application application = new Application();
            application.navigationWelcome();
        }
        if (getNewUserRegistration().length() < 8) {
            System.out.println("The username must contain at last 8 characters.");
            registerUsername();
        }
        if (getNewUserRegistration().length() >= 8) {
            registerFirstName();
            registerLastName();
        }
    }

    public void registerPassword() {
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
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", getNewUserRegistration());
                jsonObject.put("password", getNewUserRegistrationPasswordConfirmation());
                jsonObject.put("email", getNewUserEmailAddress());
                jsonObject.put("firstName", getNewUserfirstName());
                jsonObject.put("lastName", getNewUserLastName());
                jsonObject.put("birthYear", getNewUserBirthYear());
                jsonObject.put("IBAN", ibanGenerator());
                jsonObject.put("balance", 0);
                try {
                    FileWriter file = new FileWriter("src/main/database/database.txt", true);
                    file.write(jsonObject.toJSONString() + "\n");
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void registerEmail() {
        System.out.println("Email adress:");
        setNewUserEmailAddress(scanner.next());
        if (!getNewUserEmailAddress().contains("@")) {
            System.out.println("The email address does not contain @ symbol, please try again.");
            registerEmail();
        } else {
            registerBirthYear();
        }
    }

    public void registerFirstName() {
        System.out.println("First name:");
        setNewUserfirstName(scanner.next());
    }

    public void registerLastName() {
        System.out.println("Last name:");
        setNewUserLastName(scanner.next());
        registerEmail();
    }

    public void registerBirthYear() {
        System.out.println("Birth year:");
        setNewUserBirthYear(Integer.parseInt(scanner.next()));
        int year = Year.now().getValue();
        if (year - getNewUserBirthYear() < 16) {
            System.out.println("You need to be at least 16 years old to create an account at SaltBank.");
        } else {
            registerPassword();
        }
    }

    public String ibanGenerator() {
        String fullIBAN = "RO10SLTB";
        while (fullIBAN.length() < 24) {
            int ibanRandomNumbers = (int) Math.floor(Math.random() * 10000);
            fullIBAN = fullIBAN.concat(String.valueOf(ibanRandomNumbers));
        }
        return fullIBAN;
    }
}
