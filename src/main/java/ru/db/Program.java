package ru.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.db.repos.EventRepos;
import ru.db.repos.PersonRepos;
import ru.db.util.ConsoleManager;

@SpringBootApplication
public class Program implements CommandLineRunner {
    private final ConsoleManager consoleManager;


    @Autowired
    public Program(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    public static void main(String[] args) {
        SpringApplication.run(Program.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        consoleManager.work();
    }
}

