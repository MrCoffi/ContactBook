package org.example;

import org.example.Entity.Person;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class AbstractContactService implements ContactImpl {
    @Value("${contact.path}")
    private String filePath;
    private Set<Person> person = new HashSet<>();
    Scanner s = new Scanner(System.in);

    @Override
    public void changePerson() {
        while (true) {
            String input = s.nextLine();
            switch (input) {
                case "0" -> {
                    saveContactsToFile();
                    return;
                }
                case "remove" -> {
                    System.out.println("Please write to remove e-mail");
                    input = s.nextLine();
                    removeContact(input);
                }
                case "info" -> getAllContact();
                default -> {
                    if (input.split(";").length == 3) {
                        addPersonToList(input);
                    } else {
                        System.out.println("Sorry, but expected 3 value");
                    }
                }
            }
        }
    }

    @Override
    public void removeContact(String input) {
        person.removeIf(person -> person.getEmail().equals(input));
    }

    @Override
    public void getAllContact() {
        if (person.isEmpty()) {
            System.out.println("person's empty");
        } else {
            for (Person one : person) {
                System.out.println(one.toString());
            }
        }
    }

    @Override
    public void addPersonToList(String input) {
        String[] st = input.split(";");
        Person person1 = new Person(st[0], st[1], st[2]);

        if (!isDuplicateEmail(person1.getEmail())) {
            person.add(person1);
            System.out.println("Person added successfully.");
        } else {
            System.out.println("Person with the same e-mail already exists.");
        }
    }

    public boolean isDuplicateEmail(String eMail) {
        for (Person p : person) {
            if (p.getEmail().equals(eMail)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(),true))) {
            if (!person.isEmpty()) {
                for (Person one : person) {
                    if (one != null) {
                        writer.write(one.getName() + ";" + one.getPhone() + ";" + one.getEmail());
                        writer.newLine();
                    }
                }
            }
            writer.close();
            System.out.println("Contacts are appended to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public Set<Person> getPerson() {
        return person;
    }
}