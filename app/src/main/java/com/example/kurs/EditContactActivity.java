package com.example.kurs;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        // Получение ID контакта из Intent
        int contactId = getIntent().getIntExtra("contact_id", -1);

        // TODO: Загрузка данных контакта по ID и отображение их в полях для редактирования
    }
}
