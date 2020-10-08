package fr.kevin.contact.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.kevin.contact.R;
import fr.kevin.contact.model.Contact;

abstract public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactHolder> {

    static class ContactHolder extends RecyclerView.ViewHolder {
        public ImageView photo;
        public TextView name;

        public ContactHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.item_contact_photo);
            name = itemView.findViewById(R.id.item_contact_name);
        }
    }

    private List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return onItemLongClick(v);
            }
        });

        return new ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        String s = contacts.get(position).getFirstName() + " " + contacts.get(position).getLastName();
        holder.name.setText(s);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public abstract void onItemClick(View v);

    public abstract boolean onItemLongClick(View v);
}
