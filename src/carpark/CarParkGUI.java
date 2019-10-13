/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carpark;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.BevelBorder;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 *This is the GUI class that will provide all the user interaction.<br>
 * <b>Author: </b>Ben Souch<br>
 * <b>Date: </b>25/03/2019<br>
 * @author Ben Souch
 * @version #1
 */
public class CarParkGUI extends JPanel implements ActionListener, MouseListener
{
    private final JPanel northPanel, centerPanel, westPanel, eastPanel, southWestPanel, southEastPanel;
    private ImageIcon carParkLogo, car, coach, lorry;
    private final JLabel northPanelLabel, southWestPanelLabel, vehicleDetailsLabel, updateDetails, southEastPanelLabel;
    private final JLabel updLabelOne, updLabelTwo;
    private final String[] vehicleList, utilityList;
    private final JComboBox vehicleOptions, utilityOptions;
    private final JLabel[] lorryParking, carParking;
    private Vehicle[] carsParked, lorryCoachParked;
    int totalCars, totalLorryCoach;
    double currentTotalIncome, todaysTotalIncome;
    private JTextField regUpd, vehicleDetUpd;
    private final JCheckBox discCheck;
    private final JButton submit;
    private int middleClickIndex;
    private String middleClickObj;
    
    /**
     * The Constructor to initialise the GUI, counter and array variables.
     * @since #1.0
     */
    CarParkGUI()
    {   //Initialise array and array usage.
        carsParked = new Vehicle[16];
        totalCars = 0;
        lorryCoachParked = new Vehicle[4];
        totalLorryCoach = 0;
        middleClickIndex = -1;
        middleClickObj = "";
        
        //Define the backgroud colour of the panel.
        this.setBackground(Color.WHITE);
        
        //Define the dimensions for panels, labels and combo boxes.
        Dimension northPanelSize = new Dimension(1000, 200);
        Dimension northLabelSize = new Dimension(900,150);
        Dimension comboBoxSize = new Dimension(200, 30);
        Dimension centerPanelSize = new Dimension(1100, 410);
        Dimension westPanelSize = new Dimension(400, 400);
        Dimension lorryLabelSize = new Dimension(300, 80);
        Dimension eastPanelSize = new Dimension(400, 400);
        Dimension carParkingSize = new Dimension(25, 25);
        Dimension southPanelSize = new Dimension(300, 130);
        Dimension SouthPanelLabelSize = new Dimension(290, 90);
        Dimension vehicleDetailsLabelSize = new Dimension(290, 15);
        Dimension updLabelSize = new Dimension(110, 30);
        Dimension textFieldSize = new Dimension(150, 30);
        
        //Creation of ImageIcon that will be placed onto a label. Do not remove image from specified directory.
        carParkLogo = new ImageIcon("Images/CarPark_Logo.jpg");
        
        //Creation of north JPanel that will hold the logo, heading and two combo boxes.
        northPanel = new JPanel();
        northPanel.setPreferredSize(northPanelSize);
        northPanel.setBackground(Color.WHITE);
        northPanel.setOpaque(true);
        northPanel.setVisible(true);
        
        //Creation of north JLabel that contains both the logo and the heading.
        northPanelLabel = new JLabel("  TEESSIDE PARKING", carParkLogo, JLabel.CENTER);
        northPanelLabel.setFont(new Font("TEESSIDE PARKING", Font.PLAIN, 75));
        northPanelLabel.setPreferredSize(northLabelSize);
        northPanelLabel.setHorizontalAlignment(SwingConstants.LEFT);
        northPanelLabel.setBackground(Color.WHITE);
        northPanelLabel.setOpaque(true);
        northPanelLabel.setVisible(true);
        
        //Creation of the array of vehicles that will be displayed inside the add vehicle combo box.
        vehicleList = new String[] { "Add Car", "Add Coach", "Add Lorry" };
        vehicleOptions = new JComboBox(vehicleList);
        vehicleOptions.setPreferredSize(comboBoxSize);
        ((JLabel) vehicleOptions.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        vehicleOptions.addActionListener(this);
        vehicleOptions.setToolTipText("Add New Vehicle");
        
        //Creation of the array of utilities that will be displayed inside the utilies combo box.
        utilityList = new String[] { "Save", "Load", "Current Total", "Today's Total", "Clear All" };
        utilityOptions = new JComboBox(utilityList);
        utilityOptions.setPreferredSize(comboBoxSize);
        ((JLabel) utilityOptions.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        utilityOptions.addActionListener(this);
        utilityOptions.setToolTipText("Select Utilities");
        
        //Creation of new colour (lighter blue) for the south panel and its panels within it.
        Color panelColour = new Color(150, 204, 255);
        
        //Creation of south JPanel that will hold the western and eastern JPanels and their respective grid JLabels displaying the car park.
        centerPanel = new JPanel();
        centerPanel.setPreferredSize(centerPanelSize);
        centerPanel.setBackground(panelColour);
        centerPanel.setOpaque(true);
        centerPanel.setVisible(true);
        
        //Creation of western JPanel that will hold a JLabel grid.
        westPanel = new JPanel();
        westPanel.setPreferredSize(westPanelSize);
        westPanel.setBackground(panelColour);
        westPanel.setLayout(new GridLayout(4, 1, 20, 20));
        westPanel.setOpaque(true);
        westPanel.setVisible(true);
        
        //Create new lorryParking JLabels with a for loop and add them to a JLabel array and the western JPanel.
        lorryParking = new JLabel[4];
        
        for(int i = 0; i < lorryParking.length; i++)
        {
            lorryParking[i] = new JLabel();
            lorryParking[i].setBackground(Color.WHITE);
            lorryParking[i].setPreferredSize(lorryLabelSize);
            lorryParking[i].setIcon(null);
            lorryParking[i].addMouseListener(this);
            lorryParking[i].setOpaque(true);
            lorryParking[i].setVisible(true);
            westPanel.add(lorryParking[i]);
        }
        
        //Create eastern JPanel that holds the car parking JLabels and will be placed on the south JPanel.
        eastPanel = new JPanel();
        eastPanel.setPreferredSize(eastPanelSize);
        eastPanel.setBackground(panelColour);
        eastPanel.setLayout(new GridLayout(4, 4, 20, 20));
        eastPanel.setOpaque(true);
        eastPanel.setVisible(true);
        
        //Create new carParking JLabels with a for loop and add them to a JLabel array and the eastern JPanel.
        carParking = new JLabel[16];
        
        for(int i = 0; i < carParking.length; i++)
        {
            carParking[i] = new JLabel();
            carParking[i].setBackground(Color.WHITE);
            carParking[i].setPreferredSize(carParkingSize);
            carParking[i].setIcon(null);
            carParking[i].addMouseListener(this);
            carParking[i].setOpaque(true);
            carParking[i].setVisible(true);
            eastPanel.add(carParking[i]);
        }
        
        //Creation of the JLabel that will title the "toString" label.
        vehicleDetailsLabel = new JLabel("<HTML><U>Vehicle Details</U></HTML>");
        vehicleDetailsLabel.setVerticalAlignment(SwingConstants.CENTER);
        vehicleDetailsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vehicleDetailsLabel.setPreferredSize(vehicleDetailsLabelSize);
        vehicleDetailsLabel.setBackground(Color.WHITE);
        vehicleDetailsLabel.setOpaque(true);
        vehicleDetailsLabel.setVisible(true);
        
        //Creation of the JPanel which will be positioned in the South West area.
        southWestPanel = new JPanel();
        southWestPanel.setPreferredSize(southPanelSize);
        southWestPanel.setBackground(Color.WHITE);
        southWestPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, panelColour, panelColour));
        southWestPanel.setVisible(true);
        southWestPanel.setOpaque(true);
        
        //Creation of the JLabel which will hold the "toString" method result.
        southWestPanelLabel = new JLabel();
        southWestPanelLabel.setPreferredSize(SouthPanelLabelSize);
        southWestPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        southWestPanelLabel.setBackground(Color.WHITE);
        southWestPanelLabel.setOpaque(true);
        southWestPanelLabel.setVisible(true);
        
        //Creation of the JPanel which will be positioned in the South East area.
        southEastPanel = new JPanel();
        southEastPanel.setPreferredSize(southPanelSize);
        southEastPanel.setBackground(Color.WHITE);
        southEastPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, panelColour, panelColour));
        southEastPanel.setVisible(true);
        southEastPanel.setOpaque(true);
        
        //Creation of the JLabel which will title the update vehicle JLabel.
        updateDetails = new JLabel("<HTML><U>Update Vehicle Details</U></HTML>");
        updateDetails.setVerticalAlignment(SwingConstants.CENTER);
        updateDetails.setHorizontalAlignment(SwingConstants.CENTER);
        updateDetails.setPreferredSize(vehicleDetailsLabelSize);
        updateDetails.setBackground(Color.WHITE);
        updateDetails.setOpaque(true);
        updateDetails.setVisible(true);
        
        //Creation of the Update Vehicle JLabel which will contain the components to update selected vehicles.
        southEastPanelLabel = new JLabel();
        southEastPanelLabel.setPreferredSize(SouthPanelLabelSize);
        southEastPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
        southEastPanelLabel.setBackground(Color.WHITE);
        southEastPanelLabel.setOpaque(true);
        southEastPanelLabel.setVisible(true);
        
        //Creation of the two update input fields.
        regUpd = new JTextField("");
        regUpd.setPreferredSize(textFieldSize);
        regUpd.setVisible(true);
        regUpd.setOpaque(true);
        
        vehicleDetUpd = new JTextField();
        vehicleDetUpd.setPreferredSize(textFieldSize);
        vehicleDetUpd.setVisible(true);
        vehicleDetUpd.setOpaque(true);
        
        //Creation of the two field identifiers.
        updLabelOne = new JLabel("Registration: ");
        updLabelOne.setPreferredSize(updLabelSize);
        updLabelOne.setBackground(Color.WHITE);
        updLabelOne.setVisible(true);
        updLabelOne.setOpaque(true);
        
        updLabelTwo = new JLabel("Car Length: ");
        updLabelTwo.setPreferredSize(updLabelSize);
        updLabelTwo.setBackground(Color.WHITE);
        updLabelTwo.setVisible(true);
        updLabelTwo.setOpaque(true);
        
        //Creation of the discount check box (disabled or tourist operator).
        discCheck = new JCheckBox("Disabled Badge Holder");
        discCheck.setBackground(Color.WHITE);
        
        //Creation of the submit button.
        submit = new JButton("Submit!");
        submit.addMouseListener(this);
        
        //The following initialising code will add relevant components to their respective JPanels.
        northPanel.add(northPanelLabel);
        northPanel.add(vehicleOptions);
        northPanel.add(utilityOptions);
        
        centerPanel.add(westPanel);
        centerPanel.add(eastPanel);
        
        southWestPanel.add(vehicleDetailsLabel, BorderLayout.NORTH);
        southWestPanel.add(southWestPanelLabel, BorderLayout.CENTER);
        
        southEastPanel.add(updateDetails);
        southEastPanel.add(updLabelOne);
        southEastPanel.add(regUpd);
        southEastPanel.add(updLabelTwo);
        southEastPanel.add(vehicleDetUpd);
        southEastPanel.add(discCheck);
        southEastPanel.add(submit);
        
        
        //Adding both the north south and center JPanels to be displayed on the JFrame.
        this.add(northPanel);
        this.add(centerPanel);
        this.add(southWestPanel);
        this.add(southEastPanel);
    }
    
    /**
     * This method overrides the Java actionPerformed method and gets the chosen event from the relevant JcomboBox or button and calls the relevant methods based upon that choice.
     * @param ae Recognises the source of the event such as the submit button or each individual item in the JComboBoxes.
     * @since #1.0
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        JComboBox comboBox = (JComboBox) ae.getSource();
        String choice = (String) comboBox.getSelectedItem();

        switch(choice)
        {
            case "Add Car":
                addCar();
                break;
            case "Add Coach":
                addCoach();
                break;
            case "Add Lorry":
                addLorry();
                break;
            case "Save":
                try
                {
                    save();
                }
                catch(IOException e)
                {
                    
                }
                break;
            case "Load":
                try
                {
                    load();
                }
                catch(IOException | ClassNotFoundException e)
                {
                    
                }
                break;
            case "Current Total":
                returnCurrentTotal();
                break;
            case "Today's Total":
                returnTodaysTotal();
                break;
            case "Clear All":
                clearAll();
                break;
        }
    }
    
    /**
     * This method is used to obtain details of Car length, disability and length of stay to create new car object and store in the vehicle array.
     * @return Initial return to return the user back to the GUI without an "ArrayIndexOutOfBoundException" which would involve adding a Car to an already full carsParked array.
     * @return returns inside if statements are used to handle the cancel button and the 'x' on a JOptionPane to avoid input mismatch exceptions and return the user to the GUI interface.
     * @throws ArrayIndexOutOfBoundException
     * @throws InputMismatchExpection
     * @since #1.0
     */
    private void addCar()
    {
        //Check arrays to see if there is room for an additional car. Rejects attempt to add a car if not.
        if(totalCars == carsParked.length)
        {
            JOptionPane.showMessageDialog(null, "Apologies, there are no more car parking spaces.", "Parking Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String carReg = getRegistration();
        if(carReg.matches("")) //if statements here handle the use of the GUI cancel button returning the user to the initial GUI.
        {
            return;
        }
        
        int carLength = getCarLength();
        if(carLength == -1)
        {
            return;
        }
        
        int lengthOfStay = getHours();
        if(lengthOfStay == -1)
        {
            return;
        }
        
        boolean isDisabled = getDisability();

        if(carLength > 6)
        {
            car = new ImageIcon(new ImageIcon("Images/campervan.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        }
        else
        {
            car = new ImageIcon(new ImageIcon("Images/car.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
        }
        
        Car newCar = new Car(carLength, isDisabled, carReg, car, lengthOfStay);
        
        currentTotalIncome += newCar.amountCharged();
        todaysTotalIncome += newCar.amountCharged();
        
        addToCarArray(newCar);
        
        addCarToCarPark();
    }
    
    /**
     * getHours method is called from "addCar" and will obtain the amount of hours intended to stay in the car park for cars. Throws a Try Catch to handle input from the user.
     * @return returns a valid integer value ranging between 1and 24, inclusive.
     * @return the return value of -1 is a sentinel value which is used to handle cancel button and 'x' clicks on the JOptionPane.
     * @throws InputMismatchException
     * @since #1.0
     */
    private int getHours()
    {
        boolean exc;
        String hours = "";
        int getHours = 0;
        
        do
        {
            exc = false;
            
            //Try and catch thrown here to validate all invalid input form the user.
            try
            {
                hours = (String)JOptionPane.showInputDialog(null, "How long do you wish to stay for? (Hours)");
                //"hours" will return null here if the user clicks the cancel option.
                if(hours == null)
                {
                    return -1;
                }
                
                getHours = Integer.parseInt(hours);
                
                if(getHours < 1 || getHours > 24)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid length of stay.", "Length Of Stay Error!", JOptionPane.ERROR_MESSAGE);
                    exc = true;
                }
            }
            catch(InputMismatchException | NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid length of stay.", "Length Of Stay Error!", JOptionPane.ERROR_MESSAGE);
                exc = true;
            }
        }while(exc == true);

        return getHours;
    }
    
    /**
     * This method is called from "addCar" to get the length of the car from the user. Throws a Try Catch to handle input from the user.
     * @return returns a valid integer value ranging from 1 to 12, inclusive.
     * @return returns a -1 sentinel value that is used to handle cancel button and 'x' clicks on the JOptionPane.
     * @throws InputMismatchException
     * @since #1.0
     */
    private int getCarLength()
    {
        boolean exc;
        int carLength = 0;
        
        do
        {
            exc = false;
            
            //Try and catch thrown here to validate all invalid input form the user.
            try
            {
                String getCarLength = JOptionPane.showInputDialog(null, "Please enter the length of your car. (Meters)");
                //"getCarLength" will return null here if the user clicks the cancel option.
                if(getCarLength == null)
                {
                    return -1;
                }
        
                carLength = Integer.parseInt(getCarLength);
                
                if(!validateLength(carLength))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid car length.", "Car Length Error!", JOptionPane.ERROR_MESSAGE);
                    exc = true;
                }
            }
            catch(InputMismatchException | NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid car length.", "Car Length Error!", JOptionPane.ERROR_MESSAGE);
                exc = true;
            }
        }while(exc == true);
        
        return carLength;
    }
    
    /**
     * This method is called from "addCar" and gets the user's disability status.
     * @return returns a boolean value of true if the user is a blue disabled badge holder, or false if not.
     * @since #1.0
     */
    private boolean getDisability()
    {
        int disability = JOptionPane.showConfirmDialog(null, "Do you have a disability badge?", "Disability", JOptionPane.YES_NO_OPTION);
        
        return (disability == JOptionPane.YES_OPTION);
    }
    
    /**
     * This method takes new car objects of type Vehicle and adds it to a Vehicle array and increment totals accordingly.
     * @param newCar newCar parameter holds the newly created Car objects of type Vehicle.
     * @since #1.0
     */
    private void addToCarArray(Vehicle newCar)
    {
        for(int i = 0; i < carsParked.length; i++)
        {
            if(carParking[i].getIcon() == null)
            {
                carsParked[i] = newCar;
                totalCars++;
                break;
            }
        }
    }
    
    /**
     * Once a car has been added to the vehicle array, this method sets a car image to the GUI where the next JLabel has a null ImageIcon.
     * @since #1.0
     */
    private void addCarToCarPark()
    {
        for(JLabel carPark : carParking)
        {
            if(carPark.getIcon() == null)
            {
                carPark.setIcon(car);
                break;
            }
        }
    }
    
    /**
     * Once a Lorry has been added to the vehicle array, this method sets a Lorry image to the GUI where the next JLabel has a null ImageIcon.
     * @since #1.0
     */
    private void addLorryToCarPark()
    {
        for (JLabel lorryPark : lorryParking)
        {
            if (lorryPark.getIcon() == null)
            {
                lorryPark.setIcon(lorry);
                break;
            }
        }
    }
    
    /**
     * Once a Coach has been added to the vehicle array, this method sets a Coach image to the GUI where the next JLabel has a null ImageIcon.
     * @since #1.0
     */
    private void addCoachToCarPark()
    {
        for(JLabel coachPark : lorryParking)
        {
            if(coachPark.getIcon() == null)
            {
                coachPark.setIcon(coach);
                break;
            }
        }
    }
    
    /**
     * This method takes new Lorry and Coach objects of type Vehicle, adds it to a Vehicle array, and increments totals accordingly.
     * @param newLorryCoach newLorryrCoach parameter holds newly created Coach and Lorry objects of type Vehicle.
     * @since #1.0
     */
    private void addToLorryCoachArray(Vehicle newLorryCoach)
    {
        for(int i = 0; i < lorryCoachParked.length; i++)
        {
            if(lorryParking[i].getIcon() == null)
            {
                lorryCoachParked[i] = newLorryCoach;
                totalLorryCoach++;
                break;
            }
        }
    }
    
    /**
     * This method gets the registration of the user's vehicle, it is shared by each "add"Vehicle"" method.
     * @return getReg will return a value of type String which will follow the format of regular number plates, no private plates. 2 chars, 2 numbers, 1 space and 3 chars. (In that order).
     * @return the empty string return, "", is a sentinel value that is used to handle cancel button and 'x' clicks on the JOptionPane.
     * @since #1.0
     */
    private String getRegistration()
    {
        boolean failed;
        String getReg = "";
        do
        {
            failed = false;
            
            getReg = JOptionPane.showInputDialog(null, "Please enter your vehicle's registration number. (LP17 JKL)");
            //"getReg" will return null of the user clicks the cancel option.
            if(getReg == null)
            {
                return "";
            }
            else if(validateRegistration(getReg))
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid vehicle registration.", "Vehicle Registration Error!", JOptionPane.ERROR_MESSAGE);
                failed = true;
            }
        }while(failed == true);
        
        return getReg;
    }
    
    /**
     * This method validates the user's registration input with a Regular Expression.
     * @param reg This parameter comes from the "getRegistration" method which contains a value of type String.
     * @return returns a boolean value of true if "reg" meets the regular expression criteria or false if not.
     * @since #1.0
     */
    private boolean validateRegistration(String reg)
    {
        return (!reg.matches("^[A-Z]{2}[0-9]{2}[ ]{1}[A-Z]{3}$")); //Regular number plates, no private plates. 2 chars, 2 numbers, 1 space and 3 chars. (In that order).
    }
    
    /**
     * This method is used to obtain details of a Coach's number of passengers and whether or not they are a tourist operator to create new Coach object and store it in the vehicle array.
     * @return returns inside if statements are used to handle the cancel button and the 'x' on a JOptionPane to avoid Input Mismatch Exceptions and return the user to the GUI interface.
     * @throws ArrayOutOfBoundsException
     * @throws InputMismatchException
     * @since #1.0
     */
    private void addCoach()
    {
        //Check arrays to see if there is room for an additional car. Rejects attempt to add a car if not.
        if(totalLorryCoach == lorryCoachParked.length)
        {
            JOptionPane.showMessageDialog(null, "Apologies, there are no more Coach parking spaces.", "Parking Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String coachReg = getRegistration();
        if(coachReg.matches("")) //if statements here handle the use of the GUI cancel button returning the user to the initial GUI.
        {
            return;
        }
        
        int peopleAmount = getPeopleAmount();
        if(peopleAmount == -1)
        {
            return;
        }
        
        boolean touristOp = getTouristOp();
        
        coach = new ImageIcon(new ImageIcon("Images/coach.png").getImage().getScaledInstance(300, 80, Image.SCALE_DEFAULT));
        
        Coach newCoach = new Coach(peopleAmount, touristOp, coachReg, coach);
        
        currentTotalIncome += newCoach.amountCharged();
        todaysTotalIncome += newCoach.amountCharged();
        
        addToLorryCoachArray(newCoach);
        
        addCoachToCarPark();
    }
    
    /**
     * This method is called form "addCoach" and gets the amount of passengers on a coach.
     * @return returns a valid integer value ranging from 0 to 57, inclusive.
     * @return returns a sentinel value of -1 which is used to handle the cancel button and 'x' clicks on the JOptionPane.
     * @throws InputMismatchException
     * @since #1.0
     */
    private int getPeopleAmount()
    {
        int numOfPeople = 0;
        boolean exc;
        
        do
        {
            exc = false;
            
            //Try and catch thrown here to validate all invalid input form the user.
            try
            {
                String people = JOptionPane.showInputDialog(null, "Please enter the amount of passengers you have.");
                //"people" will return null here if the user clicks the cancel option.
                if(people == null)
                {
                    return -1;
                }
                
                numOfPeople = Integer.parseInt(people);
                
                if(!validatePassengers(numOfPeople))
                {
                    JOptionPane.showMessageDialog(null, "Please enter an appropriate amount of passengers. (Maximum 57)", "Passenger Error!", JOptionPane.ERROR_MESSAGE);
                    exc = true;
                }
            }
            catch(InputMismatchException | NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter an appropriate amount of passengers. (Maximum 57)", "Passenger Error!", JOptionPane.ERROR_MESSAGE);
                exc = true;
            }
        }while(exc == true);
        
        return numOfPeople;
    }   

    /**
     * This method is called from the "addCoach" method and determines whether the Coach is a tourist operator or not.
     * @return returns a boolean value of true if the user clicks yes on the JOptionPane provided, or false otherwise.
     */
    private boolean getTouristOp()
    {
        int isTouristOp = JOptionPane.showConfirmDialog(null, "Are you a Tourist Operator?", "Tourist Operator", JOptionPane.YES_NO_OPTION);
        
        return (isTouristOp == JOptionPane.YES_OPTION);
    }
    
    /**
     * This method is used to obtain details of Lorry length and disability to create new car object, store in the vehicle array and adjust totals accordingly.
     * @return Initial return to return the user back to the GUI without an "ArrayIndexOutOfBoundException" which would involve adding a Lorry to an already full lorryCoachParked array.
     * @return Each other return handles the cancel and 'x' button clicks on a JOptionPane to avoid "InputMismatchException".
     * @throws ArrayIndexOutOfBoundException
     * @throws InputMismatchException
     * @since #1.0
     */
    private void addLorry()
    {
        if(totalLorryCoach == lorryCoachParked.length)
        {
            JOptionPane.showMessageDialog(null, "Apologies, there are no more Lorry parking spaces.", "Parking Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String lorryReg = getRegistration();
        if(lorryReg.matches("")) //if statements here handle the use of the GUI cancel button returning the user to the initial GUI.
        {
            return;
        }
        
        int lorryWeight = getLorryWeight();
        if(lorryWeight == -1)
        {
            return;
        }
        
        int lengthOfStay = getDays();
        if(lengthOfStay == -1)
        {
            return;
        }
        
        lorry = new ImageIcon(new ImageIcon("Images/lorry.png").getImage().getScaledInstance(200, 80, Image.SCALE_DEFAULT));
        
        Lorry newLorry = new Lorry(lorryWeight, lengthOfStay, lorryReg, lorry);
        
        currentTotalIncome += newLorry.amountCharged();
        todaysTotalIncome += newLorry.amountCharged();
        
        addToLorryCoachArray(newLorry);
        
        addLorryToCarPark();
    }
    
    /**
     * This method is called from the "addLorry" method and gets the weight of the Lorry from the user. Throws a Try Catch to handle input from the user.
     * @return returns an integer value between the range of 1 and 35, inclusive.
     * @return returns a sentinel value of -1 to handle the cancel and 'x' button clicks on the JOptionPane.
     * @throws InputMismatchException
     * @since #1.0
     */
    private int getLorryWeight()
    {
        int lorryWeight = 0;
        boolean exc;
        
        do
        {
            exc = false;
            
            //Try and catch thrown here to validate all invalid input form the user.
            try
            {
                String weight = JOptionPane.showInputDialog(null, "Please enter the weight of your HGV. (Tonnes)");
                
                //"weight" will return null here if the user clicks the cancel option.
                if(weight == null)
                {
                    return -1;
                }
                
                lorryWeight = Integer.parseInt(weight);
                
                if(!validateWeight(lorryWeight))
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid or appropriate weight. (Maximum 35 Tonnes)", "Weight Error!", JOptionPane.ERROR_MESSAGE);
                    exc = true;
                }
            }
            catch(InputMismatchException |  NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid or appropriate weight. (Maximum 35 Tonnes)", "Weight Error!", JOptionPane.ERROR_MESSAGE);
                exc = true;
            }
        }while(exc == true);
        
        return lorryWeight;
    }
    
    /**
     * This method is called by "addLorry" and gets the amount of days the user is staying with their Lorry.
     * @return returns an integer value greater than 1, inclusive.
     * @return returns a sentinel value of -1 which is used to handle the cancel button and 'x' clicks on the JOptionPane.
     * @throws InputMismatchException
     * @since #1.0
     */
    private int getDays()
    {
        int daysStayed = 0;
        boolean exc;
        
        do
        {
            exc = false;
            
            //Try and catch thrown here to validate all invalid input form the user.
            try
            {
                String days = JOptionPane.showInputDialog(null, "How long do you wish to stay for? (Days)");
                
                //"weight" will return null here if the user clicks the cancel option.
                if(days == null)
                {
                    return -1;
                }
                
                daysStayed = Integer.parseInt(days);
                
                if(daysStayed < 1)
                {
                    JOptionPane.showMessageDialog(null, "Please enter a valid length of stay.", "Length Of Stay Error!", JOptionPane.ERROR_MESSAGE);
                    exc = true;
                }
            }
            catch(InputMismatchException |  NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter a valid length of stay.", "Length Of Stay Error!", JOptionPane.ERROR_MESSAGE);
                exc = true;
            }
        }while(exc == true);
        
        return daysStayed;
    }
    
    /**
     * Validates a file name and calls the "writeToFile" method.
     * @throws IOException
     * @return returns a sentinel value of -1 which is used to handle the cancel button and 'x' clicks on the JOptionPane.
     * @since #1.0
     */
    private void save() throws IOException
    {
        //Serialise all data to a file.
        File fileName;
        boolean exc;
        
        do
        {
            exc = false;
            
            try
            {
                String getFileName = JOptionPane.showInputDialog(null, "Please enter the filename that you wish to save to. (Must be a .dat file)", "Save File", JOptionPane.OK_CANCEL_OPTION);
                //"fileName" will return null if the user clicks the cancel button.
                if(getFileName == null)
                {
                    return;
                }
                //Ensure "fileName" is atleast 5 chars long and the file extension is type ".dat"
                if(getFileName.length() < 5 || !getFileName.substring(getFileName.length() - 4).matches(".dat"))
                {
                    JOptionPane.showMessageDialog(null, "There was an error with your filename.", "Filename Error!", JOptionPane.ERROR_MESSAGE);
                    exc = true;
                }
                else
                {
                    fileName = new File(getFileName);
                
                    if(fileName.exists())
                    {
                        int confirm = JOptionPane.showConfirmDialog(null, "The filename you entered already exists, do you wish to overwrite?", "Filename Exists!", JOptionPane.YES_NO_OPTION);
                        if(confirm == JOptionPane.YES_OPTION)
                        {
                            writeToFile(fileName);
                        }
                        else
                        {
                            exc = true;
                        }
                    }
                    else
                    {
                        writeToFile(fileName);
                    }
                }
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(null, "There was an error with your filename.", "Filename Error!", JOptionPane.ERROR_MESSAGE);
                exc = true;
            }
        }while(exc == true);
    }
    
    /**
     * Serialises the appropriate data to the specified filename.
     * @param fileName holds the name of the valid filename.
     * @throws IOException
     * @since #1.0
     */
    private void writeToFile(File fileName) throws IOException
    {
        try
        {
            ArrayList<Object> save = new ArrayList<>();
            save.add(carsParked);
            save.add(lorryCoachParked);
            save.add(totalCars);
            save.add(totalLorryCoach);
            save.add(currentTotalIncome);
            save.add(todaysTotalIncome);
            
            FileOutputStream fOut = new FileOutputStream(fileName);
            ObjectOutputStream objOut = new ObjectOutputStream(fOut);

            objOut.writeObject(save);
            
            fOut.close();
            
            JOptionPane.showMessageDialog(null, "Save Successful", "Save", JOptionPane.INFORMATION_MESSAGE);
            
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error! Failed to initialise stream.", "Stream Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    /**
     * Validates an existing filename and daisy chains "readFromFile".
     * @throws IOException
     * @throws ClassNotFoundException 
     * @since #1.0
     */
    private void load() throws IOException, ClassNotFoundException
    {
        //Deserialise all data from a file.
        File fileName;
        boolean exc;
        
        do
        {
            exc = false;
            
            try
            {
                String getFileName = JOptionPane.showInputDialog(null, "Please enter the filename that you wish to load from. (Must be a .dat file)", "Load File", JOptionPane.OK_CANCEL_OPTION);
                //"fileName" will return null if the user clicks the cancel button.
                if(getFileName == null)
                {
                    return;
                }
                else
                {
                    fileName = new File(getFileName);
                
                    if(!fileName.exists())
                    {
                        JOptionPane.showMessageDialog(null, "The filename you entered doesn't exist. Please try again.", "Filename Doesn't Exist!", JOptionPane.INFORMATION_MESSAGE);
                        exc = true;
                    }
                    else
                    {
                        clearAll();
                        readFromFile(fileName);
                    }
                }
            }
            catch(IOException | ClassNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "There was an error with your filename.", "Filename Error!", JOptionPane.ERROR_MESSAGE);
                exc = true;
            }
        }while(exc == true);
    }
    
    /**
     * De-serialises data from a specified file and updates variables accordingly.
     * @param fileName holds the name of the valid filename.
     * @throws IOException
     * @throws ClassNotFoundException 
     * @since #1.0
     */
    private void readFromFile(File fileName) throws IOException, ClassNotFoundException
    {
        try
        {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream objInput = new ObjectInputStream(fileIn);
            
            ArrayList<Object> load = (ArrayList<Object>) objInput.readObject();
            
            carsParked = (Vehicle[]) load.get(0);
            lorryCoachParked = (Vehicle[]) load.get(1);
            totalCars = (int) load.get(2);
            totalLorryCoach = (int) load.get(3);
            currentTotalIncome = (double) load.get(4);
            todaysTotalIncome = (double) load.get(5);
            
            fileIn.close();
            
            displayLoad();
        }
        catch(IOException | ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, "Error initialising output stream!", "Output Stream Error!", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Displays the ImageIcons on relevant labels based upon the Vehicle[].
     * @since #1.0
     */
    private void displayLoad()
    {
        for(int i = 0; i < totalCars; i++)
        {
            carParking[i].setIcon(carsParked[i].getImage());
        }
        
        for(int i = 0; i < totalLorryCoach; i++)
        {
            lorryParking[i].setIcon(lorryCoachParked[i].getImage());
        }
    }
    
    /**
     * Displays a message to the user showing the current total of expected income from vehicles currently in the par park.
     * @since #1.0
     */
    private void returnCurrentTotal()
    {   
        JOptionPane.showMessageDialog(null, "Current income thus far: £" + String.format("%.2f", currentTotalIncome), "Current Total Expected Income.", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Displays a message to the user showing the current total of today's income including vehicles that have been removed from the car park.
     * @since #1.0
     */
    private void returnTodaysTotal()
    {
        JOptionPane.showMessageDialog(null, "Today's overall income: £" + String.format("%.2f", todaysTotalIncome), "Todays Income.", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Clears all data from the labels on the GUI, Vehicle arrays and their running totals accordingly.
     * @since #1.0
     */
    private void clearAll()
    {
        totalCars = 0;
        totalLorryCoach = 0;
        currentTotalIncome = 0.00;
        
        for(int i = 0; i < lorryParking.length; i++)
        {
            lorryParking[i].setIcon(null);
            lorryCoachParked[i] = null;
        }
        
        for(int i = 0; i < carParking.length; i++)
        {
            carParking[i].setIcon(null);
            carsParked[i] = null;
        }
        
        JOptionPane.showMessageDialog(null, "GUI Cleared!", "Clear All", JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * Validates the length of the car.
     * @param len holds the integer value of car length to be tested by this method.
     * @return returns a boolean value of false if value passed in is less than 1 or greater than 12, inclusive, or true otherwise.
     * @since #1.0
     */
    private boolean validateLength(int len)
    {
        return (!(len < 1 || len > 12));
    }
    
    /**
     * Validates the amount of passengers in a coach.
     * @param pass holds the integer value of a number of passengers to be tested by this method.
     * @return returns a boolean value of false if value passed in is less than 0 or greater than 57, inclusive, or true otherwise.
     * @since #1.0
     */
    private boolean validatePassengers(int pass)
    {
        if(pass < 0)
        {
            return false;
        }
        else if(pass > 57)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Validates the weight of the lorry.
     * @param weight holds the integer value of a weight to be tested by this method.
     * @return returns a boolean value of false if value passed in is less than 1 or greater than 35, inclusive, or true otherwise.
     */
    private boolean validateWeight(int weight)
    {
        if(weight < 1)
        {
            return false;
        }
        else if(weight > 35)
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
    /**
     * MouseEvent method used to daisy chain right, middle and left click methods.
     * @param mEvent recognises which button was clicked on the mouse.
     * @since #1.0
     */
    
    @Override
    public void mouseClicked(MouseEvent mEvent)
    {
        switch (mEvent.getButton()) 
        {
            case MouseEvent.BUTTON1:
                leftMouseClick(mEvent);
                break;
            case MouseEvent.BUTTON2: //This click will allow the user to change/update various fields relevant to the vehicle clicked.
                middleMouseClick(mEvent);
                break;
            case MouseEvent.BUTTON3: //This click will ask the user if they are sure they wish to delete selected vehicle and do so upon committing.
                rightMouseClick(mEvent);
                break;
        }
    }

    /**
     * Finds the appropriate JLabel and Vehicle array indexes and displays their "toString" upon mouse left click.
     * @param mEvent holds the left mouse click event and can be used to retrieve the source of what was clicked.
     * @since #1.0
     */
    private void leftMouseClick(MouseEvent mEvent)
    {   
        if(mEvent.getSource() == submit)
        {
            submitUpdate();
        }
        else
        {
            for(int i = 0; i < carsParked.length; i++)
            {
                if(i < lorryCoachParked.length && mEvent.getSource().equals(lorryParking[i]) && lorryParking[i].getIcon() == null)
                {
                    JOptionPane.showMessageDialog(null, "This parking space is free!", "Free Parking Space", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                else if(mEvent.getSource().equals(carParking[i]) && carParking[i].getIcon() == null)
                {
                    JOptionPane.showMessageDialog(null, "This parking space is free!", "Free Parking Space", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                else if(i < lorryCoachParked.length && mEvent.getSource().equals(lorryParking[i]) && lorryParking[i].getIcon() != null)
                {
                    southWestPanelLabel.setText(lorryCoachParked[i].toString());
                    southWestPanelLabel.setVerticalAlignment(SwingConstants.CENTER);
                    southWestPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                }
                else if(mEvent.getSource().equals(carParking[i]) && carParking[i].getIcon() != null)
                {
                    southWestPanelLabel.setText(carsParked[i].toString());
                    southWestPanelLabel.setVerticalAlignment(SwingConstants.CENTER);
                    southWestPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                }
            }
        }
    }
    
    /**
     * Finds the appropriate JLabel and Vehicle array indexes and deletes a vehicle upon right click.
     * @param mEvent holds the right mouse click event and can be used to retrieve the source of what was clicked.
     * @since #1.0
     */
    private void rightMouseClick(MouseEvent mEvent)
    {
        for(int i = 0; i < carsParked.length; i++)
        {
            if(i < lorryCoachParked.length && mEvent.getSource().equals(lorryParking[i]) && lorryParking[i].getIcon() == null)
            {
                JOptionPane.showMessageDialog(null, "This parking space is free!", "Free Parking Space", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            else if(mEvent.getSource().equals(carParking[i]) && carParking[i].getIcon() == null)
            {
                JOptionPane.showMessageDialog(null, "This parking space is free!", "Free Parking Space", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            else if(i < lorryCoachParked.length && mEvent.getSource().equals(lorryParking[i]) && lorryParking[i].getIcon() != null)
            {
                int delete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this object?", "Delete Vehicle", JOptionPane.YES_NO_OPTION);
                if(delete == JOptionPane.YES_OPTION)
                {
                    currentTotalIncome -= lorryCoachParked[i].getAmountCharged();
                    lorryParking[i].setIcon(null);
                    lorryCoachParked[i] = null;
                    totalLorryCoach--;
                    JOptionPane.showMessageDialog(null, "Vehicle removed.", "Delete Vehicle", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                else
                {
                    break;
                }
            }
            else if(mEvent.getSource().equals(carParking[i]) && carParking[i].getIcon() != null)
            {
                int delete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this vehicle?", "Delete Vehicle", JOptionPane.YES_NO_OPTION);
                if(delete == JOptionPane.YES_OPTION)
                {
                    currentTotalIncome -= carsParked[i].getAmountCharged();
                    carParking[i].setIcon(null);
                    carsParked[i] = null;
                    totalCars--;
                    JOptionPane.showMessageDialog(null, "Vehicle removed.", "Delete Vehicle", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                else
                {
                    break;
                }
            }
        }
    }
    
    /**
     * Finds the appropriate JLabel and Vehicle array indexes and updates the "Update" GUI Panel to display appropriate JTextFields.
     * @param mEvent holds the middle mouse click event and can be used to retrieve the source of what was clicked.
     * @since #1.0
     */
    private void middleMouseClick(MouseEvent mEvent)
    {
        for(int i = 0; i < carsParked.length; i++)
        {
            if(i < lorryCoachParked.length && mEvent.getSource().equals(lorryParking[i]) && lorryParking[i].getIcon() == null)
            {
                JOptionPane.showMessageDialog(null, "This parking space is free!", "Free Parking Space", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            else if(mEvent.getSource().equals(carParking[i]) && carParking[i].getIcon() == null)
            {
                JOptionPane.showMessageDialog(null, "This parking space is free!", "Free Parking Space", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            else if(i < lorryCoachParked.length && mEvent.getSource().equals(lorryParking[i]) && lorryParking[i].getIcon() != null)
            {
                if(lorryParking[i].getIcon() == lorry)
                {
                    updLabelTwo.setText("Lorry Weight: ");
                    discCheck.setText("N/A");
                    middleClickObj = "Lorry";
                    middleClickIndex = i;
                    break;
                }
                else
                {
                    updLabelTwo.setText("No. Of Passengers: ");
                    discCheck.setText("Tourist Operator");
                    middleClickObj = "Coach";
                    middleClickIndex = i;
                    break;
                }
            }
            else if(mEvent.getSource().equals(carParking[i]) && carParking[i].getIcon() != null)
            {
                updLabelTwo.setText("Car Length: ");
                discCheck.setText("Disabled Badge Holder");
                middleClickObj = "Car";
                middleClickIndex = i;
                break;
            }
        }
    }
    
    /**
     * Gets the data from the text fields and updates the classes appropriately, modifying total accordingly.
     * @since #1.0
     */
    private void submitUpdate()
    {
        if(middleClickIndex == -1 || middleClickObj.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please select a vehicle to update.", "Vehicle Not Specified", JOptionPane.ERROR_MESSAGE);
        }
        else if(regUpd.getText().equals("") || vehicleDetUpd.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "One of your fields were not filled in.", "Empty Field Error!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            switch(middleClickObj)
            {
                case "Lorry":
                    if(validateRegistration(regUpd.getText()) || !validateWeight(Integer.parseInt(vehicleDetUpd.getText())))
                    {
                        JOptionPane.showMessageDialog(null, "Error! Please check your details again.", "Data Entry Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        currentTotalIncome -= lorryCoachParked[middleClickIndex].getAmountCharged();
                        todaysTotalIncome -= lorryCoachParked[middleClickIndex].getAmountCharged();
                        lorryCoachParked[middleClickIndex].update(regUpd.getText(), Integer.parseInt(vehicleDetUpd.getText()));
                        currentTotalIncome += lorryCoachParked[middleClickIndex].getAmountCharged();
                        todaysTotalIncome += lorryCoachParked[middleClickIndex].getAmountCharged();
                        JOptionPane.showMessageDialog(null, "Update successfull!", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                        regUpd.setText("");
                        vehicleDetUpd.setText("");
                    }
                    break;
                case "Coach":
                    if(validateRegistration(regUpd.getText()) || !validatePassengers(Integer.parseInt(vehicleDetUpd.getText())))
                    {
                        JOptionPane.showMessageDialog(null, "Error! Please check your details again.", "Data Entry Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        currentTotalIncome -= lorryCoachParked[middleClickIndex].getAmountCharged();
                        todaysTotalIncome -= lorryCoachParked[middleClickIndex].getAmountCharged();
                        lorryCoachParked[middleClickIndex].update(regUpd.getText(), Integer.parseInt(vehicleDetUpd.getText()), discCheck.isSelected());
                        currentTotalIncome += lorryCoachParked[middleClickIndex].getAmountCharged();
                        todaysTotalIncome += lorryCoachParked[middleClickIndex].getAmountCharged();
                        JOptionPane.showMessageDialog(null, "Update successfull!", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                        regUpd.setText("");
                        vehicleDetUpd.setText("");
                    }
                    break;
                case "Car":
                    if(validateRegistration(regUpd.getText()) || !validateLength(Integer.parseInt(vehicleDetUpd.getText())))
                    {
                        JOptionPane.showMessageDialog(null, "Error! Please check your details again.", "Data Entry Error!", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        currentTotalIncome -= carsParked[middleClickIndex].getAmountCharged();
                        todaysTotalIncome -= carsParked[middleClickIndex].getAmountCharged();
                        carsParked[middleClickIndex].update(regUpd.getText(), Integer.parseInt(vehicleDetUpd.getText()), discCheck.isSelected());
                        currentTotalIncome += carsParked[middleClickIndex].getAmountCharged();
                        todaysTotalIncome += carsParked[middleClickIndex].getAmountCharged();
                        carsParked[middleClickIndex].update(regUpd.getText(), Integer.parseInt(vehicleDetUpd.getText()), discCheck.isSelected());
                        JOptionPane.showMessageDialog(null, "Update successfull!", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                        regUpd.setText("");
                        vehicleDetUpd.setText("");
                    }
                    break;
            }
        }
    }
    
    /**
     * Unused Method. Method incorporated to allow program to compile.
     * @param e holds the mouse pressed event. "e" is of type MouseEvent.
     */
    @Override
    public void mousePressed(MouseEvent e)
    {
        
    }

    /**
     * Unused Method. Method incorporated to allow program to compile.
     * @param e holds the mouse released event. "e" is of type MouseEvent.
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {
        
    }

    /**
     * Displays a border around the JLabel that your mouse hovers over.
     * @param mEvent holds the mouse entered event. "mEvent" is of type MouseEvent.
     * @since #1.0
     */
    @Override
    public void mouseEntered(MouseEvent mEvent)
    {
        for(int i = 0; i < carsParked.length; i++)
        {
            if(i < lorryCoachParked.length && mEvent.getSource().equals(lorryParking[i]))
            {
                lorryParking[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
            else if(mEvent.getSource().equals(carParking[i]))
            {
                carParking[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            }
        }
    }

    /**
     * Removes the border around the JLabel that your mouse hovers over.
     * @param mEvent holds the mouse exited event. "mEvent" is of type MouseEvent.
     */
    @Override
    public void mouseExited(MouseEvent mEvent)
    {
        for(int i = 0; i < carsParked.length; i++)
        {
            if(i < lorryCoachParked.length && mEvent.getSource().equals(lorryParking[i]))
            {
                lorryParking[i].setBorder(null);
            }
            else if(mEvent.getSource().equals(carParking[i]))
            {
                carParking[i].setBorder(null);
            }
        }
    }
}