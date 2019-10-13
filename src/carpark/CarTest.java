/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import javax.swing.ImageIcon;

/**
 * Test Class for Car which tests toString() and getCharge() methods.
 * @author Ben Souch
 * @since #1.0
 */
public class CarTest
{
    public static void main(String[] args)
    {
        ImageIcon carImage = new ImageIcon("Images/Car.jpg");
        
        Car firstCar = new Car(5, false, "RJ63 MTY", carImage, 6);
        System.out.println("Expected charge for first car: £6.00\n" + "Actual charge: £" + String.format("%.2f", firstCar.getAmountCharged()));
        System.out.println(firstCar.toString());
        
        Car secondCar = new Car(5, true, "RJ60 MTU", carImage, 3);
        System.out.println("Expected charge for 2nd car: £0.00\n" + "Actual charge: £" + String.format("%.2f", secondCar.getAmountCharged()));
        System.out.println(secondCar.toString());
    }
}