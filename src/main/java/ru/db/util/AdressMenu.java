package ru.db.util;

import de.vandermeer.asciitable.AsciiTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.db.models.Adress;
import ru.db.repos.AdressRepos;

import java.util.List;
import java.util.Scanner;

@Component
public class AdressMenu {
    private final AdressRepos repos;

    @Autowired
    public AdressMenu(AdressRepos repos) {
        this.repos = repos;
    }

    public void save(Scanner scanner) {
        Adress adress = new Adress();

        System.out.print("Введите город: ");
        scanner.skip("[\r\n]+");
        String city = scanner.nextLine();
        adress.setCity(city);

        System.out.print("Введите улицу: ");
        String street = scanner.nextLine();
        adress.setStreet(street);

        System.out.print("Введите номер дома: ");
        int houseHumber = scanner.nextInt();
        adress.setHouseNumber(houseHumber);

        repos.save(adress);
        System.out.println("Адрес сохранен");
    }

    public void findAll() {
        List<Adress> adressList = (List<Adress>) repos.findAll();

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Город", "Улица", "Номер дома");
        table.addRule();
        for (Adress adress : adressList) {
            table.addRow(adress.getId(), adress.getCity(), adress.getStreet(), adress.getHouseNumber());
            table.addRule();
        }
        System.out.println(table.render());
    }

    public Adress findById(Scanner scanner) {
        Adress adress = checkAdress(scanner);
        if (adress == null) {
            return null;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("ID", "Город", "Улица", "Номер дома");
        table.addRule();
        table.addRow(adress.getId(), adress.getCity(), adress.getStreet(), adress.getHouseNumber());
        table.addRule();
        System.out.println(table.render());
        return adress;
    }

    public void update(Scanner scanner) {
        Adress adress = checkAdress(scanner);
        if (adress == null) {
            return;
        }

        System.out.print("Введите город: ");
        scanner.skip("[\r\n]+");
        String city = scanner.nextLine();
        adress.setCity(city);

        System.out.print("Введите улицу: ");
        String street = scanner.nextLine();
        adress.setStreet(street);

        System.out.print("Введите номер дома: ");
        int houseHumber = scanner.nextInt();
        adress.setHouseNumber(houseHumber);

        repos.save(adress);
        System.out.println("Адрес обновлен");
    }

    public void delete(Scanner scanner) {
        Adress adress = checkAdress(scanner);
        if (adress == null) {
            return;
        }

        repos.delete(adress);
        System.out.println("Адрес удален");
    }

    private Adress checkAdress(Scanner scanner) {
        System.out.println("Введите ID");
        int id = scanner.nextInt();

        if (repos.findById(id).isPresent()) {
            return repos.findById(id).get();
        } else {
            System.out.println("Адрес не найден");
            return null;
        }
    }
}
