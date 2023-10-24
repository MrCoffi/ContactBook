package org.example;

import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class AbstractContactService implements ContactImpl {
    @Value("${contact.path}")
    private String filePath;
    private Set<String> person = new HashSet<>();
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
        person.removeIf(p -> p.contains(input));
    }

    @Override
    public void getAllContact() {
        if (person.isEmpty()) {
            System.out.println("person's empty");
        } else {
            for (String p : person) {
                System.out.println(replaceForInfo(p));
            }
        }
    }

    @Override
    public void addPersonToList(String input) {
        String[] st = input.split(";");
        person.add(Arrays.toString(st));
    }

    @Override
    public void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            if (!person.isEmpty()) {
                for (String p : person) {
                    if (!p.isEmpty()) {
                        writer.write(replaceForSave(p));
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

    @Override
    public String replaceForSave(String text) {
        return text
                .replace("[", "")
                .replace("]", "")
                .replaceAll(",|//s", ";")
                .replaceAll(" ", "");
    }

    @Override
    public String replaceForInfo(String p) {
        return p
                .replace("[", "")
                .replace("]", "")
                .replaceAll(",", "|")
                .replaceAll(" ", "");
    }

    public String getFilePath() {
        return filePath;
    }

    public Set<String> getPerson() {
        return person;
    }

}
