package fr.kevin.contact.storage.utility;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

abstract public class DatabaseStorage<T> implements Storage<T> {
    private SQLiteOpenHelper helper;
    private String table;

    public DatabaseStorage(SQLiteOpenHelper helper, String table) {
        this.helper = helper;
        this.table = table;
    }

    protected abstract ContentValues objectToContentValues(int id, T object);

    protected abstract T cursorToObject(Cursor cursor);

    @Override
    public void insert(T object) {
        helper.getWritableDatabase().insert(table, null, objectToContentValues(-1, object));
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<>();

        Cursor cursor = helper.getReadableDatabase().query(table, null, null, null, null, null, null);
        while (cursor.moveToNext())
            list.add(cursorToObject(cursor));
        cursor.close();

        return list;
    }

    @Override
    public T find(int id) {
        T object = null;

        Cursor cursor = helper.getReadableDatabase().query(table, null, BaseColumns._ID + " = ?", new String[]{"" + id}, null, null, null);
        if (cursor.moveToNext()) object = cursorToObject(cursor);
        cursor.close();

        return object;
    }

    @Override
    public int size() {
        Cursor cursor = helper.getReadableDatabase().query(table, null, null, null, null, null, null);
        int size = cursor.getCount();
        cursor.close();
        return size;
    }

    @Override
    public void update(int id, T object) {
        helper.getWritableDatabase().update(table, objectToContentValues(id, object), BaseColumns._ID + " = ?", new String[]{"" + id});
    }

    @Override
    public void delete(int id) {
        helper.getWritableDatabase().delete(table, BaseColumns._ID + " = ?", new String[]{"" + id});
    }
}
