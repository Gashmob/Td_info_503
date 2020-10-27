package fr.kevin.contact.storage;

import android.content.Context;
import android.content.SharedPreferences;

import fr.kevin.contact.model.Contact;
import fr.kevin.contact.storage.utility.Storage;

public final class ContactStorage {
    private static final String PREF_NAME = "fr.kevin.contact.preferences";
    private static final String PREF_STORAGE = "storage";
    public static final int PREF_STORAGE_NONE = 0;
    public static final int PREF_STORAGE_FILE_JSON = 1;
    public static final int PREF_STORAGE_DATABASE = 2;
    private static final int PREF_STORAGE_DEFAULT = PREF_STORAGE_NONE;

    private static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static int getPreferencesStorage(Context context) {
        return getPreferences(context).getInt(PREF_STORAGE, PREF_STORAGE_DEFAULT);
    }

    public static void setPreferencesStorage(Context context, int prefStorage) {
        getPreferences(context).edit().putInt(PREF_STORAGE, prefStorage).apply();
    }

    public static Storage<Contact> get(Context context) {
        switch (getPreferencesStorage(context)) {
            case PREF_STORAGE_FILE_JSON:
                return ContactJsonFileStorage.get(context);

            case PREF_STORAGE_DATABASE:
                return ContactDatabaseStorage.get(context);
        }
        return ContactNoneStorage.get();
    }
}
