package com.example.kurs;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class AddContactActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText groupEditText;
    private EditText emailEditText;
    private EditText notesEditText;
    private Button saveContactButton;

    // ViewModel для контактов
    private ContactViewModel contactViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        // Инициализация полей
        nameEditText = findViewById(R.id.contactNameEditText);
        phoneEditText = findViewById(R.id.contactPhoneEditText);
        groupEditText = findViewById(R.id.contactGroupEditText);
        emailEditText = findViewById(R.id.contactEmailEditText);
        notesEditText = findViewById(R.id.contactNotesEditText);
        saveContactButton = findViewById(R.id.saveContactButton);

        // Получение ViewModel для работы с данными
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        // Обработчик нажатия на кнопку "Сохранить"
        saveContactButton.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();
            String group = groupEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String notes = notesEditText.getText().toString();

            // Проверка на обязательные поля (например, имя и телефон)
            if (!name.isEmpty() && !phone.isEmpty()) {
                // Создаем новый объект контакта
                ModelContact newContact = new ModelContact(name, phone, group, notes, email);

                // Добавляем контакт в базу данных через ViewModel
                contactViewModel.addContact(newContact);

                // Закрываем активность после сохранения
                Toast.makeText(this, "Контакт сохранен", Toast.LENGTH_SHORT).show();
                finish();  // Закрываем текущую активность
            } else {
                // Если не все обязательные поля заполнены
                Toast.makeText(this, "Пожалуйста, заполните имя и телефон", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
