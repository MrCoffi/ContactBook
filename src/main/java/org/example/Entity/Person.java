package org.example.Entity;

public class Person {
    private String name;
    private String phone;
    private String email;

    public Person(String name, String phone, String eMail) {
        this.name = name;
        this.phone = phone;
        this.email = eMail;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return name + "|" + phone + "|" + email;
    }
}
