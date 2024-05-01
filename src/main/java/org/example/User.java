package org.example;

import java.io.IOException;

public class User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) throws IOException {
        this.name = name;
    }
}
