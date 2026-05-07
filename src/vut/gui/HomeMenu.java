// 223515760 - CHIGBU CHIDI
// GUI Main menu
package vut.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import vut.data.DataStorageException;
import vut.data.NotFoundException;
import vut.data.Property;

// home menu class extending jframe
public class HomeMenu extends JFrame {

    // declare menu bar
    JMenuBar barHomeMenu;

    // declare menus
    JMenu mnuFile, mnuProperties;

    // declare menu items
    JMenuItem mnuSearchProperty, mnuUpdateAgent, mnuCount, mnuCancel, mnuExit;
    JMenuItem mnuAddProperty, mnuViewProperty, mnuDashboard;

    // declare text area for display
    JTextArea taDisplay;

    // declare panel for text area
    JPanel pnlDisplay;

    //declare array of PD Class type to store data
    private ArrayList<Property> arrPropertyList;

    //object of property class
    private Property objProperty;

    //Constructor of home menu
    public HomeMenu() {

        // initialize array
        arrPropertyList = new ArrayList<>();

        //initialize menu bar
        barHomeMenu = new JMenuBar();

        // initialize menus
        mnuFile = new JMenu("FILE");
        mnuProperties = new JMenu("PROPERTIES");

        //create font for menus
        Font objMenuFont = new Font("ARIAL", Font.BOLD, 24);

        //set font for menus
        mnuFile.setFont(objMenuFont);
        mnuProperties.setFont(objMenuFont);

        // initialize menu items
        mnuSearchProperty = new JMenuItem("Search Property Sold By Ref");
        mnuUpdateAgent = new JMenuItem("Update Agent Name");
        mnuCount = new JMenuItem("Count No Of Townhouses Sold");
        mnuCancel = new JMenuItem("Cancel Properties From Sold List");
        mnuExit = new JMenuItem("Exit");

        mnuAddProperty = new JMenuItem("Add Selling Property");
        mnuViewProperty = new JMenuItem("View Properties Sold");
        mnuDashboard = new JMenuItem("View Dashboard");

        //font for menu items
        Font objMenuItemsFont = new Font("ARIAL", Font.PLAIN, 16);

        // set fonts for menu items
        mnuSearchProperty.setFont(objMenuItemsFont);
        mnuUpdateAgent.setFont(objMenuItemsFont);
        mnuCount.setFont(objMenuItemsFont);
        mnuCancel.setFont(objMenuItemsFont);
        mnuExit.setFont(objMenuItemsFont);

        mnuAddProperty.setFont(objMenuItemsFont);
        mnuViewProperty.setFont(objMenuItemsFont);

        // sadd menu items to respective menus
        mnuFile.add(mnuSearchProperty);
        mnuFile.add(mnuUpdateAgent);
        mnuFile.add(mnuCount);
        mnuFile.add(mnuCancel);
        mnuFile.add(mnuExit);

        mnuProperties.add(mnuAddProperty);
        mnuProperties.add(mnuViewProperty);
        mnuProperties.add(mnuDashboard);

        // add menus onto the menu bar
        barHomeMenu.add(mnuFile);
        barHomeMenu.add(mnuProperties);

        // set the menu bar onto the jframe
        setJMenuBar(barHomeMenu);

        // register menu item events 
        mnuSearchProperty.addActionListener(new mnuSearchPropertyEvent());
        mnuUpdateAgent.addActionListener(new mnuUpdateAgentEvent());
        mnuCount.addActionListener(new mnuCountEvent());
        mnuCancel.addActionListener(new mnuCancelEvent());
        mnuExit.addActionListener(new mnuExitEvent());

        mnuAddProperty.addActionListener(new mnuAddPropertyEvent());
        mnuViewProperty.addActionListener(new mnuViewPropertyEvent());
        mnuDashboard.addActionListener(new mnuDashboardEvent());

        try {
            // on initialization of gui call the initialize method of pd class
            objProperty = new Property();
            objProperty.initialise();

        } catch (DataStorageException e) {

            JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }
    // end of constructor of home menu

    // INNER CLASS FOR ALL MNU ITEM EVENTS
    private class mnuSearchPropertyEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // use try catch

            try {
                String pRefNo = JOptionPane.showInputDialog(rootPane, "Enter Ref No", "Find Property", JOptionPane.QUESTION_MESSAGE);

                if (pRefNo.isEmpty()) {
                    throw new NotFoundException("You must eneter a refreence number");
                }

                String display = objProperty.findSoldProp(pRefNo).toString();

                taDisplay.setText(display);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(barHomeMenu, ex.getMessage());
            }

            // call methods for find sold method of pd class using ref no collect via inputbox
            // display the returned data
        }

    }

    // for update
    private class mnuUpdateAgentEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // use try catch
            try {
                String pRefNo = JOptionPane.showInputDialog(rootPane, "Enter Ref No", "Find Property", JOptionPane.QUESTION_MESSAGE);

                Property objProperty2 = objProperty.findSoldProp(pRefNo);

                String newAgentName = JOptionPane.showInputDialog(rootPane, "Enter New Agent Name", "New Name", JOptionPane.QUESTION_MESSAGE);

                objProperty.updateAgentName(objProperty2, newAgentName);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(barHomeMenu, ex.getMessage());
            }
            // locate agent using findsoldprop method and refno collected via inputbox
            // another inputbox to collect the new agent name
            //call update agent name method of pd class
        }

    }

    // for count
    private class mnuCountEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String display = "There are " + objProperty.countTownhouses() + " Townhouses sold";

            JOptionPane.showMessageDialog(barHomeMenu, display);
            // call count method and display result in msg box not dispaly area
        }

    }

    // for cancel
    private class mnuCancelEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // try catch
            try {
                String pRefNo = JOptionPane.showInputDialog(rootPane, "Enter Ref No", "Find Property", JOptionPane.QUESTION_MESSAGE);

                Property objProperty2 = objProperty.findSoldProp(pRefNo);

                objProperty.deleteSoldProp(objProperty2);

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(barHomeMenu, ex.getMessage());
            }
            // use ref no to identify property using input box
            // call delete method to delete that property
        }

    }

    // for exit
    private class mnuExitEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // try catch

            try {
                int exit = JOptionPane.showConfirmDialog(barHomeMenu, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);

                if (exit == JOptionPane.YES_OPTION) {

                    // save data to file 
                    objProperty.terminate();

                    System.exit(0);
                }

            } catch (DataStorageException ex) {

                JOptionPane.showMessageDialog(barHomeMenu, "Failed to save");

            }
            // must verify is user wants to cancel using confirmdialogbox
            // call terminate method to exit application if yes
        }

    }

    // for add property
    private class mnuAddPropertyEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // open the property entry form
            // object of the property entry form
            PropertyEntryForm objPropertyEntryForm = new PropertyEntryForm(arrPropertyList);

            // set title of prop entry form
            objPropertyEntryForm.setTitle("ADD SELLING PROPERTY FORM BY CHIGBU CHIDI");

            // set size of prop entry form
            objPropertyEntryForm.setSize(500, 500);

            // set prop entry form to visible
            objPropertyEntryForm.setVisible(true);

            // prevent prop entry form from resizing
            objPropertyEntryForm.setResizable(false);

            // call methd to add prop from pd class
        }

    }

    // for view properties
    private class mnuViewPropertyEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            ArrayList<Property> properties = objProperty.getAll();

            if (properties.isEmpty()) {
                JOptionPane.showMessageDialog(
                        rootPane,
                        "No properties found.",
                        "PropTrack",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return;
            }

            PropertyTableView tableView = new PropertyTableView(properties);
            tableView.setVisible(true);
        }

    }

    private class mnuDashboardEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            ArrayList<Property> properties = objProperty.getAll();

            DashboardScreen dashboard = new DashboardScreen(properties);

            dashboard.setVisible(true);
        }
    }

    public static void main(String[] args) {

        // object of home menu to call
        HomeMenu objHomeMenu = new HomeMenu();

        // set size of home menu form
        objHomeMenu.setSize(1200, 600);

        // set Title 
        objHomeMenu.setTitle("HOME MENU BY CHIGBU CHIDI");

        // make form visible
        objHomeMenu.setVisible(true);

    }

}
