/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import javax.swing.ImageIcon;

/**
 * Test Class for Coach which tests toString() and getCharge() methods.
 * @author Ben Souch
 * @since #1.0
 */
public class CoachTest
{
    public static void main(String[] args)
    {
        ImageIcon coachImage = new ImageIcon("Images/Coach.jpg");
        
        Coach newCoach = new Coach(50, true, "RJ63 MTY", coachImage);
        
        System.out.println(String.format("%.2f", newCoach.getAmountCharged()));
        System.out.println(newCoach.toString());
    }
}