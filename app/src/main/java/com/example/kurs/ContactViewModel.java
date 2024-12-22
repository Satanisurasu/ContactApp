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
        contactRepository.insert(contact);  // Вставка контакта в репозиторий
    }

    public void updateContact(ModelContact contact) {
        contactRepository.update(contact);
    }

    public void deleteContact(ModelContact contact) {
        contactRepository.delete(contact);
    }

    public void searchContacts(String name, String phone, String email) {
        contactRepository.searchContacts(name, phone, email);
    }

    public LiveData<ModelContact> getContactById(int contactId) {
        return contactRepository.getContactById(contactId);
    }
}
