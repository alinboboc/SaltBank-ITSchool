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
        System.out.println("Bine ai venit la Saltbank");
        System.out.println("Pentru a te inregistra, trebuie sa introduci un nume de utilizator: ");
        System.out.println("Daca vrei sa mergi la meniul anterior, apasa tasta 9");
        registerUsername();
    }

    public void registerUsername() throws IOException {
        System.out.println("Nume utilizator: ");
        setNewUserRegistration(scanner.next());
        if (getNewUserRegistration().equals("9")) {
            Application application = new Application();
            application.navigationWelcome();
        }
        if (getNewUserRegistration().length() < 8) {
            System.out.println("Numele de utilizator trebuie sa contina cel putin 8 caractere");
            registerUsername();
        }
        if (getNewUserRegistration().length() >= 8) {
            registerFirstName();
            registerLastName();
        }
    }

    public void registerPassword() {
        System.out.println("Parola: ");
        setNewUserRegistrationPassword(scanner.next());
        if (getNewUserRegistrationPassword().length() < 8) {
            System.out.println("Parola trebuie sa aibe cel putin 8 caractere. Va rugam sa reintroduceti parola:");
            registerPassword();
        } else {
            System.out.println("Confirmare parola: ");
            setNewUserRegistrationPasswordConfirmation(scanner.next());
            if (!Objects.equals(getNewUserRegistrationPasswordConfirmation(), getNewUserRegistrationPassword())) {
                System.out.println("Parola introdusa nu coincide cu prima parola, incearca din nou.");
                registerPassword();
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("username", getNewUserRegistration());
                jsonObject.put("password", getNewUserRegistrationPasswordConfirmation());
                jsonObject.put("IBAN", ibanGenerator());
                jsonObject.put("firstName", getNewUserfirstName());
                jsonObject.put("lastName", getNewUserLastName());
                jsonObject.put("birthYear", getNewUserBirthYear());
                jsonObject.put("email", getNewUserEmailAddress());
                try {
                    FileWriter file = new FileWriter("src/main/database/database.txt", true);
                    file.write(jsonObject.toJSONString());
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void registerEmail() {
        System.out.println("Adresa de email: ");
        setNewUserEmailAddress(scanner.next());
        if (!getNewUserEmailAddress().contains("@")) {
            System.out.println("Adresa de email introdusa nu este valida, incearca din nou.");
            registerEmail();
        } else {
            registerBirthYear();
        }
    }

    public void registerFirstName() {
        System.out.println("Introduceti numele de familie: ");
        setNewUserfirstName(scanner.next());
    }

    public void registerLastName() {
        System.out.println("Introduceti prenumele: ");
        setNewUserLastName(scanner.next());
        registerEmail();
    }

    public void registerBirthYear() {
        System.out.println("Introduceti anul nasterii: ");
        setNewUserBirthYear(Integer.parseInt(scanner.next()));
        int year = Year.now().getValue();
        if (year - getNewUserBirthYear() < 16) {
            System.out.println("Nu ai varsta sufieienta pentru a creea un cont la SaltBank.");
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
