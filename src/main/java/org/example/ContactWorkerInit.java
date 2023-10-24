package org.example;

import lombok.NoArgsConstructor;
import org.example.Entity.Person;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
public class ContactWorkerInit extends AbstractContactService {
    private Set<Person> myContacts = new HashSet<>();


    @Override
    public void changePerson() {
        loadContactsFromFile();
        super.changePerson();
    }

    @Override
    public void getAllContact() {
        myContacts.addAll(super.getPerson());
        for (Person person : myContacts) {
            System.out.println(person.toString());
        }
    }

    @Override
    public void addPersonToList(String input) {
        String[] st = input.split(";");
        Person person1 = new Person(st[0], st[1], st[2]);

        if (!isDuplicateEmail(person1.getEmail())) {
            myContacts.add(person1);
            System.out.println("Person added successfully.");
        } else {
            System.out.println("Person with the same e-mail already exists.");
        }
    }

    @Override
    public boolean isDuplicateEmail(String eMail) {
        for (Person p : myContacts) {
            if (p.getEmail().equals(eMail)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeContact(String input) {
        myContacts.removeIf(person -> person.getEmail().equals(input));
    }

    @Override
    public void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(super.getFilePath()))) {

            if (!myContacts.isEmpty()) {
                for (Person person : myContacts) {
                    if (person != null) {
                        writer.write(person.getName() + ";" + person.getPhone() + ";" + person.getEmail());
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


    private void loadContactsFromFile() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(super.getFilePath()));
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Person person = new Person();
                    person.setName(parts[0]);
                    person.setPhone(parts[1]);
                    person.setEmail(parts[2]);
                    if (!isDuplicateEmail(person.getEmail())) {
                        myContacts.add(person);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}