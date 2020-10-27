package fr.kevin.contact.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import fr.kevin.contact.model.Contact;
import fr.kevin.contact.storage.utility.DatabaseStorage;

public class ContactDatabaseStorage extends DatabaseStorage<Contact> {
    private static final String TABLE_NAME = "contact";
    private static final int NUM_ID = 0;
    private static final String NAME_FIRST_NAME = "first_name";
    private static final int NUM_FIRST_NAME = 1;
    private static final String NAME_LAST_NAME = "last_name";
    private static final int NUM_LAST_NAME = 2;
    private static final String NAME_PHONE = "phone";
    private static final int NUM_PHONE = 3;
    private static final String NAME_HOME = "home";
    private static final int NUM_HOME = 4;
    private static final String NAME_MAIL = "mail";
    private static final int NUM_MAIL = 5;
    private static final String NAME_LOCATION = "location";
    private static final int NUM_LOCATION = 6;

    private static ContactDatabaseStorage STORAGE;

    public static ContactDatabaseStorage get(Context context) {
        if (STORAGE == null) {
            STORAGE = new ContactDatabaseStorage(context);
        }
        return STORAGE;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "Contact.db";

        private static final String SQL_CREATE_CONTACT_ENTRIES = "CREATE TABLE " + TABLE_NAME + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY,"
                + NAME_FIRST_NAME + " TEXT," + NAME_LAST_NAME + " TEXT," + NAME_PHONE + " TEXT,"
                + NAME_HOME + " TEXT," + NAME_MAIL + " TEXT," + NAME_LOCATION + " TEXT)";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_CONTACT_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    private ContactDatabaseStorage(Context context) {
        super(new DatabaseHelper(context), TABLE_NAME);
    }

    @Override
    protected ContentValues objectToContentValues(int id, Contact object) {
        ContentValues values = new ContentValues();
        values.put(NAME_FIRST_NAME, object.getFirstName());
        values.put(NAME_LAST_NAME, object.getLastName());
        values.put(NAME_PHONE, object.getPhone());
        values.put(NAME_HOME, object.getHome());
        values.put(NAME_MAIL, object.getMail());
        values.put(NAME_LOCATION, object.getLocation());
        return null;
    }

    @Override
    protected Contact cursorToObject(Cursor cursor) {
        return new Contact(cursor.getInt(NUM_ID),
                cursor.getString(NUM_FIRST_NAME),
                cursor.getString(NUM_LAST_NAME),
                cursor.getString(NUM_PHONE),
                cursor.getString(NUM_HOME),
                cursor.getString(NUM_MAIL),
                cursor.getString(NUM_LOCATION));
    }
}
