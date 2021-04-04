package ru.db.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.db.Entity;
import ru.db.models.EventType;

import java.text.ParseException;
import java.util.Scanner;

@Component
public class ConsoleManager {
    private final Scanner scanner = new Scanner(System.in);

    private final PersonMenu personMenu;
    private final NoteMenu noteMenu;
    private final EventTypeMenu eventTypeMenu;
    private final AdressMenu adressMenu;
    private final EventMenu eventMenu;

    @Autowired
    public ConsoleManager(PersonMenu personMenu, NoteMenu noteMenu, EventTypeMenu eventTypeMenu, AdressMenu adressMenu, EventMenu eventMenu) {
        this.personMenu = personMenu;
        this.noteMenu = noteMenu;
        this.eventTypeMenu = eventTypeMenu;
        this.adressMenu = adressMenu;
        this.eventMenu = eventMenu;
    }

    public void work() throws ParseException {
        printHelp();

        while (true){
            printHelp();
        }
    }

    private void printHelp() throws ParseException {
        System.out.println("Здравствуйте!");
        System.out.println("Для меню работы с пользователями введите 1");
        System.out.println("Для меню работы с заметками введите 2");
        System.out.println("Для меню работы с событиями введите 3");
        System.out.println("Для меню работы с типами событий введите 4");
        System.out.println("Для меню работы с адресами событий введите 5");
        System.out.println("Для выхода введите 0");


        int answer = scanner.nextInt();
        switch(answer) {
            case 1:
                printMenu(Entity.Пользователь);
                break;
            case 2:
                printMenu(Entity.Заметка);
                break;
            case 3:
                printMenu(Entity.Событие);
                break;
            case 4:
                printMenu(Entity.ТипСобытия);
                break;
            case 5:
                printMenu(Entity.Адрес);
                break;
        }
    }

    private void printMenu(Entity entityName) throws ParseException {
        System.out.println("Меню сущности " + entityName);
        System.out.println("Для создания сущности введите 1");
        System.out.println("Для вывода всех сущностей введите 2");
        System.out.println("Для вывода сущности по id введите 3");
        System.out.println("Для изменения сущности введите 4");
        System.out.println("Для удаления сущности введите 5");

        int answer = scanner.nextInt();
        switch (answer) {
            case 1:
                save(entityName);
                break;
            case 2:
                findAll(entityName);
                break;
            case 3:
                findById(entityName);
                break;
            case 4:
                update(entityName);
                break;
            case 5:
                delete(entityName);
                break;
        }
    }

    private void save(Entity entityName) throws ParseException {
        switch (entityName) {
            case Пользователь:
                personMenu.save(scanner);
                break;
            case Заметка:
                noteMenu.save(scanner);
                break;
            case ТипСобытия:
                eventTypeMenu.save(scanner);
                break;
            case Адрес:
                adressMenu.save(scanner);
                break;
            case Событие:
                eventMenu.save(scanner);
                break;
        }
    }

    private void findAll(Entity entityName) {
        switch (entityName) {
            case Пользователь:
                personMenu.findAll();
                break;
            case Заметка:
                noteMenu.findAll();
                break;
            case ТипСобытия:
                eventTypeMenu.findAll();
                break;
            case Адрес:
                adressMenu.findAll();
                break;
            case Событие:
                eventMenu.findAll();
                break;
        }
    }

    private void findById(Entity entityName) {
        switch (entityName) {
            case Пользователь:
                personMenu.findById(scanner);
                break;
            case Заметка:
                noteMenu.findById(scanner);
                break;
            case ТипСобытия:
                eventTypeMenu.findById(scanner);
                break;
            case Адрес:
                adressMenu.findById(scanner);
                break;
            case Событие:
                eventMenu.findById(scanner);
                break;
        }
    }

    private void update(Entity entityName) throws ParseException {
        switch (entityName) {
            case Пользователь:
                personMenu.update(scanner);
                break;
            case Заметка:
                noteMenu.update(scanner);
                break;
            case ТипСобытия:
                eventTypeMenu.update(scanner);
                break;
            case Адрес:
                adressMenu.update(scanner);
                break;
            case Событие:
                eventMenu.update(scanner);
                break;
        }
    }

    private void delete(Entity entityName) {
        switch (entityName) {
            case Пользователь:
                personMenu.delete(scanner);
                break;
            case Заметка:
                noteMenu.delete(scanner);
                break;
            case ТипСобытия:
                eventTypeMenu.delete(scanner);
                break;
            case Адрес:
                adressMenu.delete(scanner);
                break;
            case Событие:
                eventMenu.delete(scanner);
        }
    }


}
