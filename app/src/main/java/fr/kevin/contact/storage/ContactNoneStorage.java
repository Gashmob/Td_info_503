package fr.kevin.contact.storage;

import java.util.List;

import fr.kevin.contact.model.Contact;
import fr.kevin.contact.storage.utility.Storage;

public class ContactNoneStorage implements Storage<Contact> {

    private static ContactNoneStorage STORAGE;

    public static ContactNoneStorage get() {
        if (STORAGE == null) {
            STORAGE = new ContactNoneStorage();
        }
        return STORAGE;
    }

    private ContactNoneStorage() {
    }

    @Override
    public void insert(Contact object) {
    }

    @Override
    public List<Contact> findAll() {
        return null;
    }

    @Override
    public Contact find(int id) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void update(int id, Contact object) {
    }

    @Override
    public void delete(int id) {
    }
}
