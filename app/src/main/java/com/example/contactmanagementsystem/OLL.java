package com.example.contactmanagementsystem;

public class OLL
{
    private LinkedListNode first;
    private LinkedListNode last;

    private int size;

    public OLL()
    {
        first = null;
        last = null;
        size = 0;
    }

    public boolean IsEmpty()
    {
        return (first == null);
    }

    public int GetSize() { return size; }

    public void Insert(Contact contact)
    {
        LinkedListNode newLinkedListNode = new LinkedListNode(contact);

        if (IsEmpty())
            first = last = newLinkedListNode;
        else
        {
            LinkedListNode current = first;
            LinkedListNode previous = null;

            while (current != null && contact.GetName().compareToIgnoreCase(current.GetContact().GetName()) > 0)
            {
                previous = current;
                current = current.GetNext();
            }

            if (previous == null)
            {
                newLinkedListNode.SetNext(first);
                first = newLinkedListNode;
            }
            else
            {
                newLinkedListNode.SetNext(current);
                previous.SetNext(newLinkedListNode);
            }

            if (current == null)
                last = newLinkedListNode;
        }

        size++;
    }

    public boolean Remove(Contact contact)
    {
        LinkedListNode current = first;
        LinkedListNode previous = null;

        while (current != null && !current.GetContact().GetName().equals(contact.GetName()))
        {
            previous = current;
            current = current.GetNext();
        }

        if (current == null)
        {
            System.out.println(contact.GetName() + " is not in the list!");
            return false;
        }

        if (previous == null)
        {
            first = current.GetNext();
        }
        else
            previous.SetNext(current.GetNext());

        if (current == last)
            last = previous;

        size--;
        return true;
    }

    public boolean Search(Contact contact)
    {
        LinkedListNode current = first;

        while (current != null)
        {
            if (current.GetContact().GetName().equals(contact.GetName()))
                return true;

            current = current.GetNext();
        }

        return false;
    }

    public int SearchForPosition(Contact contact)
    {
        int position = -1;
        LinkedListNode current = first;

        while (current != null)
        {
            position++;

            if (current.GetContact().GetName().equals(contact.GetName()))
                return position;

            current = current.GetNext();
        }

        // If the Contact is not found, return -1
        return -1;
    }

    public Contact SearchForContact(int index)
    {
        int currentPosition = 0;
        LinkedListNode current = first;

        while (current != null)
        {
            if (currentPosition == index)
                return current.GetContact();

            currentPosition++;
            current = current.GetNext();
        }

        // If the index is not found, return null
        return null;
    }

    public int Size()
    {
        int count = 0;
        LinkedListNode current = first;

        while (current != null)
        {
            count++;
            current = current.GetNext();
        }

        return count;
    }

    public void OutputList()
    {
        LinkedListNode current = first;

        while (current != null)
        {
            System.out.println(current.GetContact());
            current = current.GetNext();
        }
    }
}
