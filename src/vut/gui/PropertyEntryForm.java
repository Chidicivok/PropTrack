// 223515760 - CHIGBU CHIDI
//property entry form
package vut.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import vut.data.Property;

// property entry form extending jframe
public class PropertyEntryForm extends JFrame {

    // declare array of PD class type to store data
    private ArrayList<Property> arrPropertyList;

    // declare labels
    JLabel lblPropertRefNo, lblPropertyType, lblAgency, lblArea, lblSellingPrice, lblIsDepositRequired, lblAgentName, lblCustomer;

    // declare text fields
    JTextField txtPropertyRefNo, txtArea, txtSellingPrice, txtAgentName, txtCustomer;

    // declare combo box
    JComboBox<String> cmbPropertyType, cmbAgency;

    ButtonGroup depositGroup;

    // declare radio buttons
    JRadioButton rbnYes, rbnNo;

    // declare buttons
    JButton btnAddProperty, btnClear, btnClose;

    // declare panel for controls
    JPanel pnlPropertyEntry;

//     //object od pd class
//     Property objProperty;
    public PropertyEntryForm(ArrayList<Property> arrPropertyList) {

        // initialize array
        this.arrPropertyList = arrPropertyList;

        // initialize labels
        lblPropertRefNo = new JLabel("Prop Ref No:");
        lblPropertyType = new JLabel("Property Type:");
        lblAgency = new JLabel("Agency:");
        lblArea = new JLabel("Area:");
        lblSellingPrice = new JLabel("Selling Price:");
        lblIsDepositRequired = new JLabel("Is Deposit Required?");
        lblAgentName = new JLabel("Agent Name:");
        lblCustomer = new JLabel("Customer:");

        // initialize text fields
        txtPropertyRefNo = new JTextField();
        txtArea = new JTextField();
        txtSellingPrice = new JTextField();
        txtAgentName = new JTextField();
        txtCustomer = new JTextField();

        // initialize combo box
        cmbPropertyType = new JComboBox<>();
        cmbAgency = new JComboBox<>();

        // insert values into property type combo box
        cmbPropertyType.addItem("House");
        cmbPropertyType.addItem("Townhouse");
        cmbPropertyType.addItem("Flat");

        // insert values into agency combo box
        cmbAgency.addItem("LeapFrog");
        cmbAgency.addItem("Realnet");
        cmbAgency.addItem("Remax");

        //initialize radio buttons
        rbnYes = new JRadioButton("YES");
        rbnNo = new JRadioButton("NO");

        depositGroup = new ButtonGroup();
        depositGroup.add(rbnYes);
        depositGroup.add(rbnNo);

        // initialize buttons
        btnAddProperty = new JButton("Add Property");
        btnClear = new JButton("Clear");
        btnClose = new JButton("Close");

        // initialize panel for controls
        pnlPropertyEntry = new JPanel();

        // set panel layout to null to enable manual placing of controls
        pnlPropertyEntry.setLayout(null);

        // add controls unto the panel
        pnlPropertyEntry.add(lblPropertRefNo);
        pnlPropertyEntry.add(lblPropertyType);
        pnlPropertyEntry.add(lblAgency);
        pnlPropertyEntry.add(lblArea);
        pnlPropertyEntry.add(lblSellingPrice);
        pnlPropertyEntry.add(lblIsDepositRequired);
        pnlPropertyEntry.add(lblAgentName);
        pnlPropertyEntry.add(lblCustomer);
        pnlPropertyEntry.add(txtPropertyRefNo);
        pnlPropertyEntry.add(txtArea);
        pnlPropertyEntry.add(txtSellingPrice);
        pnlPropertyEntry.add(txtAgentName);
        pnlPropertyEntry.add(txtCustomer);
        pnlPropertyEntry.add(cmbPropertyType);
        pnlPropertyEntry.add(cmbAgency);
        pnlPropertyEntry.add(rbnYes);
        pnlPropertyEntry.add(rbnNo);
        pnlPropertyEntry.add(btnAddProperty);
        pnlPropertyEntry.add(btnClear);
        pnlPropertyEntry.add(btnClose);

        // set size for controls and placement on jframe panel
        lblPropertRefNo.setBounds(20, 20, 150, 25);
        txtPropertyRefNo.setBounds(200, 20, 200, 25);

        lblPropertyType.setBounds(20, 50, 150, 25);
        cmbPropertyType.setBounds(200, 50, 200, 25);

        lblAgency.setBounds(20, 80, 150, 25);
        cmbAgency.setBounds(200, 80, 200, 25);

        lblArea.setBounds(20, 110, 150, 25);
        txtArea.setBounds(200, 110, 200, 25);

        lblSellingPrice.setBounds(20, 140, 150, 25);
        txtSellingPrice.setBounds(200, 140, 200, 25);

        lblIsDepositRequired.setBounds(20, 170, 150, 25);
        rbnYes.setBounds(200, 170, 50, 25);
        rbnNo.setBounds(250, 170, 50, 25);

        lblAgentName.setBounds(20, 200, 150, 25);
        txtAgentName.setBounds(200, 200, 200, 25);

        lblCustomer.setBounds(20, 230, 150, 25);
        txtCustomer.setBounds(200, 230, 200, 25);

        btnAddProperty.setBounds(20, 300, 120, 25);
        btnClear.setBounds(160, 300, 120, 25);
        btnClose.setBounds(300, 300, 120, 25);

        // fix panel unto jframe and ensure visibility
        setContentPane(pnlPropertyEntry);

        // register button events
        btnAddProperty.addActionListener(new btnAddPropertyEvent());
        btnClear.addActionListener(new btnClearEvent());
        btnClose.addActionListener(new btnCloseEvent());

        

//          // initialize default constructor
//          objProperty = new Property();
    }

    

