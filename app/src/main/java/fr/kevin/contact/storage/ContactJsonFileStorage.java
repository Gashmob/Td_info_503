package fr.kevin.contact.storage;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import fr.kevin.contact.model.Contact;
import fr.kevin.contact.storage.utility.JsonFileStorage;

public class ContactJsonFileStorage extends JsonFileStorage<Contact> {
    private static final String NAME = "contact";
    private static final String CONTACT_ID = "id";
    private static final String CONTACT_FIRST_NAME = "first_name";
    private static final String CONTACT_LAST_NAME = "last_name";
    private static final String CONTACT_PHONE = "phone";
    private static final String CONTACT_HOME = "home";
    private static final String CONTACT_MAIL = "mail";
    private static final String CONTACT_LOCATION = "location";

    public ContactJsonFileStorage(Context context, String name) {
        super(context, name);
    }

    @Override
    protected JSONObject objectToJsonObject(int id, Contact object) {
        JSONObject json = new JSONObject();

        try {
            json.put(CONTACT_ID, "" + id);
            json.put(CONTACT_FIRST_NAME, object.getFirstName());
            json.put(CONTACT_LAST_NAME, object.getLastName());
            json.put(CONTACT_PHONE, object.getPhone());
            json.put(CONTACT_HOME, object.getHome());
            json.put(CONTACT_MAIL, object.getMail());
            json.put(CONTACT_LOCATION, object.getLocation());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    @Override
    protected Contact jsonObjectToObject(JSONObject jsonObject) {
        try {
            return new Contact(jsonObject.getString(CONTACT_FIRST_NAME),
                    jsonObject.getString(CONTACT_LAST_NAME),
                    jsonObject.getString(CONTACT_PHONE),
                    jsonObject.getString(CONTACT_HOME),
                    jsonObject.getString(CONTACT_MAIL),
                    jsonObject.getString(CONTACT_LOCATION));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
