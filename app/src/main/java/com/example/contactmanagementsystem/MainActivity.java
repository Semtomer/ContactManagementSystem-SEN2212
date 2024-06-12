package com.example.contactmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ListView contactListView;
    private FloatingActionButton addContactButton;
    private Button categoriesButton;
    private SearchView searchView;
    private ContactAdapter contactAdapter;

    public static BST currentContacts;
    public static BST filteredContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactListView = findViewById(R.id.contactListView);
        addContactButton = findViewById(R.id.addContactButton);
        categoriesButton = findViewById(R.id.categoryButton);
        searchView = findViewById(R.id.searchView);

        currentContacts = DataManager.loadContacts(getApplicationContext());
        if (currentContacts == null) {
            currentContacts = new BST();
            data.createFirstContacts(currentContacts);
            DataManager.saveContacts(getApplicationContext(), currentContacts);
        }

        // Initialize adapter and set it to the ListView
        contactAdapter = new ContactAdapter(this, currentContacts);
        contactListView.setAdapter(contactAdapter);

        // Set click listener for adding a new contact
        addContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewContactActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for navigating to category activity
        categoriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for contact list items to navigate to contact detail activity
        contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact selectedContact = currentContacts.SearchForContact(position + 1);
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                intent.putExtra("contact_name", selectedContact.GetName());
                intent.putExtra("contact_phone", selectedContact.GetPhoneNumber());
                intent.putExtra("contact_email", selectedContact.GetEmailAddress());
                intent.putExtra("contact_birthdate", selectedContact.GetDateOfBirth());
                intent.putExtra("contact_category", selectedContact.GetCategory().getDisplayName());

                //intent.putExtra("")
                startActivity(intent);
            }
        });

        // Set search functionality
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterContacts(newText);
                return true;
            }
        });
    }

    private void filterContacts(String query) {
        filteredContacts = new BST();
        filterContactsHelper(currentContacts.GetRoot(), query.toLowerCase());
        contactAdapter = new ContactAdapter(this, filteredContacts);
        contactListView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();
    }

    private void filterContactsHelper(TreeNode node, String query) {
        if (node == null) return;

        filterContactsHelper(node.GetLeft(), query);

        Contact contact = node.GetContact();
        if (contact.GetName().toLowerCase().startsWith(query) ||
                contact.GetPhoneNumber().toLowerCase().startsWith(query) ||
                contact.GetEmailAddress().toLowerCase().startsWith(query)) {
            filteredContacts.AddContact(contact);
        }

        filterContactsHelper(node.GetRight(), query);
    }
}

