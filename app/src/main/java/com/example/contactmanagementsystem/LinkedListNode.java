package com.example.contactmanagementsystem;

public class LinkedListNode
{
    private Contact contact;
    private LinkedListNode next;

    public LinkedListNode()
    {
        contact = null;
        next = null;
    }

    public LinkedListNode(Contact contact)
    {
        this.contact = contact;
    }

    public LinkedListNode(Contact contact, LinkedListNode next)
    {
        this.contact = contact;
        this.next = next;
    }

    // Getters
    public Contact GetContact() { return contact; }
    public LinkedListNode GetNext() { return next; }

    // Setters
    public void SetContact(Contact contact) { this.contact = contact; }
    public void SetNext(LinkedListNode next) { this.next = next; }
}

