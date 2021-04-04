package ru.db.util;

import de.vandermeer.asciitable.AsciiTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.db.models.Person;
import ru.db.repos.PersonRepos;

import java.util.List;
import java.util.Scanner;

@Component
public class PersonMenu {

    private final PersonRepos repos;

    @Autowired
    public PersonMenu(PersonRepos repos) {
        this.repos = repos;
    }

    public void save(Scanner scanner) {
        Person person = new Person();

        System.out.print("Введите имя: ");
        scanner.skip("[\r\n]+");
        String name = scanner.nextLine();
        person.setName(name);

        System.out.print("Введите фамилию: ");
        String surname = scanner.nextLine();
        person.setSurname(surname);

        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        person.setLogin(login);

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        person.setPassword(password);

        repos.save(person);
        System.out.println("Пользователь сохранен");
    }

    public void findAll() {
        List<Person> personList = (List<Person>) repos.findAll();

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Имя", "Фамилия", "Логин", "Пароль");
        table.addRule();
        for (Person person : personList) {
            table.addRow(person.getId(), person.getName(), person.getSurname(),
                    person.getLogin(), person.getPassword());
            table.addRule();
        }
        System.out.println(table.render());
    }

    public Person findById(Scanner scanner) {
        Person person = checkPerson(scanner);
        if (person == null) {
            return null;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Имя", "Фамилия", "Логин", "Пароль");
        table.addRule();
        table.addRow(person.getId(), person.getName(), person.getSurname(),
                person.getLogin(), person.getPassword());
        table.addRule();
        System.out.println(table.render());
        return person;
    }

    public void update(Scanner scanner) {
        Person person = checkPerson(scanner);
        if (person == null) {
            return;
        }

        System.out.print("Введите имя: ");
        scanner.skip("[\r\n]+");
        String name = scanner.nextLine();
        person.setName(name);

        System.out.print("Введите фамилию: ");
        String surname = scanner.nextLine();
        person.setSurname(surname);

        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        person.setLogin(login);

        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        person.setPassword(password);

        repos.save(person);
        System.out.println("Пользователь обновлен");
    }

    public void delete(Scanner scanner) {
        Person person = checkPerson(scanner);
        if (person == null) {
            return;
        }

        repos.delete(person);
        System.out.println("Пользователь удален");
    }

    private Person checkPerson(Scanner scanner) {
        System.out.println("Введите ID");
        int id = scanner.nextInt();

        if (repos.findById(id).isPresent()) {
            return repos.findById(id).get();
        } else {
            System.out.println("Пользователь не найден");
            return null;
        }
    }
}
