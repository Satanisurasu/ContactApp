package com.example.kurs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<ModelContact> contacts;
    private Context context;

    public ContactAdapter(Context context) {
        this.context = context;
        this.contacts = new ArrayList<>();
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        ModelContact contact = contacts.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhone());
        holder.groupTextView.setText(contact.getGroup());
        holder.emailTextView.setText(contact.getEmail());
        holder.notesTextView.setText(contact.getNotes());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(List<ModelContact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneTextView;
        TextView groupTextView;
        TextView emailTextView;
        TextView notesTextView;

        public ContactViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.contactName);
            phoneTextView = itemView.findViewById(R.id.contactPhone);
            groupTextView = itemView.findViewById(R.id.contactGroup);
            emailTextView = itemView.findViewById(R.id.contactEmail);
            notesTextView = itemView.findViewById(R.id.contactNotes);
        }
    }
}
