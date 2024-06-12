package com.example.contactmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 4000; // Splash screen timeout duration in milliseconds
    public static BST currentContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Simulate loading data with a delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run()
            {
                currentContacts = DataManager.loadContacts(getApplicationContext());
                if (currentContacts == null) {
                    BST initialContacts = new BST();
                    data.createFirstContacts(initialContacts);
                    DataManager.saveContacts(getApplicationContext(), initialContacts);
                    currentContacts = initialContacts;
                }

                // Display contacts in the tree data structure (for demonstration purposes)
                displayContacts(currentContacts, 1);

                // After the timeout, start the main activity
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close this activity
            }
        }, SPLASH_TIMEOUT);
    }

    private void displayContacts(BST contacts, int order)
    {
        switch (order)
        {
            case 0: contacts.PreOrder();  break;
            case 1: contacts.InOrder();   break;
            case 2: contacts.PostOrder(); break;
        }
    }
}
