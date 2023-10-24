package org.example;

import org.springframework.stereotype.Component;
@Component
public class ProfileWorker {
    private final ContactImpl contact;

    public ProfileWorker(ContactImpl contact) {
        this.contact = contact;
    }

    public void doWork(){
        contact.changePerson();
    }
}
