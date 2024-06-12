package com.example.contactmanagementsystem;

public class TreeNode
{
    private Contact contact;
    private TreeNode left, right;

    // Constructor
    public TreeNode(Contact contact)
    {
        this.contact = contact;
        this.left = null;
        this.right = null;
    }

    //Getters
    public Contact GetContact() { return contact; }
    public TreeNode GetLeft() { return left; }
    public TreeNode GetRight() { return right; }

    //Setters
    public void SetContact(Contact contact) { this.contact = contact; }
    public void SetLeft(TreeNode left) { this.left = left; }
    public void SetRight(TreeNode right) { this.right = right; }
}

