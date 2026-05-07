//223515760- CHIGBU CHIDI
//Problem domain class
// declare package
package vut.data;

import java.util.ArrayList;
import java.io.Serializable;

public class Property implements Serializable {

    private static final long serialVersionUID = 1L;

    // declaration of class variables
    private String pRefNo, propType, area, agentname, agency, aCust;
    private double sellingPrice;
    private boolean isDepositReq;

    // default constructor
    public Property() {
        pRefNo = propType = area = agentname = agency = aCust = "";
        sellingPrice = 0;
        isDepositReq = false;
    }

    // parameterized constructor
    public Property(String pRefNo, String propType, String area, String agentname, String agency, double sellingPrice, boolean isDepositReq, String aCust) {
     setPRefNo(pRefNo);
     setPropType(propType);
     setArea(area);
     setAgentName(agentname);
     setAgency(agency);
     setSellingPrice(sellingPrice);
     setIsDepositReq(isDepositReq);
     setCustomer(aCust);
}

    // set property type
    public void setPropType(String propType) {

        if (propType == null || propType.trim().isEmpty()) {
            throw new IllegalArgumentException("Property type is required");
        }

        this.propType = propType;
    }

    public String getPropType() {
        return propType;
    }

    public String getArea() {
        return area;
    }

    public String getCustomer() {
        return aCust;
    }

    public void setAgentName(String agentname) {

        if (agentname == null || agentname.trim().isEmpty()) {
            throw new IllegalArgumentException("Agent name  is required");
        }

        this.agentname = agentname.trim();
    }

    public String getAgentName() {
        return agentname;
    }

    public String getPRefNo() {
        return pRefNo;
    }

    public void setAgency(String agency) {

        if (agency == null || agency.trim().isEmpty()) {
            throw new IllegalArgumentException("Agency is required");
        }

        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }

    public void setSellingPrice(double sellingPrice) {

        if (sellingPrice <= 0) {
            throw new IllegalArgumentException("Selling price must be greater than zero");
        }

        this.sellingPrice = sellingPrice;
    }

    public void setPRefNo(String pRefNo) {
        if (pRefNo == null || pRefNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Property reference number is required");
        }

        this.pRefNo = pRefNo.trim().toUpperCase();
    }

    public void setArea(String area) {
        if (area == null || area.trim().isEmpty()) {
            throw new IllegalArgumentException("Area is required");
        }

        this.area = area.trim().toUpperCase();
    }

    public void setCustomer(String aCust) {
        if (aCust == null || aCust.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required");
        }

        this.aCust = aCust.trim().toUpperCase();
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setIsDepositReq(boolean isDepositReq) {
        this.isDepositReq = isDepositReq;
    }

    public boolean getIsDepositReq() {
        return isDepositReq;
    }

    //methods to be added
    public double calcDeposit() {
        double depositAmount;

        if (isDepositReq) {
            depositAmount = sellingPrice * 0.1;  //10%
        } else {
            depositAmount = 0;   // 0%
        }

        return depositAmount;
    }

    public double getLoan() {
        double loanAmount;
        loanAmount = sellingPrice - calcDeposit();   // variance of SP and deposit
        return loanAmount;
    }

    public double calcMonthlyInstalment() {
        double repayment;
        double interestRate = (10.5 / 100.0) / 12.0;  // divide by 100 and 12 for months of year
        int termOfPayment = 20 * 12;   // convert to months

        repayment = (getLoan() * interestRate) / (1 - Math.pow(1 + interestRate, -termOfPayment));

        return repayment;
    }

    public double totPayment() {
        return calcMonthlyInstalment() * 12 * 20;
    }

    public double totInterest() {
        return totPayment() - getLoan();  // total interest = total amount - total loan
    }

    public double calc_Commission() {
        return sellingPrice * 0.05;  // 5%
    }

    public String toString() {
        return aCust + "\t" + pRefNo + "\t" + propType + "\t" + area + "\tR " + String.format("%.2f", sellingPrice) + "\tR " + String.format("%.2f", calcDeposit()) + "\tR " + String.format("%.2f", getLoan()) + "\tR " + String.format("%.2f", calcMonthlyInstalment()) + "\tR " + String.format("%.2f", totPayment()) + "\tR " + String.format("%.2f", totInterest()) + "\t" + agency + "\t" + agentname;
    }

    // DA METHODS
    public void initialise() throws DataStorageException {
        PropertyDA.initialise();
    }

    //addprop
    public void AddProp(Property objProperty1) throws DuplicateException {
        PropertyDA.AddProp(objProperty1);
    }

    // findprop
    public Property findSoldProp(String pRefNo) throws NotFoundException {
        return PropertyDA.findSoldProp(pRefNo);
    }

    //update agent name
    public void updateAgentName(Property objProperty, String newAgentName) throws NotFoundException {

        PropertyDA.updateAgentName(objProperty, newAgentName);

    }

    // count topwnhouses
    public int countTownhouses() {
        return PropertyDA.countTownhouses();
    }

    // delete
    public void deleteSoldProp(Property objProperty) throws NotFoundException {
        PropertyDA.deleteSoldProp(objProperty);
    }

    // get all
    public ArrayList<Property> getAll() {
        return PropertyDA.getAll();
    }

    // terminate methods
    public void terminate() throws DataStorageException {
        PropertyDA.terminate();
    }

}
