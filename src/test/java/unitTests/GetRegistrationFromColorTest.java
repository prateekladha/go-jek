package unitTests;

import com.gojek.parkingLot.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetRegistrationFromColorTest {

    private ParkingLot parkingLot;

    @Before
    public void setup(){
        parkingLot = new ParkingLot();
    }

    @Test
    public void test_LotNotInitialized(){
        assertEquals(parkingLot.getRegistrationNumbersFromColor("White"), null);
    }

    @Test
    public void test_NoCarParked(){
        parkingLot.createParkingLot(10);
        assertEquals(parkingLot.getRegistrationNumbersFromColor("White"),null);
    }

    @Test
    public void test_CarParked_SameColor(){
        parkingLot.createParkingLot(10);
        parkingLot.park("Reg No", "White");
        assertEquals(parkingLot.getRegistrationNumbersFromColor("White").size(),1);
    }

    @Test
    public void test_CarParked_DiffColor(){
        parkingLot.createParkingLot(10);
        parkingLot.park("Reg No", "White");
        assertEquals(parkingLot.getRegistrationNumbersFromColor("Black"),null);
    }
}
