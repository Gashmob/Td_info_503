package fr.kevin.contact.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.kevin.contact.R;
import fr.kevin.contact.adapter.ContactAdapter;
import fr.kevin.contact.dialog.ContactDialogFragment;
import fr.kevin.contact.dialog.DeleteDialogFragment;
import fr.kevin.contact.dialog.Updatable;
import fr.kevin.contact.model.Contact;
import fr.kevin.contact.storage.ContactStorage;

public class ListActivity extends AppCompatActivity implements Updatable {

    public static final String EXTRA_CONTACT = "contact";

    private RecyclerView list;

    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        contacts = ContactStorage.get(getApplicationContext()).findAll();

        list = findViewById(R.id.contact_list);
        list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        list.setAdapter(new ContactAdapter(getApplicationContext()) {
            @Override
            public void onItemClick(View v) {
                Contact contact = contacts.get(list.getChildViewHolder(v).getAdapterPosition());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(EXTRA_CONTACT, (Integer) v.getTag());
                startActivity(intent);
            }

            @Override
            public boolean onItemLongClick(View v) {
                (new DeleteDialogFragment(ListActivity.this, (int) v.getTag())).show(getSupportFragmentManager(), "");
                return true;
            }
        });

        findViewById(R.id.contact_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                (new ContactDialogFragment(ListActivity.this)).show(getSupportFragmentManager(), "");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        int id = R.id.storage_none;
        switch (ContactStorage.getPreferencesStorage(getApplicationContext())) {
            case ContactStorage.PREF_STORAGE_FILE_JSON:
                id = R.id.storage_json_file;
                break;

            case ContactStorage.PREF_STORAGE_DATABASE:
                id = R.id.storage_database;
        }
        menu.findItem(id).setChecked(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int prefStorage = ContactStorage.PREF_STORAGE_NONE;
        switch (item.getItemId()) {
            case R.id.storage_json_file:
                prefStorage = ContactStorage.PREF_STORAGE_FILE_JSON;
                break;

            case R.id.storage_database:
                prefStorage = ContactStorage.PREF_STORAGE_DATABASE;
        }
        ContactStorage.setPreferencesStorage(getApplicationContext(), prefStorage);
        list.getAdapter().notifyDataSetChanged();
        return true;
    }

    @Override
    protected void onStart() {
        update();
        super.onStart();
    }

    @Override
    public void update() {
        list.getAdapter().notifyDataSetChanged();
    }
}