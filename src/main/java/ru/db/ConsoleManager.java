package ru.db;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleManager {
    public void work() {
        Scanner scanner = new Scanner(System.in);
        while(true){
            scanner.next();
        }
    }
}
