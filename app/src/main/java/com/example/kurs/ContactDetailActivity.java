package com.example.kurs;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ContactDetailActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, emailEditText, groupEditText, notesEditText;
    private Button saveButton;
    private ContactViewModel contactViewModel;
    private int contactId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        emailEditText = findViewById(R.id.emailEditText);
        groupEditText = findViewById(R.id.groupEditText);
        notesEditText = findViewById(R.id.notesEditText);
        saveButton = findViewById(R.id.saveButton);

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        contactId = getIntent().getIntExtra("contactId", -1);

        if (contactId != -1) {
            // Загружаем контакт для редактирования
            contactViewModel.getContactById(contactId).observe(this, contact -> {
                if (contact != null) {
                    nameEditText.setText(contact.getName());
                    phoneEditText.setText(contact.getPhone());  // Используем getPhone()
                    emailEditText.setText(contact.getEmail());
                    groupEditText.setText(contact.getGroup());
                    notesEditText.setText(contact.getNotes());
                }
            });
        }

        saveButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String group = groupEditText.getText().toString();
            String notes = notesEditText.getText().toString();

            if (contactId == -1) {
                // Добавляем новый контакт
                contactViewModel.addContact(new ModelContact(name, phone, email, group, notes));  // Используем новый конструктор
            } else {
                // Обновляем существующий контакт
                contactViewModel.updateContact(new ModelContact(name, phone, email, group, notes));  // Используем новый конструктор
            }
            finish();
        });
    }
}

