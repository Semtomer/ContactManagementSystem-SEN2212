package com.example.contactmanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    private TextView messageTextView;

    OLL acquaintance = new OLL();
    OLL colleague = new OLL();
    OLL family = new OLL();
    OLL friend = new OLL();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // Initialize message TextView
        messageTextView = findViewById(R.id.messageTextView);

        // Find TextViews for categories
        TextView familyTextView = findViewById(R.id.familyTextView);
        TextView acquaintanceTextView = findViewById(R.id.acquaintanceTextView);
        TextView colleagueTextView = findViewById(R.id.colleagueTextView);
        TextView friendTextView = findViewById(R.id.friendTextView);

        BST categoryContactsBST = DataManager.loadContacts(getApplicationContext());
        OLL categoryContactsOLL = new OLL();

        for (int i = 1; i <= categoryContactsBST.GetSize() ; i++)
            categoryContactsOLL.Insert(categoryContactsBST.SearchForContact(i));

        int testSize = categoryContactsOLL.GetSize();

        for (int i = 0; i < categoryContactsOLL.GetSize(); i++)
        {
            if (categoryContactsOLL.SearchForContact(i).category == Category.ACQUAINTANCE)
                acquaintance.Insert(categoryContactsOLL.SearchForContact(i));
            else if (categoryContactsOLL.SearchForContact(i).category == Category.COLLEAGUE)
                colleague.Insert(categoryContactsOLL.SearchForContact(i));
            else if (categoryContactsOLL.SearchForContact(i).category == Category.FAMILY)
                family.Insert(categoryContactsOLL.SearchForContact(i));
            else if (categoryContactsOLL.SearchForContact(i).category == Category.FRIEND)
                friend.Insert(categoryContactsOLL.SearchForContact(i));
        }

        // Set click listeners for each category TextView
        familyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String familyCategoriesString = "";
                for (int i = 0; i < family.GetSize(); i++)
                {
                    if (i == family.GetSize() - 1)
                        familyCategoriesString += family.SearchForContact(i).GetName();
                    else
                        familyCategoriesString += family.SearchForContact(i).GetName() + "\n";

                    showMessage(familyCategoriesString);
                }

                if (family.GetSize() == 0)
                    showMessage("");
            }
        });

        acquaintanceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String acquaintanceCategoriesString = "";
                for (int i = 0; i < acquaintance.GetSize(); i++)
                {
                    if (i == acquaintance.GetSize() - 1)
                        acquaintanceCategoriesString += acquaintance.SearchForContact(i).GetName();
                    else
                        acquaintanceCategoriesString += acquaintance.SearchForContact(i).GetName() + "\n";

                    showMessage(acquaintanceCategoriesString);
                }

                if (acquaintance.GetSize() == 0)
                    showMessage("");
            }
        });

        colleagueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String colleagueCategoriesString = "";
                for (int i = 0; i < colleague.GetSize(); i++)
                {
                    if (i == colleague.GetSize() - 1)
                        colleagueCategoriesString += colleague.SearchForContact(i).GetName();
                    else
                        colleagueCategoriesString += colleague.SearchForContact(i).GetName() + "\n";

                    showMessage(colleagueCategoriesString);
                }

                if (colleague.GetSize() == 0)
                    showMessage("");
            }
        });

        friendTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String friendCategoriesString = "";
                for (int i = 0; i < friend.GetSize(); i++)
                {
                    if (i == friend.GetSize() - 1)
                        friendCategoriesString += friend.SearchForContact(i).GetName();
                    else
                        friendCategoriesString += friend.SearchForContact(i).GetName() + "\n";

                    showMessage(friendCategoriesString);
                }

                if (friend.GetSize() == 0)
                    showMessage("");
            }
        });
    }

    private void showMessage(String message) {
        messageTextView.setText(message);
    }
}

