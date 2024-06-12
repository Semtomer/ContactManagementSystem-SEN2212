package com.example.contactmanagementsystem;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private BST contacts;

    public ContactAdapter(Context context, BST contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.GetSize();
    }

    @Override
    public Object getItem(int position) {
        return contacts.SearchForContact(position + 1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_contact, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.contactNameTextView = convertView.findViewById(R.id.contactNameTextView);
            viewHolder.callIconImageView = convertView.findViewById(R.id.callIconImageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final Contact contact = (Contact) getItem(position);
        viewHolder.contactNameTextView.setText(contact.GetName());

        // Set click listener for the call icon
        viewHolder.callIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = contact.GetPhoneNumber();
                initiateCall(context, phoneNumber);
            }
        });

        // Set click listener for the contact item
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start ContactDetailActivity and pass contact details as extras
                Intent intent = new Intent(context, ContactDetailActivity.class);
                intent.putExtra("contact_name", contact.GetName());
                intent.putExtra("contact_phone", contact.GetPhoneNumber());
                intent.putExtra("contact_email", contact.GetEmailAddress());
                intent.putExtra("contact_birthdate", contact.GetDateOfBirth());
                intent.putExtra("contact_category", contact.GetCategory().getDisplayName());
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView contactNameTextView;
        ImageView callIconImageView;
    }

    private void initiateCall(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        context.startActivity(intent);
    }
}
