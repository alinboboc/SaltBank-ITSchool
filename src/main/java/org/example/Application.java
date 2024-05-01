package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public Application() throws IOException {
        welcome();
        navigationWelcome();
    }

    public void welcome() {
        System.out.println("\uD83C\uDD82\uD83C\uDD70\uD83C\uDD7B\uD83C\uDD83\uD83C\uDD71\uD83C\uDD70\uD83C\uDD7D\uD83C\uDD7A");
        System.out.println("Bine ai venit la SaltBank. Pentru a continua, trebuie sa te autentifici sau sa te inregistrezi.");
        System.out.println("Alege de mai jos optionea pe care o doresti.");
    }

    public void navigationWelcome() throws IOException {
        System.out.println("-> 1. Autentificare");
        System.out.println("-> 2. Inregistrare");
        System.out.println("-> 3. Paraseste programul");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        do {
            switch (userInput) {
                case "1":
                    System.out.println("Ai ales sa te autentifici");
                    Login newLogin = new Login();
                    newLogin.loginWelcome();
                    break;
                case "2":
                    System.out.println("Ai ales sa te inregistrezi");
                    Register newRegister = new Register();
                    newRegister.registerWelcome();
                    break;
                case "3":
                    System.out.println("Ai ales sa parasesti programul");
                    break;
                default:
                    System.out.println("Tasta apasata nu te duce nicaieri, te rog sa reincerci.");
                    navigationWelcome();
                    break;
            }
        } while (!userInput.equals("1") && !userInput.equals("2") && !userInput.equals("3"));
    }
}
