/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import javax.swing.ImageIcon;

/**
 * This Car Class extends the Vehicle Class and contains variables relevant to the creation of a Car.<br>
 * <b>Author: </b>Ben Souch<br>
 * <b>Date: </b>25/03/2019<br>
 * @author Ben Souch
 * @version #1.0
 */
public class Car extends Vehicle
{
    private int length;
    private boolean disabled;
    private final int numOfHours;
    
    /**
     * Basic initialisation of variables in this class.
     */
    Car()
    {
        length = 0;
        disabled = false;
        numOfHours = 0;
    }

    /**
     * Constructor used to initialise variables in Car and its parent, Vehicle, class.
     * @param len initialises the length variable with an integer between 1 and 12 inclusive.
     * @param disable initialises the disabled variable. True if user has a disabled badge, false if not.
     * @param reg initialises the registration variable in the parent class vehicle. Format consists of 2 chars, 2 integers, 1 space and 3 chars.
     * @param car initialises the ImageIcon Image variable in parent class vehicle. Image will be of a car for use on JLabels in the GUI class.
     * @param hours initialises the number of hours stayed variable with an integer between 1 and 24 inclusive.
     * @since #1.0
     */
    Car(int len, boolean disable, String reg, ImageIcon car, int hours)
    {
        super(reg, car);
        length = len;
        disabled = disable;
        numOfHours = hours;
        super.setAmountCharged(amountCharged());
    }
    
    /**
     * This method is used to get the length of the Car object specified.
     * @return returns and integer value of the length of the specified Car. The value will range from 1 to 12, inclusive.
     * @since #1.0
     */
    protected int getLength()
    {
        return length;
    }
    
    /**
     * This method is used to get the disability status of a specified Car object.
     * @return returns a boolean value of the disability status. True if a user has a disability badge, false if not.
     * @since #1.0
     */
    protected boolean isDisabled()
    {
        return disabled;
    }
    
    /**
     * Update overrides the abstract method in parent class Vehicle and is used to update a Car object's characteristics and the charge if required.
     * @param updReg updates the current registration to a new one by updating the registration variable in the Vehicle class. Anything not following the format 2 chars, 2 integers, 1 space and 3 chars
     * will be rejected.
     * @param value updates the current length of the car to a new value. Anything not between 1 and 12 meters, inclusive, will be rejected as a value.
     * @param checkDiscount updates the current boolean value based upon whether or not the user is a disabled badge holder, true is so, false if not.
     * @since #1.0
     */
    @Override
    protected void update(String updReg, int value, boolean checkDiscount)
    {
        super.setRegistration(updReg);
        length = value;
        disabled = checkDiscount;
        amountCharged();
    }
    
    /**
     * This method is used to get the current value of the number of hours the specified Car object will stay.
     * @return returns the number of hours the specified Car object will stay. This will be an integer value between 1 and 24, inclusive.
     */
    protected int getHours()
    {
        return numOfHours;
    }
    
    /**
     * This method determines the charge for a newly created Car object which is based upon on the length of stay, the length of the car or is free for disabled badge holders.
     * Also sets and updates the parent class Vehicle's amount charged variable.
     * @return returns a double value of the amount charged for the specified Car object. Free, or 0.00 charge, if the user is a disabled badge holder.
     * @since #1.0
     */
    protected double amountCharged()
    {
        double charge = 0;
        
        if(disabled)
        {
            charge = 0;
        }
        else
        {
            if(length > 6)
            {
                charge = 1.50 * numOfHours;
            }
            else
            {
                charge = 1.00 * numOfHours;
            }
        }
        
        super.setAmountCharged(charge);
        return charge;
    }

    /**
     * The toString is an overridden abstract method from the parent class Vehicle and is used to return characteristics of the specified Car object.
     * It has HTML tags (HTML and br) to define it's layout on the JLabel. HTML to display on the JLabel (not compatible otherwise) and br to set a new line.
     * @return returns a String representation of all relevant characteristics of the specified Car object.
     * @since #1.0
     */
    @Override
    public String toString() 
    {
        return "<HTML>The registration number is: " + super.getRegistration() + "<br>The charge for your stay is: £" + String.format("%.2f", super.getAmountCharged()) + "<br>Your car length is: " + length + " Metres" + "<br>Disabled badge holder: " + disabled + "</HTML>";
    }

    /**
     * Overridden abstract method from parent class Vehicle, however, it is not used in this class but needs to be here to allow the program to compile.
     * @param updReg N/A
     * @param value N/A
     * @since #1.0
     */
    @Override
    protected void update(String updReg, int value)
    {
        
    }
}