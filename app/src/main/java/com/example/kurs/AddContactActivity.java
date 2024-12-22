package com.example.kurs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText groupEditText;
    private EditText emailEditText;
    private EditText notesEditText;
    private Button saveContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        nameEditText = findViewById(R.id.contactNameEditText);
        phoneEditText = findViewById(R.id.contactPhoneEditText);
        groupEditText = findViewById(R.id.contactGroupEditText);
        emailEditText = findViewById(R.id.contactEmailEditText);
        notesEditText = findViewById(R.id.contactNotesEditText);
        saveContactButton = findViewById(R.id.saveContactButton);

        saveContactButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String group = groupEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String notes = notesEditText.getText().toString();

            // Логика сохранения нового контакта в базу данных или в ViewModel
            ModelContact newContact = new ModelContact(name, phone, group, notes, email);
            // Например, ты можешь добавить его в список в ViewModel или напрямую в адаптер
        });
    }
}
