package fr.kevin.contact.storage.utility;

import android.database.sqlite.SQLiteOpenHelper;

abstract public class DatabaseStorage<T> implements Storage<T> {
    private SQLiteOpenHelper helper;
    private String table;

    public DatabaseStorage(SQLiteOpenHelper helper, String table) {
        this.helper = helper;
        this.table = table;
    }
}
