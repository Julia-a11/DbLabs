package ru.db.util;

import de.vandermeer.asciitable.AsciiTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.db.models.Adress;
import ru.db.models.Event;
import ru.db.models.EventType;
import ru.db.models.Person;
import ru.db.repos.EventRepos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class EventMenu {
    private final EventRepos repos;

    private final PersonMenu personMenu;
    private final EventTypeMenu eventTypeMenu;
    private final AdressMenu adressMenu;

    @Autowired
    public EventMenu(EventRepos repos, PersonMenu personMenu, EventTypeMenu eventTypeMenu, AdressMenu adressMenu) {
        this.repos = repos;
        this.personMenu = personMenu;
        this.eventTypeMenu = eventTypeMenu;
        this.adressMenu = adressMenu;
    }

    public void save(Scanner scanner) throws ParseException {
        Event event = new Event();

        System.out.print("Введите название: ");
        scanner.skip("[\r\n]+");
        String name = scanner.nextLine();
        event.setName(name);

        System.out.print("Введите дату: ");
        String date = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date parsed = format.parse(date);
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        event.setDate(sql);
        repos.save(event);

        System.out.println("Выберите тип: ");
        event.setEventType(chooseEventType(scanner));

        System.out.println("Выберите адресс: ");
        event.setAdress(chooseAdress(scanner));

        System.out.println("Выберите пользователей: ");
        event.setPersonList(choosePerson(scanner));

        repos.save(event);
        System.out.println("Событие сохранено");
    }

    public void findAll() {
        List<Event> eventList = (List<Event>) repos.findAll();

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Название", "Дата", "Тип события", "Адрес", "Участники");
        table.addRule();
        for (Event event : eventList) {
            table.addRow(event.getId(), event.getName(), event.getDate(),
                    event.getEventType() != null ? event.getEventType().getType() : "",
                    event.getAdress() != null ? event.getAdress().getCity() + " "
                            + event.getAdress().getStreet() : "",
                    getPersons(event.getPersonList()));
            table.addRule();
        }
        System.out.println(table.render());
    }

    public Event findById(Scanner scanner) {
        Event event = checkEvent(scanner);
        if (event == null) {
            return null;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Название", "Дата", "Тип события", "Адрес");
        table.addRule();
        table.addRow(event.getId(), event.getName(), event.getDate(),
                event.getEventType().getType(), event.getAdress().getCity() + " " + event.getAdress().getStreet());
        table.addRule();
        System.out.println(table.render());
        return event;
    }

    public void update(Scanner scanner) throws ParseException {
        Event event = checkEvent(scanner);
        if (event == null) {
            return;
        }

        System.out.print("Введите название: ");
        scanner.skip("[\r\n]+");
        String name = scanner.nextLine();
        event.setName(name);

        System.out.print("Введите дату: ");
        String date = scanner.nextLine();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date parsed = format.parse(date);
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        event.setDate(sql);

        System.out.print("Выберите тип: ");
        event.setEventType(chooseEventType(scanner));

        System.out.print("Выберите адресс: ");
        event.setAdress(chooseAdress(scanner));

        repos.save(event);
        System.out.println("Событие обновлено");
    }

    public void delete(Scanner scanner) {
        Event Event = checkEvent(scanner);
        if (Event == null) {
            return;
        }

        repos.delete(Event);
        System.out.println("Событие удалено");
    }

    private Event checkEvent(Scanner scanner) {
        System.out.println("Введите ID");
        int id = scanner.nextInt();

        if (repos.findById(id).isPresent()) {
            return repos.findById(id).get();
        } else {
            System.out.println("Событие не найдено");
            return null;
        }
    }

    private EventType chooseEventType(Scanner scanner) {
        eventTypeMenu.findAll();
        return eventTypeMenu.findById(scanner);
    }

    private Adress chooseAdress(Scanner scanner) {
        adressMenu.findAll();
        return adressMenu.findById(scanner);
    }

    private List<Person> choosePerson(Scanner scanner) {
        personMenu.findAll();
        List<Person> personList = new ArrayList<>();
        int asnwer = -1;
        while (asnwer != 0) {
            personList.add(personMenu.findById(scanner));
            System.out.println("Введите 0 чтобы закончить ввод");
            asnwer = scanner.nextInt();
        }
        return personList;
    }

    private String getPersons(List<Person> personList) {
        StringBuilder result = new StringBuilder();
        personList.forEach(e -> result.append(e.getName()).append(" ").append(e.getSurname()));
        return result.toString();
    }
}
