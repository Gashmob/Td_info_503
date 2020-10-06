package fr.kevin.contact.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import fr.kevin.contact.R;
import fr.kevin.contact.adapter.ContactAdapter;
import fr.kevin.contact.model.Contact;

public class ListActivity extends AppCompatActivity {

    public static final String EXTRA_CONTACT = "contact";

    private RecyclerView list;

    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        contacts = new ArrayList<>();
        contacts.add(new Contact("Michel", "Camil", "06 34 34 54 26", "04 45 62 25 52", "michelcamil@monsupersite.fr", "14, rue des escadrilles, 95000 PARIS"));
        contacts.add(new Contact("Jean", "Van"));
        contacts.add(new Contact("Luc", "Roe"));
        contacts.add(new Contact("Lea", "Kole"));

        list = findViewById(R.id.contact_list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setAdapter(new ContactAdapter(contacts) {
            @Override
            public void onItemClick(View v) {
                Contact contact = contacts.get(list.getChildViewHolder(v).getAdapterPosition());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(EXTRA_CONTACT, contact);
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View v) {
                Contact contact = contacts.get(list.getChildViewHolder(v).getAdapterPosition());
                Toast.makeText(getApplicationContext(), getString(R.string.erase_toast_message, contact.getFirstName(), contact.getLastName()), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        ((FloatingActionButton) findViewById(R.id.contact_add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Add a contact", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }
}