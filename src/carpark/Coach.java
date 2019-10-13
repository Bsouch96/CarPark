/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import javax.swing.ImageIcon;

/**
 * This Coach Class extends the Vehicle Class and contains variables relevant to the creation of a Coach.<br>
 * <b>Author: </b>Ben Souch<br>
 * <b>Date: </b>25/03/2019<br>
 * @author Ben Souch
 * @version #1
 */
public class Coach extends Vehicle
{
    private int numOfPassengers;
    private boolean touristOperator;
    
    /**
     * Basic initialisation of variables in this class.
     * @since #1.0
     */
    Coach()
    {
        numOfPassengers = 0;
        touristOperator = false;
    }
    
    
    /**
     * Constructor used to initialise variables in Coach and its parent, Vehicle, class.
     * @param passengers initialises the number of passengers aboard a created Coach object. Value from 0 to 57, inclusive.
     * @param operator initialises the boolean value tourist operator. True if the created Coach is a tourist operator or false if not.
     * @param reg initialises the registration variable in the parent class vehicle. Format consists of 2 chars, 2 integers, 1 space and 3 chars.
     * @param coach initialises the ImageIcon Image variable in parent class vehicle. Image will be of a coach for use on JLabels in the GUI class.
     * @since #1.0
     */
    Coach(int passengers, boolean operator, String reg, ImageIcon coach)
    {
        super(reg, coach);
        numOfPassengers = passengers;
        touristOperator = operator;
        super.setAmountCharged(amountCharged());
    }
    
    /**
     * Retrieves the number of passengers for a specified Coach object.
     * @return returns the number of passengers on a Coach object. Value will be between 0 and 57, inclusive.
     * @since #1.0
     */
    protected int getnumOfPassengers()
    {
        return numOfPassengers;
    }

    /**
     * Retrieves the tourist operator status for a specified Coach object.
     * @return returns the boolean value for tourist operator. Value will be true if the Coach object is a tourist operator and false if not.
     * @since #1.0
     */
    protected boolean getTouristOperator()
    {
        return touristOperator;
    }
    
    /**
     * Update overrides the abstract method in parent class Vehicle and is used to update a Coach object's characteristics and the charge if required.
     * @param updReg updates the current registration to a new one by updating the registration variable in the Vehicle class. Anything not following the format 2 chars, 2 integers, 1 space and 3 chars
     * will be rejected.
     * @param value updates the current number of passengers to a new value. Anything not between 0 and 57, inclusive, will be rejected.
     * @param checkDiscount updates the current boolean value based upon whether or not the user is a tourist operator, true is so, false if not.
     * @since #1.0
     */
    @Override
    protected void update(String updReg, int value, boolean checkDiscount)
    {
        super.setRegistration(updReg);
        numOfPassengers = value;
        touristOperator = checkDiscount;
        amountCharged();
    }
    
    /**
     * This method determines the charge for newly created Coach object based upon number of passengers and their tourist operator status, additionally,
     * all multiplication here adds a 10% discount based upon their "touristOperator" status.
     * @return returns a double value of the amount charged for the specified Coach object.
     */
    protected double amountCharged()
    {
        double charge = 0;
        
        if(numOfPassengers <= 20 && touristOperator)
        {
            charge = 4.50 * 0.9;
        }
        else if(numOfPassengers <= 20 && !touristOperator)
        {
            charge = 4.50;
        }
        else if(numOfPassengers > 20 && touristOperator)
        {
            charge = 6.00 * 0.9;
        }
        else if(numOfPassengers > 20 && !touristOperator)
        {
            charge = 6.00;
        }

        super.setAmountCharged(charge);
        return charge;
    }
    
    //Returns all relevant data to this Coach as a string.
    /**
     * The toString is an overridden abstract method from the parent class Vehicle and is used to return characteristics of the specified Coach object.
     * It has HTML tags (HTML and br) to define it's layout on the JLabel. HTML to display on the JLabel (not compatible otherwise) and br to set a new line.
     * @return returns all relevant characteristics of the specified Coach object.
     * @since #1.0
     */
    @Override
    public String toString()
    {
        return "<HTML>The registration number is: " + super.getRegistration() + "<br>The charge per week is: £" + String.format("%.2f", super.getAmountCharged()) + "<br>Number of passengers are: " + numOfPassengers + "<br>Tourist Operator: " + touristOperator + "</HTML>";
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