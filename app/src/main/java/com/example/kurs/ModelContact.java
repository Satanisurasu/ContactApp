package com.example.kurs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class ModelContact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;
    private String group;
    private String notes;
    private String email;

    // Конструкторы
    public ModelContact(String name, String phone, String group, String notes, String email) {
        this.name = name;
        this.phone = phone;
        this.group = group;
        this.notes = notes;
        this.email = email;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
