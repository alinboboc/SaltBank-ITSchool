package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public Application() throws IOException {
        welcome();
        navigationWelcome();
    }

    private void welcome() {
        System.out.println("\uD83C\uDD82\uD83C\uDD70\uD83C\uDD7B\uD83C\uDD83\uD83C\uDD71\uD83C\uDD70\uD83C\uDD7D\uD83C\uDD7A");
        System.out.println("Welcome to SaltBank. To continue, please create an account or log in into an existing one.");
    }

    void navigationWelcome() throws IOException {
        System.out.println("1. Log in");
        System.out.println("2. Register new account");
        System.out.println("3. Exit");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        do {
            switch (userInput) {
                case "1":
                    Login newLogin = new Login();
                    newLogin.loginWelcome();
                    break;
                case "2":
                    Register newRegister = new Register();
                    newRegister.registerWelcome();
                    break;
                case "3":
                    System.out.println("You chose to leave the program. Good bye.");
                    killApp();
                    break;
                default:
                    System.out.println("The key you pressed is not associated with a command, please try again.");
                    navigationWelcome();
                    break;
            }
        } while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3"));
    }

    private void killApp(){
        return;
    }
}
