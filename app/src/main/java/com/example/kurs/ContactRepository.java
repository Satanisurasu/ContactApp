package com.example.kurs;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactRepository {

    private ContactDao contactDao;
    private LiveData<List<ModelContact>> allContacts;

    public ContactRepository(Application application) {
        ContactDatabase db = ContactDatabase.getDatabase(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAllContacts();
    }

    public LiveData<List<ModelContact>> getAllContacts() {
        return allContacts;
    }

    public void insert(ModelContact contact) {
        new InsertAsyncTask(contactDao).execute(contact);
    }

    public void update(ModelContact contact) {
        new UpdateAsyncTask(contactDao).execute(contact);
    }

    public void delete(ModelContact contact) {
        new DeleteAsyncTask(contactDao).execute(contact);
    }

    public void searchContacts(String name, String phone, String email) {
        // Реализация поиска по имени, телефону и email
    }

    public LiveData<ModelContact> getContactById(int contactId) {
        return contactDao.getContactById(contactId);
    }

    // AsyncTask для вставки
    private static class InsertAsyncTask extends AsyncTask<ModelContact, Void, Void> {
        private ContactDao asyncTaskDao;

        InsertAsyncTask(ContactDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ModelContact... contacts) {
            asyncTaskDao.insert(contacts[0]);
            return null;
        }
    }

    // AsyncTask для обновления
    private static class UpdateAsyncTask extends AsyncTask<ModelContact, Void, Void> {
        private ContactDao asyncTaskDao;

        UpdateAsyncTask(ContactDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ModelContact... contacts) {
            asyncTaskDao.update(contacts[0]);
            return null;
        }
    }

    // AsyncTask для удаления
    private static class DeleteAsyncTask extends AsyncTask<ModelContact, Void, Void> {
        private ContactDao asyncTaskDao;

        DeleteAsyncTask(ContactDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ModelContact... contacts) {
            asyncTaskDao.delete(contacts[0]);
            return null;
        }
    }
}
