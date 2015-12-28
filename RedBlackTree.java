/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 * A complex data structure: red black tree
 * 0 for black and 1 for red
 * @author Cristian Bodnar
 */
public class RedBlackTree 
{
    public Node root, nil;
    
    private final int RED = 1;
    private final int BLACK = 0;
    
    public RedBlackTree()
    {
        root = nil = null;
    }
    
    private void insertFixup(Node z)
    {
        //While the red black tree property is broken
        while(z.p.color == 1)
        {
            if(z.p == z.p.p.left)
            {
                //y is the uncle
                Node y = z.p.p.right;
                
                //Uncle is red - case 1
                if(y.color == RED)
                {
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                }
                else //Uncle is black
                {
                    if(z == z.p.right)
                    {
                        z = z.p;
                        leftRotate(z);
                    }
                    z.p.color = BLACK;
                    z.p.p.color = RED;
                    rightRotate(z.p.p);
                }
            }
            else
            {
                //y is the uncle
                Node y = z.p.p.left;
                
                //Uncle is red - case 1
                if(y.color == RED)
                {
                    z.p.color = BLACK;
                    y.color = BLACK;
                    z.p.p.color = RED;
                    z = z.p.p;
                }
                else //Uncle is black
                {
                    if(z == z.p.left)
                    {
                        z = z.p;
                        rightRotate(z);
                    }
                    z.p.color = BLACK;
                    z.p.p.color = RED;
                    leftRotate(z.p.p);
                }
            }
        }
    }
    
    public void insert(int key)
    {
        //Create a new node to which the key is assigned
        //The code is initially red (1)
        Node z = new Node();
        z.color = 1;
        z.key = key;
        
        //Insert the node
        Node y = this.nil;
        Node x = this.root;
        while(x != this.nil)
        {
            y = x;
            if(z.key < x.key)
                x = x.left;
            else
                x = x.right;
        }
        
        //y is the parent of z
        z.p = y;
        
        //Check where to add z
        if(z.p == this.nil)
            this.root = z;
        else if(z.key < z.p.key)
            z.p.left = z;
        else
            z.p.right = z;
        
        //Make the childs of z nil
        z.left = this.nil;
        z.right = this.nil;
        
        //Fix the possible broken properties of the red black tree
        insertFixup(z);
    }
    
    public void delete(int key)
    {
        
    }
    
    //Execute a left rotation in the BlackTree where node x is the parent
    private void leftRotate(Node x)
    {
        //Define y
        Node y = x.right;
        
        //y left child goes to x and it will be replaced by x
        x.right = y.left;
        
        //Check if x is the root
        if(x.p == nil)
            this.root = y;
        //If x is the left child
        else if(x == x.p.left)
            x.p.left = y;
        //else x is the right child
        else 
            x.p.right = y;
        
        //New link between x and y
        y.left = x;
        x.p = y;
    }
    
    private void rightRotate(Node x)
    {
        //Define y
        Node y = x.left;
        
        //y right child becomes x left child
        x.left = y.right;
        
        //Check if x is root
        if(x.p == nil)
            this.root = y;
        //x is left child of its parent
        else if(x == x.p.left)
            x.p.left = y;
        //x is right child of its parent
        else
            x.p.right = y;
        
        //New link between x and y
        y.right = x;
        x.p = y;
    }
}