    private class btnAddPropertyEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                // collect inputs
                String pRefNo = txtPropertyRefNo.getText().trim().toUpperCase();
                String propType = (String) cmbPropertyType.getSelectedItem();
                String area = txtArea.getText().trim().toUpperCase();
                String agentname = txtAgentName.getText().trim();
                String agency = (String) cmbAgency.getSelectedItem();
                String aCust = txtCustomer.getText().toUpperCase();
                double sellingPrice = Double.parseDouble(txtSellingPrice.getText().trim());

                boolean isDepositReq;
                if (rbnYes.isSelected()) {
                    isDepositReq = true;
                } else if (rbnNo.isSelected()) {
                    isDepositReq = false;
                } else {
                    throw new Exception("You must select if deposit is required");
                }

                // create object of pd class to store this inputs
                Property objProperty1 = new Property(pRefNo, propType, area, agentname, agency, sellingPrice, isDepositReq, aCust);

                // use another object of pd class to call addrpov - checks duplicates and adds
                Property objProperty = new Property();
                objProperty.AddProp(objProperty1);

                // display message for succesful add
                JOptionPane.showMessageDialog(rootPane, "Propert added succesfully", "Information", JOptionPane.INFORMATION_MESSAGE);

                // clear button
                btnClear.doClick();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    // for clear
    private class btnClearEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // clear inputs
            txtPropertyRefNo.setText("");
            cmbPropertyType.setSelectedIndex(0);
            cmbAgency.setSelectedIndex(0);
            txtArea.setText("");
            txtSellingPrice.setText("");
            
            depositGroup.clearSelection();
            
            txtAgentName.setText("");
            txtCustomer.setText("");

            // set focus to property ref no control
            txtPropertyRefNo.requestFocus();

        }

    }

    // for close
    private class btnCloseEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // close this form
            dispose();
        }

    }
}

//     public static void main(String[] args) {
//          PropertyEntryForm obj = new PropertyEntryForm();
//          obj.setSize(600, 400);
//          obj.setVisible(true);
//          
//          obj.setResizable(false);
//     }
//}
