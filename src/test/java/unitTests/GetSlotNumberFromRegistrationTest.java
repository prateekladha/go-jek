package unitTests;

import com.gojek.parkingLot.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetSlotNumberFromRegistrationTest {

    private ParkingLot parkingLot;

    @Before
    public void setup(){
        parkingLot = new ParkingLot();
    }

    @Test
    public void test_LotNotInitialized(){
        assertEquals(parkingLot.getSlotNumberFromRegistrationNumber("Reg No"), -1);
    }

    @Test
    public void test_NoCarParked(){
        parkingLot.createParkingLot(10);
        assertEquals(parkingLot.getSlotNumberFromRegistrationNumber("Reg No"), -1);
    }

    @Test
    public void test_CarParked_SameReg(){
        parkingLot.createParkingLot(10);
        parkingLot.park("Reg No", "White");
        assertEquals(parkingLot.getSlotNumberFromRegistrationNumber("Reg No"),1);
    }

    @Test
    public void test_CarParked_DiffReg(){
        parkingLot.createParkingLot(10);
        parkingLot.park("Reg No", "White");
        assertEquals(parkingLot.getSlotNumberFromRegistrationNumber("Reg No 1"),-1);
    }
}
