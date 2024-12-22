package com.example.kurs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView; // Используем правильный класс SearchView
import com.google.android.material.floatingactionbutton.FloatingActionButton; // Добавляем правильный импорт для FloatingActionButton

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ContactViewModel contactViewModel;
    private SearchView searchView;
    private FloatingActionButton addContactButton;  // Используем FloatingActionButton, а не Button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Используем правильный ID для RecyclerView
        recyclerView = findViewById(R.id.recyclerView);  // Исправляем на recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Устанавливаем менеджер компоновки
        contactAdapter = new ContactAdapter(this); // Контакт адаптер
        recyclerView.setAdapter(contactAdapter);  // Устанавливаем адаптер

        // Инициализация ViewModel
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        // Наблюдаем за изменениями в контактах
        contactViewModel.getAllContacts().observe(this, contacts -> {
            if (contacts != null) {
                contactAdapter.setContacts(contacts);  // Обновляем список в адаптере
            }
        });

        // Поиск по контактам
        searchView = findViewById(R.id.searchView);  // Здесь правильно находим SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                contactViewModel.searchContacts(query, query, query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactViewModel.searchContacts(newText, newText, newText);
                return false;
            }
        });

        // Кнопка для добавления нового контакта
        addContactButton = findViewById(R.id.addContactButton);  // Теперь это FloatingActionButton
        addContactButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
        });
    }
}
