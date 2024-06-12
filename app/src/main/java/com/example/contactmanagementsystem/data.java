package com.example.contactmanagementsystem;

public class data {

    // Create sample contacts
    public static void createFirstContacts(BST contacts)
    {
        // Create contacts
        Contact s1 = new Contact("Jane Smith", "0987654321", "jane.smith@example.com", "19.05.1989", Category.FAMILY);
        Contact s2 = new Contact("John Doe", "1234567890", "john.doe@example.com", "22.09.1995", Category.ACQUAINTANCE);
        Contact s3 = new Contact("Alice Johnson", "9876543210", "alice.johnson@example.com", "09.01.2000", Category.COLLEAGUE);
        Contact s4 = new Contact("Elif Yılmaz", "5321234567", "elif.yilmaz@hotmail.com", "15.03.1998", Category.FRIEND);
        Contact s5 = new Contact("Emre Kaya", "+5459876543", "emre.kaya@gmail.com", "20.11.1985", Category.ACQUAINTANCE);
        Contact s6 = new Contact("Ayşe Çelik", "5335551212", "ayse.celik@hotmail.com", "10.07.1988", Category.COLLEAGUE);
        Contact s7 = new Contact("Mehmet Demir", "5543692581", "mehmet.demir@gmail.com", "2.05.1975", Category.FAMILY);
        Contact s8 = new Contact("Zeynep Öztürk", "5337778888", "zeynep.ozturk@gmail.com", "30.01.1995", Category.FRIEND);

        // Create a binary search tree
        contacts = new BST();

        // Add contacts to the BST
        contacts.AddContact(s1);
        contacts.AddContact(s2);
        contacts.AddContact(s3);
        contacts.AddContact(s4);
        contacts.AddContact(s5);
        contacts.AddContact(s6);
        contacts.AddContact(s7);
        contacts.AddContact(s8);
    }
}
