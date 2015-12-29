/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author crisb
 */
public class Node 
{
   public Node p, left, right;
   public int color, key;
   
   public Node()
   {
       p = left = right = null;
       color = key = -1;
   }
}
