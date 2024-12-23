package com.example.kurs;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepository contactRepository;
    private LiveData<List<ModelContact>> allContacts;

    public ContactViewModel(Application application) {
        super(application);
        contactRepository = new ContactRepository(application);
        allContacts = contactRepository.getAllContacts();
    }

    public LiveData<List<ModelContact>> getAllContacts() {
        return allContacts;
    }

    public void addContact(ModelContact contact) {
        contactRepository.insert(contact);
    }

    public void updateContact(ModelContact contact) {
        contactRepository.update(contact);
    }

    public void deleteContact(ModelContact contact) {
        contactRepository.delete(contact);
    }

    public LiveData<List<ModelContact>> searchContacts(String name, String phone, String email) {
        return contactRepository.searchContacts(name, phone, email);
    }

    public LiveData<ModelContact> getContactById(int contactId) {
        return contactRepository.getContactById(contactId);
    }

    public LiveData<List<ModelContact>> getContactsSortedByGroup() {
        return contactRepository.getContactsSortedByGroup();
    }

}

