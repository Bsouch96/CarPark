/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import javax.swing.ImageIcon;

/**
 * Test Class for Lorry which tests toString() and getCharge() methods.
 * @author Ben Souch
 * @since #1.0
 */
public class LorryTest
{
    public static void main(String[] args)
    {
        ImageIcon lorryImage = new ImageIcon();
        
        Lorry newLorry = new Lorry(30, 5, "RJ63 MTY", lorryImage);
        
        System.out.println(String.format("%.2f", newLorry.getAmountCharged()));
        System.out.println(newLorry.toString());
    }
}
