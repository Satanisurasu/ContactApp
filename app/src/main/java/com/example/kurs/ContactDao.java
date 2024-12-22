package com.example.kurs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(ModelContact contact);

    @Update
    void update(ModelContact contact);

    @Delete
    void delete(ModelContact contact);

    @Query("SELECT * FROM contacts WHERE id = :contactId LIMIT 1")
    LiveData<ModelContact> getContactById(int contactId);

    @Query("SELECT * FROM contacts")
    LiveData<List<ModelContact>> getAllContacts();

    // Добавляем запрос для поиска контактов по имени, телефону и email
    @Query("SELECT * FROM contacts WHERE name LIKE :name OR phone LIKE :phone OR email LIKE :email")
    LiveData<List<ModelContact>> searchContacts(String name, String phone, String email);
}

