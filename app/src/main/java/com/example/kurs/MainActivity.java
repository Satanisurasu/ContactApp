package com.example.kurs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ContactViewModel contactViewModel;
    private SearchView searchView;
    private FloatingActionButton addContactButton;
    private List<ModelContact> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        contactAdapter = new ContactAdapter(this, contactViewModel); // Передаём ViewModel в адаптер
        recyclerView.setAdapter(contactAdapter);

        contactViewModel.getAllContacts().observe(this, contacts -> {
            if (contacts != null) {
                contactAdapter.setContacts(contacts);
            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                contactViewModel.searchContacts(query, query, query).observe(MainActivity.this, contacts -> {
                    if (contacts != null) {
                        contactAdapter.setContacts(contacts);
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                contactViewModel.searchContacts(newText, newText, newText).observe(MainActivity.this, contacts -> {
                    if (contacts != null) {
                        contactAdapter.setContacts(contacts);
                    }
                });
                return false;
            }
        });

        addContactButton = findViewById(R.id.addContactButton);
        addContactButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            startActivity(intent);
        });

        Button sortButton = findViewById(R.id.sortButton);
        sortButton.setOnClickListener(v -> {
            contactViewModel.getContactsSortedByGroup().observe(this, sortedContacts -> {
                if (sortedContacts != null) {
                    contactAdapter.setContacts(sortedContacts);
                    Toast.makeText(this, "Список отсортирован по группам", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}

