package com.example.contactmanagementsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Contact
{
    private String name, phoneNumber, emailAddress, dateOfBirth;
    private int age;
    Category category;

    // Constructor
    public Contact(String name, String phoneNumber, String emailAddress, String dateOfBirth, Category category)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.dateOfBirth = dateOfBirth;
        this.category = category;
        this.age = CalculateAge(dateOfBirth);
    }

    // Getters
    public String GetName() { return name; }
    public String GetPhoneNumber() { return phoneNumber; }
    public String GetEmailAddress() { return emailAddress; }
    public String GetDateOfBirth() { return dateOfBirth; }
    public Category GetCategory() { return category; }
    public int GetAge() { return age; }

    // Setters
    public void SetName(String name) { this.name = name; }
    public void SetPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void SetEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void SetDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
        this.age = CalculateAge(dateOfBirth);
    }
    public void SetCategory(Category category)
    { this.category = category; }

    // Calculating the age from the date of birth
    // Assuming the format of dateOfBirth is "dd.MM.yyyy"
    private int CalculateAge(String dateOfBirth)
    {
        if (!dateOfBirth.equalsIgnoreCase("") && dateOfBirth != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");


            try
            {
                Date birthDate = sdf.parse(dateOfBirth);
                Calendar birthDateCal = Calendar.getInstance();
                birthDateCal.setTime(birthDate);

                Calendar currentDateCal = Calendar.getInstance();

                int age = currentDateCal.get(Calendar.YEAR) - birthDateCal.get(Calendar.YEAR);

                // Adjust age if the current date has not passed the birth month and day yet
                if (currentDateCal.get(Calendar.MONTH) < birthDateCal.get(Calendar.MONTH) ||
                        (currentDateCal.get(Calendar.MONTH) == birthDateCal.get(Calendar.MONTH) &&
                                currentDateCal.get(Calendar.DAY_OF_MONTH) < birthDateCal.get(Calendar.DAY_OF_MONTH)))
                {
                    age--;
                }

                return age;
            }
            catch (ParseException e)
            {
                e.printStackTrace();
                return -1; // Return -1 if there's a parsing error
            }
        }
        else
            return -1;
    }

    @Override
    public String toString()
    {
        return "Name of the Contact " + GetName() + " with phone number " + GetPhoneNumber() +
                " with e-mail address " + GetEmailAddress() +
                " with date of birth " + GetDateOfBirth() + " and age " + GetAge() + ".";
    }
}


