package com.example.contactmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddNewContactActivity extends AppCompatActivity
{
    private EditText nameEditText, phoneEditText, emailEditText, birthdateEditText;
    private Spinner categorySpinner;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        nameEditText = findViewById(R.id.newNameEditText);
        phoneEditText = findViewById(R.id.newPhoneNumberEditText);
        emailEditText = findViewById(R.id.newEmailEditText);
        birthdateEditText = findViewById(R.id.newBirthdateEditText);

        saveButton = findViewById(R.id.saveButton);
        categorySpinner = findViewById(R.id.newCategorySpinner);
        ArrayAdapter<Category> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Category.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the contact (You'll need to implement the saving logic here)

                Category selectedCategory = (Category) categorySpinner.getSelectedItem();

                if (selectedCategory == null)
                    selectedCategory = Category.FAMILY;

                Contact newContact = new Contact(nameEditText.getText().toString(),
                                                 phoneEditText.getText().toString(),
                                                 emailEditText.getText().toString(),
                                                 birthdateEditText.getText().toString(),
                                                 selectedCategory);
                SplashActivity.currentContacts.AddContact(newContact);
                DataManager.saveContacts(getApplicationContext(), SplashActivity.currentContacts);

                Intent intent = new Intent(AddNewContactActivity.this, MainActivity.class);
                startActivity(intent);
                // After saving, finish this activity and return to MainActivity
                finish();
            }
        });

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Retrieve the selected category
                Category selectedCategory = (Category) parentView.getItemAtPosition(position);
                // Do something with the selected category
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case where nothing is selected
            }
        });
    }
}
