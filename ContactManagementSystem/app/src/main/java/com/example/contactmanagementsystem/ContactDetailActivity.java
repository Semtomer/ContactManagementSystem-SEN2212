package com.example.contactmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ContactDetailActivity extends AppCompatActivity {

    private EditText nameEditText, phoneEditText, emailEditText, birthdateEditText;
    private TextView ageTextView;
    private Spinner categorySpinner;
    private Button saveButton;
    private BST currentContacts;
    private Contact selectedContact;
    private ImageView deleteIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        nameEditText = findViewById(R.id.contactNameEditText);
        phoneEditText = findViewById(R.id.phoneNumberEditText);
        emailEditText = findViewById(R.id.emailEditText);
        birthdateEditText = findViewById(R.id.birthdateEditText);
        deleteIcon = findViewById(R.id.deleteContactIcon);
        ageTextView = findViewById(R.id.ageTextView);
        saveButton = findViewById(R.id.saveButton);
        categorySpinner = findViewById(R.id.categorySpinner);
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Category.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Retrieve the contact's details from the intent extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String contactName = extras.getString("contact_name");
            String contactPhone = extras.getString("contact_phone");
            String contactEmail = extras.getString("contact_email");
            String contactBirthdate = extras.getString("contact_birthdate");
            String contactCategory = extras.getString("contact_category");

            currentContacts = DataManager.loadContacts(getApplicationContext());

            for (int i = 1; i <= currentContacts.GetSize() ; i++)
            {
                if (currentContacts.SearchForContact(i).GetName().equalsIgnoreCase(contactName) &&
                        currentContacts.SearchForContact(i).GetPhoneNumber().equalsIgnoreCase(contactPhone) &&
                        currentContacts.SearchForContact(i).GetEmailAddress().equalsIgnoreCase(contactEmail) &&
                        currentContacts.SearchForContact(i).GetDateOfBirth().equalsIgnoreCase(contactBirthdate) &&
                        currentContacts.SearchForContact(i).GetCategory().getDisplayName().equalsIgnoreCase(contactCategory)
                )
                {
                    selectedContact = currentContacts.SearchForContact(i);
                }
            }

            // Display the contact's details in EditText fields
            nameEditText.setText(contactName);
            phoneEditText.setText(contactPhone);
            emailEditText.setText(contactEmail);
            birthdateEditText.setText(contactBirthdate);

            if (selectedContact.GetAge() == 0)
                ageTextView.setText("Unknown");
            else
                ageTextView.setText(String.valueOf(selectedContact.GetAge()));

            Category selectedCategory;
            if (contactCategory != null)
                selectedCategory = Category.fromString(contactCategory);
            else
                selectedCategory = Category.FAMILY;

            // Set selected category in spinner
            ArrayAdapter<Category> categoryAdapter = (ArrayAdapter<Category>) categorySpinner.getAdapter();
            if (categoryAdapter != null) {
                int position = categoryAdapter.getPosition(selectedCategory);
                categorySpinner.setSelection(position);
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the contact (You'll need to implement the saving logic here)

                Category selectedCategory = (Category) categorySpinner.getSelectedItem();

                if (selectedCategory == null)
                    selectedCategory = Category.FAMILY;

                selectedContact.SetName(nameEditText.getText().toString());
                selectedContact.SetPhoneNumber(phoneEditText.getText().toString());
                selectedContact.SetEmailAddress(emailEditText.getText().toString());
                selectedContact.SetDateOfBirth(birthdateEditText.getText().toString());
                selectedContact.SetCategory(selectedCategory);

                DataManager.saveContacts(getApplicationContext(), currentContacts);

                Intent intent = new Intent(ContactDetailActivity.this, MainActivity.class);
                startActivity(intent);
                // After saving, finish this activity and return to MainActivity
                finish();
            }
        });

        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                currentContacts.DeleteContact(selectedContact);
                DataManager.saveContacts(getApplicationContext(), currentContacts);

                Intent intent = new Intent(ContactDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
