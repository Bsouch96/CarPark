/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 * This is the abstract parent class of Car, Lorry and Coach which holds variables relevant to each one.<br>
 * <b>Author: </b>Ben Souch<br>
 * <b>Date: </b>25/03/2019<br>
 * @author Ben Souch
 * @version #1
 */
public abstract class Vehicle implements Serializable
{
    private String registration;
    private ImageIcon image;
    private double amountCharged;
    
    /**
     * Basic initialisation of variables in this class.
     */
    Vehicle()
    {
        registration = "";
        image = null;
        amountCharged = 0;
    }

    /**
     * Constructor used to initialise variables based upon what is sent from the Car, Coach and Lorry classes.
     * @param reg initialises the registration variable in the parent class vehicle. Format consists of 2 chars, 2 integers, 1 space and 3 chars. Received from Car, Lorry or Coach classes.
     * @param img initialises the image of the created vehicle. This variable is receives it's data from the Car, Coach or Lory class.
     * @since #1.0
     */
    Vehicle(String reg, ImageIcon img)
    {
        registration = reg;
        image = img;
    }
    
    /**
     * This method is used to get the image of the specified Vehicle object. This will be used when the user is performing mouse clicks.
     * @return returns the ImageIcon type image when a user adds a vehicle to the GUI and places it on the relevant JLabel.
     * @since #1.0
     */
    protected ImageIcon getImage()
    {
        return image;
    }
    
     /**
      * This method is used to retrieve the registration of a specified Vehicle object and is part of the overridden toString methods in each of the Car, Coach and Lorry classes.
      * @return returns a String value of the registration.
      * @since #1.0
      */
    protected String getRegistration()
    {
        return registration;
    }
    
    /**
     * This method is used to retrieve the amount charged for a specified Vehicle object.
     * @return returns a double value of the amount charged.
     * @since #1.0
     */
    protected double getAmountCharged()
    {
        return amountCharged;
    }
    
    /**
     * Method used to set the amount charged for a specified vehicle.
     * @param charge contains the charge for a specified vehicle.
     * @since #1.0
     */
    protected void setAmountCharged(double charge)
    {
        amountCharged = charge;
    }
    
    /**
     * Method used to set the registration for a specified vehicle.
     * @param reg contains the registration for a specified vehicle.
     * @since #1.0
     */
    protected void setRegistration(String reg)
    {
        registration = reg;
    }
    
    /**
     * Method used to set the image of a specified vehicle.
     * @param img contains the relevant image for a specified vehicle.
     * @since #1.0
     */
    protected void setImage(ImageIcon img)
    {
        image = img;
    }
    
    /**
     * This abstract method overrides the original Java toString method and is used by other classes, Car, Coach and Lorry, to retrieve a string representation of a specified vehicle object.
     * @see #toString
     * @since #1.0
     */
    @Override
    public abstract String toString();
    
    /**
     * This abstract method is overridden and used solely by the Lorry class to update it's registration, weight and, potentially, charge if required.
     * @param updReg updates the current registration to a new one by updating the registration variable in the Vehicle class. Anything not following the format 2 chars, 2 integers, 1 space and 3 chars
     * will be rejected. The updReg String value comes from the overridden update method in Lorry.
     * @param value updates the current weight of the specified Lorry object to a new value. Anything not between 1 and 35, inclusive, will be rejected. The updated value
     * comes from the overridden update method in Lorry.
     * @see #update(java.lang.String, int) 
     * @since #1.0
     */
    protected abstract void update(String updReg, int value);
    
    /**
     * This abstract method is overridden and used solely by the Car and Coach classes to update it's registration, length of car (car) or number of passengers (Coach)
     * and, potentially, charge if required.
     * @param updReg updates the current registration to a new one by updating the registration variable in the Vehicle class. Anything not following the format 2 chars, 2 integers, 1 space and 3 chars
     * will be rejected. The updReg String value comes from the overridden update method in either Car or Coach.
     * @param value updates either the current length of the specified Car object or number of passengers of a specified Coach object to a new value.
     * Any value not between 1 and 12, or 0 and 57 inclusive, respectively, will be rejected. The updated value comes from the overridden update method in either Car or Coach.
     * @param checkDiscount updates the current boolean value based upon whether or not the user is a disabled badge holder (Car) or a tourist operator (Coach), relevant to the
     * appropriate class. True if so, false if not. The updated value comes from the overridden update method in either Car or Coach.
     * @see #update(java.lang.String, int, boolean)
     * @since #1.0
     */
    protected abstract void update(String updReg, int value, boolean checkDiscount);
}
