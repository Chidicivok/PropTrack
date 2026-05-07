package vut.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vut.data.Property;

public class PropertyTableView extends JFrame {

    private JTable tblProperties;
    private DefaultTableModel tableModel;

    public PropertyTableView(ArrayList<Property> propertyList) {

        String[] columns = {
            "Customer",
            "Ref No",
            "Type",
            "Area",
            "Selling Price",
            "Deposit",
            "Loan",
            "Monthly Instalment",
            "Total Payment",
            "Total Interest",
            "Agency",
            "Agent"
        };

        tableModel = new DefaultTableModel(columns, 0);

        tblProperties = new JTable(tableModel);
        tblProperties.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(tblProperties);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        loadTableData(propertyList);

        setSize(1100, 500);
        setTitle("PropTrack - Properties Sold");
        setLocationRelativeTo(null);
    }

    private void loadTableData(ArrayList<Property> propertyList) {

        for (Property property : propertyList) {

            Object[] row = {
                property.getCustomer(),
                property.getPRefNo(),
                property.getPropType(),
                property.getArea(),
                String.format("R %.2f", property.getSellingPrice()),
                String.format("R %.2f", property.calcDeposit()),
                String.format("R %.2f", property.getLoan()),
                String.format("R %.2f", property.calcMonthlyInstalment()),
                String.format("R %.2f", property.totPayment()),
                String.format("R %.2f", property.totInterest()),
                property.getAgency(),
                property.getAgentName()
            };

            tableModel.addRow(row);
        }
    }
}