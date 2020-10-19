package fr.kevin.contact.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import fr.kevin.contact.R;
import fr.kevin.contact.model.Contact;
import fr.kevin.contact.storage.ContactStorage;

public class DeleteDialogFragment extends DialogFragment {

    private final Updatable updatable;
    private final int id;

    public DeleteDialogFragment(Updatable updatable, int id) {
        this.updatable = updatable;
        this.id = id;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Contact contact = ContactStorage.get(getContext()).find(id);
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_delete_title)
                .setMessage(getString(R.string.dialog_delete_message, contact.getFirstName(), contact.getLastName()))
                .setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactStorage.get(getContext()).delete(DeleteDialogFragment.this.id);
                        updatable.update();
                    }
                })
                .setNegativeButton(R.string.dialog_negative_button, null)
                .create();
    }
}
