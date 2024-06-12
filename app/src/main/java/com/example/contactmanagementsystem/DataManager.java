package com.example.contactmanagementsystem;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class DataManager {

    private static final String FILENAME = "contacts.json";

    public static void saveContacts(Context context, BST contacts) {
        Gson gson = new Gson();
        String json = gson.toJson(contacts);
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.close();
        } catch (Exception e) {
            Log.e("DataManager", "Error saving contacts: " + e.getMessage());
        }
    }

    public static BST loadContacts(Context context) {
        BST contacts = new BST();
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            isr.close();
            fis.close();

            Gson gson = new Gson();
            contacts = gson.fromJson(sb.toString(), BST.class);
        } catch (Exception e) {
            Log.e("DataManager", "Error loading contacts: " + e.getMessage());
        }
        return contacts;
    }
}
