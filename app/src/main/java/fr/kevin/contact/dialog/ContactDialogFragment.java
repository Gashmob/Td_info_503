package fr.kevin.contact.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import fr.kevin.contact.R;
import fr.kevin.contact.model.Contact;
import fr.kevin.contact.storage.ContactStorage;

public class ContactDialogFragment extends DialogFragment {

    private static final int ID_NONE = -1;

    private Updatable updatable;
    private int id;
    private View view;

    public ContactDialogFragment(Updatable updatable) {
        this(updatable, ID_NONE);
    }

    public ContactDialogFragment(Updatable updatable, int id) {
        this.updatable = updatable;
        this.id = id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        view = requireActivity().getLayoutInflater().inflate(R.layout.dialog_contact, null);
        setContactToView();
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_edit_title)
                .setView(view)
                .setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Contact contact = getContactFromView();
                        if (ContactDialogFragment.this.id == ID_NONE) {
                            ContactStorage.get(getContext()).insert(contact);
                        } else {
                            ContactStorage.get(getContext()).update(ContactDialogFragment.this.id, contact);
                        }
                        updatable.update();
                    }
                })
                .setNegativeButton(R.string.dialog_negative_button, null)
                .create();
    }

    private Contact getContactFromView() {
        return new Contact(id,
                ((EditText) view.findViewById(R.id.first_name)).getText().toString(),
                ((EditText) view.findViewById(R.id.last_name)).getText().toString(),
                ((EditText) view.findViewById(R.id.phone)).getText().toString(),
                ((EditText) view.findViewById(R.id.home)).getText().toString(),
                ((EditText) view.findViewById(R.id.email)).getText().toString(),
                ((EditText) view.findViewById(R.id.location)).getText().toString()
                );
    }

    private void setContactToView() {
        Contact contact = ContactStorage.get(getContext()).find(id);
        if (contact != null) {
            ((EditText) view.findViewById(R.id.first_name)).setText(contact.getFirstName());
            ((EditText) view.findViewById(R.id.last_name)).setText(contact.getLastName());
            ((EditText) view.findViewById(R.id.phone)).setText(contact.getPhone());
            ((EditText) view.findViewById(R.id.home)).setText(contact.getHome());
            ((EditText) view.findViewById(R.id.email)).setText(contact.getMail());
            ((EditText) view.findViewById(R.id.location)).setText(contact.getLocation());
        }
    }
}
