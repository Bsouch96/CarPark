/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This is the Driver class with the main method.<br>
 * <b>Author: </b>Ben Souch<br>
 * <b>Date: </b>25/03/2019<br>
 * @author Ben Souch
 * @version #1
 */
public class CarPark
{

    /**
     * The main method here creates a JFrame object, defines it, creates an object of the GUI class and adds it to the JFrame.
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        JFrame carParkFrame = new JFrame();
        CarParkGUI gui = new CarParkGUI();
        
        Dimension frameSize = new Dimension(1200, 800);
        carParkFrame.setPreferredSize(frameSize);
        carParkFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        carParkFrame.setBackground(Color.WHITE);
        carParkFrame.pack();
        carParkFrame.setResizable(false);
        carParkFrame.setVisible(true);
        carParkFrame.setLocationRelativeTo(null);
        
        carParkFrame.add(gui, BorderLayout.CENTER);
    }
    
}