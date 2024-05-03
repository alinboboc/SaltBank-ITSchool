package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Login {

    public Login() {
    }

    public void loginWelcome() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please insert the username.");
        System.out.println("If you want to go back to the main menu, please insert 9 and press the Enter key.");
        String userLoginInput = scanner.next();

        if (Objects.equals(userLoginInput, "9")) {
            Application app = new Application();
        }

        System.out.println("Insert the password.");
        String passwordLoginInput = scanner.next();

        loginCheck(userLoginInput, passwordLoginInput);

    }

    public void loginCheck(String userLoginInput, String passwordLoginInput) {
        String filePath = "src/main/database/database.txt";
        File file = new File(filePath);

        if (file.exists()) {
            try {
                Scanner passwordReader = new Scanner(file);
                while (passwordReader.hasNextLine()) {
                    String data = passwordReader.nextLine();
                    if (Objects.equals(data, passwordLoginInput)) {
                        System.out.println("Welcome to SaltBank " + userLoginInput);
                    } else {
                        System.out.println("The password is not correct, please try again.");
                        loginWelcome();
                    }
                }
                passwordReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred, please try again later.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("The username is not in the database.");
        }
    }
}
