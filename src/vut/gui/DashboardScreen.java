package vut.gui;

import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import vut.data.Property;

public class DashboardScreen extends JFrame {

    private JPanel panel;

    private JLabel lblTotalProperties;
    private JLabel lblTotalSales;
    private JLabel lblTotalCommission;
    private JLabel lblHighestPrice;
    private JLabel lblTownhouses;

    public DashboardScreen(ArrayList<Property> propertyList) {

        panel = new JPanel();
        panel.setLayout(null);

        Font titleFont = new Font("Arial", Font.BOLD, 22);
        Font statFont = new Font("Arial", Font.PLAIN, 18);

        JLabel lblTitle = new JLabel("PropTrack Dashboard");
        lblTitle.setFont(titleFont);
        lblTitle.setBounds(20, 20, 400, 40);

        panel.add(lblTitle);

        lblTotalProperties = new JLabel();
        lblTotalSales = new JLabel();
        lblTotalCommission = new JLabel();
        lblHighestPrice = new JLabel();
        lblTownhouses = new JLabel();

        lblTotalProperties.setFont(statFont);
        lblTotalSales.setFont(statFont);
        lblTotalCommission.setFont(statFont);
        lblHighestPrice.setFont(statFont);
        lblTownhouses.setFont(statFont);

        lblTotalProperties.setBounds(20, 100, 600, 30);
        lblTotalSales.setBounds(20, 150, 600, 30);
        lblTotalCommission.setBounds(20, 200, 600, 30);
        lblHighestPrice.setBounds(20, 250, 600, 30);
        lblTownhouses.setBounds(20, 300, 600, 30);

        panel.add(lblTotalProperties);
        panel.add(lblTotalSales);
        panel.add(lblTotalCommission);
        panel.add(lblHighestPrice);
        panel.add(lblTownhouses);

        calculateStatistics(propertyList);

        setContentPane(panel);

        setTitle("PropTrack Dashboard");
        setSize(700, 450);
        setLocationRelativeTo(null);
    }

    private void calculateStatistics(ArrayList<Property> propertyList) {

        int totalProperties = propertyList.size();

        double totalSales = 0;
        double totalCommission = 0;
        double highestPrice = 0;

        int townhouseCount = 0;

        for (Property property : propertyList) {

            totalSales += property.getSellingPrice();

            totalCommission += property.calc_Commission();

            if (property.getSellingPrice() > highestPrice) {
                highestPrice = property.getSellingPrice();
            }

            if (property.getPropType().equalsIgnoreCase("Townhouse")) {
                townhouseCount++;
            }
        }

        NumberFormat currency = NumberFormat.getCurrencyInstance(new Locale("en", "ZA"));

        lblTotalProperties.setText("Total Properties Sold: " + totalProperties);

        lblTotalSales.setText("Total Sales Value: "
                + currency.format(totalSales));

        lblTotalCommission.setText("Total Commission Earned: "
                + currency.format(totalCommission));

        lblHighestPrice.setText("Highest Property Price: "
                + currency.format(highestPrice));

        lblTownhouses.setText("Number of Townhouses Sold: "
                + townhouseCount);
    }
}