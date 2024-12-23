package com.example.kurs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<ModelContact> contacts;
    private Context context;
    private ContactViewModel contactViewModel;

    public ContactAdapter(Context context, ContactViewModel contactViewModel) {
        this.context = context;
        this.contactViewModel = contactViewModel;
        this.contacts = new ArrayList<>();
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ModelContact contact = contacts.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhone());
        holder.groupTextView.setText(contact.getGroup());
        holder.emailTextView.setText(contact.getEmail());
        holder.notesTextView.setText(contact.getNotes());

        // Обработчик клика на кнопку редактирования
        holder.editButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditContactActivity.class);
            intent.putExtra("contact_id", contact.getId()); // Передаём ID контакта
            context.startActivity(intent);
        });

        // Обработчик клика на кнопку удаления
        holder.deleteButton.setOnClickListener(v -> {
            showDeleteConfirmationDialog(contact);
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(List<ModelContact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    private void showDeleteConfirmationDialog(ModelContact contact) {
        new AlertDialog.Builder(context)
                .setMessage("Вы уверены, что хотите удалить этот контакт?")
                .setCancelable(false)
                .setPositiveButton("Удалить", (dialog, id) -> {
                    contactViewModel.deleteContact(contact); // Удаляем контакт
                    Toast.makeText(context, "Контакт удалён", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Отмена", (dialog, id) -> dialog.dismiss())
                .create()
                .show();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneTextView;
        TextView groupTextView;
        TextView emailTextView;
        TextView notesTextView;
        ImageButton editButton; // Кнопка редактирования
        ImageButton deleteButton; // Кнопка удаления

        public ContactViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contactName);
            phoneTextView = itemView.findViewById(R.id.contactPhone);
            groupTextView = itemView.findViewById(R.id.contactGroup);
            emailTextView = itemView.findViewById(R.id.contactEmail);
            notesTextView = itemView.findViewById(R.id.contactNotes);
            editButton = itemView.findViewById(R.id.editButton); // Находим кнопку редактирования
            deleteButton = itemView.findViewById(R.id.deleteButton); // Находим кнопку удаления
        }
    }
}



