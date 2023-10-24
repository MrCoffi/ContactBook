package org.example;

public interface ContactImpl {
    void changePerson();
    void removeContact(String input);
    void getAllContact();
    void addPersonToList(String input);
    void saveContactsToFile();
    String replaceForInfo(String p);
    String replaceForSave(String text);
}
