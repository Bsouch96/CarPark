/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import javax.swing.ImageIcon;

/**
 * This Lorry Class extends the Vehicle Class and contains variables relevant to the creation of a Lorry.<br>
 * <b>Author: </b>Ben Souch<br>
 * <b>Date: </b>25/03/2019<br>
 * @author Ben Souch
 * @version #1
 */
public class Lorry extends Vehicle
{
    private int weight;
    private final int numOfDays;
    
    /**
     * Basic initialisation of variables in this class.
     * @since #1.0
     */
    Lorry()
    {
        weight = 0;
        numOfDays = 0;
    }
    
    /**
     * Constructor used to initialise variables in Lorry and its parent, Vehicle, class.
     * @param weight initialises the weight of a created Lorry object. Value will range from 1 to 35, inclusive.
     * @param days initialises the number of days stayed with an integer value that will be greater than 1.
     * @param reg initialises the registration variable in the parent class vehicle. Format consists of 2 chars, 2 integers, 1 space and 3 chars.
     * @param lorry initialises the ImageIcon Image variable in parent class vehicle. Image will be of a lorry for use on JLabels in the GUI class.
     * @since #1.0
     */
    Lorry(int weight, int days, String reg, ImageIcon lorry)
    {
        super(reg, lorry);
        this.weight = weight;
        numOfDays = days;
        super.setAmountCharged(amountCharged());
    }

    /**
     * Retrieves the weight for a specified Lorry object.
     * @return returns the current weight of a Lorry object. Value will range from 1 to 35, inclusive.
     * @since #1.0
     */
    protected int getWeight()
    {
        return weight;
    }
    
    /**
     * Retrieves the number of days in which the specified Lorry object will stay.
     * @return returns the number of days the specified Lorry object will stay. This will be an integer value greater than 1.
     * @since #1.0
     */
    protected int getNumOfDays()
    {
        return numOfDays;
    }
    
    /**
     * Update overrides the abstract method in parent class Vehicle and is used to update a Lorry object's characteristics and the charge if required.
     * @param updReg updates the current registration to a new one by updating the registration variable in the Vehicle class. Anything not following the format 2 chars, 2 integers, 1 space and 3 chars
     * will be rejected.
     * @param value updates the current weight of the specified Lorry object to a new value. Anything not between 1 and 35, inclusive, will be rejected.
     * @since #1.0
     */
    @Override
    protected void update(String updReg, int value)
    {
        super.setRegistration(updReg);
        weight = value;
        amountCharged();
    }
    
    /**
     * This method determines the charge for a newly created Lorry object which is based upon on the length of stay and the weight of the lorry.
     * @return returns a double value of the amount charged for the specified Lorry object.
     */
    protected double amountCharged()
    {
        double charge = 0;
        
        if(weight < 20)
        {
            charge = 5.00 * numOfDays;
        }
        else
        {
            charge = 8.00 * numOfDays;
        }
        
        super.setAmountCharged(charge);
        return charge;
    }
    
    
    /**
     * The toString is an overridden abstract method from the parent class Vehicle and is used to return characteristics of the specified Car object.
     * It has HTML tags (HTML and br) to define it's layout on the JLabel. HTML to display on the JLabel (not compatible otherwise) and br to set a new line.
     * @return returns a String representation of all relevant characteristics of the specified Lorry object.
     * @since #1.0
     */
    @Override
    public String toString()
    {
        return "<HTML>The registration number is: " + super.getRegistration() + "<br>The charge for your day is: £" + String.format("%.2f", super.getAmountCharged()) + "<br>Your lorry weight is: " + weight + " Tonnes</HTML>";
    }

    /**
     * Overridden abstract method from parent class Vehicle, however, it is not used in this class but needs to be here to allow the program to compile.
     * @param updReg N/A
     * @param value N/A
     * @param checkDiscount N/A
     * @since #1.0
     */
    @Override
    protected void update(String updReg, int value, boolean checkDiscount)
    {
        
    }
}