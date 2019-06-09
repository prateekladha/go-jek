package unitTests;

import com.gojek.parkingLot.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CarParkTests {

    private ParkingLot parkingLot;

    @Before
    public void setup(){
        parkingLot = new ParkingLot();
    }

    @Test
    public void test_CreateParking(){
        parkingLot.createParkingLot(10);
        assertEquals(parkingLot.getNUMBEROFLOTS(), 10);
    }

    @Test
    public void test_ParkCar(){
        parkingLot.createParkingLot(10);
        int slotNumber = parkingLot.park("Reg No 1", "White");
        assertEquals(slotNumber, 1);
        assertEquals(parkingLot.getAvailableSlotsCount(), 9);
    }

}
