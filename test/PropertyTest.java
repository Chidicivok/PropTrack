




import org.junit.Test;
import static org.junit.Assert.*;
import vut.data.Property;

public class PropertyTest {

    @Test
    public void testCalculateDepositWhenDepositRequired() {
        Property property = new Property("P001", "House", "Vereeniging", "John Agent", "Remax", 1000000, true, "Chidi Customer");
        assertEquals(100000, property.calcDeposit(), 0.01);
    }

    @Test
    public void testCalculateDepositWhenDepositNotRequired() {
        Property property = new Property("P002", "Flat", "Vanderbijlpark", "Mary Agent", "Realnet", 800000, false, "Test Customer");
        assertEquals(0, property.calcDeposit(), 0.01);
    }

    @Test
    public void testLoanCalculation() {
        Property property = new Property("P003", "Townhouse", "Sasolburg", "Agent Name", "LeapFrog", 500000, true, "Customer Name");
        assertEquals(450000, property.getLoan(), 0.01);
    }

    @Test
    public void testCommissionCalculation() {
        Property property = new Property("P004", "House", "Johannesburg", "Agent Name", "Remax", 1200000, true, "Customer Name");
        assertEquals(60000, property.calc_Commission(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSellingPriceCannotBeZero() {
        new Property("P005", "House", "Pretoria", "Agent Name", "Remax", 0, true, "Customer Name");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPropertyReferenceCannotBeEmpty() {
        new Property("", "House", "Pretoria", "Agent Name", "Remax", 500000, true, "Customer Name");
    }
}



