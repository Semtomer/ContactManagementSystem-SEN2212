package com.example.contactmanagementsystem;

public class BST
{
    private TreeNode root;
    private int size;

    // Constructor
    public BST()
    {
        this.root = null;
        this.size = 0;
    }

    // Getters
    public int GetSize() { return size; }
    public TreeNode GetRoot() { return root; }

    private TreeNode CreateNewNode(Contact contact)
    {
        return new TreeNode(contact);
    }

    public boolean AddContact(Contact contact)
    {
        if (root == null)
            root = CreateNewNode(contact);
        else
        {
            TreeNode parent = null;
            TreeNode current = root;

            while (current != null)
            {
                int compareResult = contact.GetName().compareToIgnoreCase(current.GetContact().GetName());

                if (compareResult > 0)
                {
                    parent = current;
                    current = current.GetRight();
                }
                else if (compareResult < 0)
                {
                    parent = current;
                    current = current.GetLeft();
                }
                else
                    return false; //Duplicate node not insterted
            }

            if (contact.GetName().compareToIgnoreCase(parent.GetContact().GetName()) > 0)
                parent.SetRight(CreateNewNode(contact));
            else
                parent.SetLeft(CreateNewNode(contact));
        }

        size++;
        return true; //Contact added
    }

    public boolean DeleteContact(Contact contact)
    {
        TreeNode parent = null;
        TreeNode current = root;

        while (current != null)
        {
            int compareResult = contact.GetName().compareToIgnoreCase(current.GetContact().GetName());

            if (compareResult > 0)
            {
                parent = current;
                current = current.GetRight();
            }
            else if (compareResult < 0)
            {
                parent = current;
                current = current.GetLeft();
            }
            else
                break; // Element is in the tree pointed at by current
        }

        if (current == null) //Element is not in the tree
            return false;

        //Case1 current has no left children
        if (current.GetLeft() == null)
        {
            // Connect the parent with the right child of the current node
            if (parent == null)
                root = current.GetRight();
            else
            {
                if (contact.GetName().compareToIgnoreCase(parent.GetContact().GetName()) > 0)
                    parent.SetRight(current.GetRight());
                else
                    parent.SetLeft(current.GetRight());
            }
        }
        else // Case 2: The current node has a left child
        {
            // Locate the rightmost node in the left subtree of the current node and also its parent
            TreeNode parentOfRightMost = current;
            TreeNode rightMost = current.GetLeft();

            while(rightMost.GetRight() != null)
            {
                parentOfRightMost = rightMost;
                rightMost = rightMost.GetRight(); // Keep going to the right
            }

            // Replace the element in current by the element in rightMost
            current.SetContact(rightMost.GetContact());

            // Eliminate rightmost node
            if (parentOfRightMost.GetRight() == rightMost)
                parentOfRightMost.SetRight(rightMost.GetLeft());
            else // Special case: parentOfRightMost == current
                parentOfRightMost.SetLeft(rightMost.GetLeft());
        }

        size--;
        return true; //Contact deleted
    }

    public void InOrder()
    {
        InOrder(root);
    }

    public void PostOrder()
    {
        PostOrder(root);
    }

    public void PreOrder()
    {
        PreOrder(root);
    }

    private void InOrder(TreeNode root)
    {
        if (root == null)
            return;

        InOrder(root.GetLeft());
        System.out.println(root.GetContact().GetName() + " ");
        InOrder(root.GetRight());
    }

    private void PostOrder(TreeNode root)
    {
        if (root == null)
            return;

        PostOrder(root.GetLeft());
        PostOrder(root.GetRight());
        System.out.println(root.GetContact().GetName() + " ");
    }

    private void PreOrder(TreeNode root)
    {
        if (root == null)
            return;

        System.out.println(root.GetContact().GetName() + " ");
        PreOrder(root.GetLeft());
        PreOrder(root.GetRight());
    }

    public boolean Search(Contact contact)
    {
        TreeNode current = root;

        while (current != null)
        {
            int compareResult = contact.GetName().compareToIgnoreCase(current.GetContact().GetName());

            if (compareResult < 0)
                current = current.GetLeft();
            else if (compareResult > 0)
                current = current.GetRight();
            else
                return true; // Element is found
        }

        return false;
    }

    // Search for position method uses In-Order traversal logic.
    public int SearchForPosition(Contact contact)
    {
        return SearchForPositionHelper(root, contact, new int[]{1});
    }

    private int SearchForPositionHelper(TreeNode node, Contact contact, int[] position)
    {
        if (node == null)
            return -1; // Contact not found

        int leftPosition = SearchForPositionHelper(node.GetLeft(), contact, position);

        if (leftPosition != -1)
            return leftPosition; // Contact found in the left subtree

        if (contact.GetName().equalsIgnoreCase(node.GetContact().GetName()))
            return position[0]; // Contact found at current node

        position[0]++; // Increment position

        int rightPosition = SearchForPositionHelper(node.GetRight(), contact, position);

        if (rightPosition != -1)
            return rightPosition; // Contact found in the right subtree

        return -1; // Contact not found
    }

    public Contact SearchForContact(int position)
    {
        return SearchForContactHelper(root, position, new int[]{1});
    }

    private Contact SearchForContactHelper(TreeNode node, int position, int[] currentPosition)
    {
        if (node == null)
            return null; // Contact not found

        Contact leftContact = SearchForContactHelper(node.GetLeft(), position, currentPosition);

        if (leftContact != null)
            return leftContact; // Contact found in the left subtree

        if (currentPosition[0] == position)
            return node.GetContact(); // Contact found at current node

        currentPosition[0]++; // Increment current position

        Contact rightContact = SearchForContactHelper(node.GetRight(), position, currentPosition);

        if (rightContact != null)
            return rightContact; // Contact found in the right subtree

        return null; // Contact not found
    }
}
