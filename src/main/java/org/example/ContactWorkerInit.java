package org.example;

import lombok.NoArgsConstructor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class ContactWorkerInit extends AbstractContactService {
    private Set<String> myContacts = new HashSet<>();


    @Override
    public void changePerson() {
        loadContactsFromFile();
        super.changePerson();
    }

    @Override
    public void getAllContact() {
        myContacts.addAll(super.getPerson());
        for (String line : myContacts) {
            System.out.println(replaceForInfo(line).replaceAll(",", "|"));
        }
    }

    @Override
    public void removeContact(String input) {
        myContacts.removeIf(p -> p.contains(input));
    }

    @Override
    public void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(super.getFilePath()))) {

            if (!myContacts.isEmpty()) {
                for (String p : myContacts) {
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

    private void loadContactsFromFile() {
        try {
            myContacts.addAll(Files.readAllLines(Paths.get(super.getFilePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}