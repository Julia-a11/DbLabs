package ru.db.util;

import de.vandermeer.asciitable.AsciiTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.db.models.EventType;
import ru.db.models.EventType;
import ru.db.repos.EventTypeRepos;
import ru.db.repos.EventTypeRepos;

import java.util.List;
import java.util.Scanner;

@Component
public class EventTypeMenu {

    private final EventTypeRepos repos;

    @Autowired
    public EventTypeMenu(EventTypeRepos repos) {
        this.repos = repos;
    }

    public void save(Scanner scanner) {
        EventType eventType = new EventType();

        System.out.print("Введите тип: ");
        scanner.skip("[\r\n]+");
        String name = scanner.nextLine();
        eventType.setType(name);

        System.out.print("Введите приоритет: ");
        int priority = scanner.nextInt();
        eventType.setPriority(priority);
        
        repos.save(eventType);
        System.out.println("Тип события сохранен");
    }

    public void findAll() {
        List<EventType> eventTypeList = (List<EventType>) repos.findAll();

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Тип", "Приоритет");
        table.addRule();
        for (EventType eventType : eventTypeList) {
            table.addRow(eventType.getId(), eventType.getType(), eventType.getPriority());
            table.addRule();
        }
        System.out.println(table.render());
    }

    public EventType findById(Scanner scanner) {
        EventType eventType = checkEventType(scanner);
        if (eventType == null) {
            return null;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Тип", "Приоритет");
        table.addRule();
        table.addRow(eventType.getId(), eventType.getType(), eventType.getPriority());
        table.addRule();
        System.out.println(table.render());
        return eventType;
    }

    public void update(Scanner scanner) {
        EventType eventType = checkEventType(scanner);
        if (eventType == null) {
            return;
        }

        System.out.print("Введите тип: ");
        String name = scanner.next();
        eventType.setType(name);

        System.out.print("Введите приоритет: ");
        int priority = scanner.nextInt();
        eventType.setPriority(priority);

        repos.save(eventType);
        System.out.println("Тип события обновлен");
    }

    public void delete(Scanner scanner) {
        EventType eventType = checkEventType(scanner);
        if (eventType == null) {
            return;
        }

        repos.delete(eventType);
        System.out.println("Тип события удален");
    }

    private EventType checkEventType(Scanner scanner) {
        System.out.println("Введите ID");
        int id = scanner.nextInt();

        if (repos.findById(id).isPresent()) {
            return repos.findById(id).get();
        } else {
            System.out.println("Тип события не найден");
            return null;
        }
    }
}
