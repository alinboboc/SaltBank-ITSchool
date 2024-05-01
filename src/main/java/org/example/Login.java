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
        System.out.println("Introduceti numele de utilizator");
        System.out.println("Daca vrei sa mergi la meniul anterior, apasa 9");
        System.out.println("-> ");
        String userLoginInput = scanner.next();

        if (Objects.equals(userLoginInput, "9")) {
            Application app = new Application();
        }

        System.out.println("Introduceti parola");
        System.out.println("-> ");
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
                        System.out.println("Bine ai venit la SaltBank " + userLoginInput);
                    } else {
                        System.out.println("Parola introdusa nu este corecta. Te rugam sa reintroduci datele");
                        loginWelcome();
                    }
                }
                passwordReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("A aparut o eroare, te rugam sa incerci mai tarziu.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Numele de utilizator introdus nu exista, doresti sa iti creezi un cont nou?");
        }
    }
}
