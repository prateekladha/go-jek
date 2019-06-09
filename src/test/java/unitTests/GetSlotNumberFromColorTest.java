package unitTests;

import com.gojek.parkingLot.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetSlotNumberFromColorTest {

    private ParkingLot parkingLot;

    @Before
    public void setup(){
        parkingLot = new ParkingLot();
    }

    @Test
    public void test_LotNotInitialized(){
        assertEquals(parkingLot.getSlotNumbersFromColor("White"), null);
    }

    @Test
    public void test_NoCarParked(){
        parkingLot.createParkingLot(10);
        assertEquals(parkingLot.getSlotNumbersFromColor("White").size(),0);
    }

    @Test
    public void test_CarParked_SameColor(){
        parkingLot.createParkingLot(10);
        parkingLot.park("Reg No", "White");
        assertEquals(parkingLot.getSlotNumbersFromColor("White").size(),1);
    }

    @Test
    public void test_CarParked_DiffColor(){
        parkingLot.createParkingLot(10);
        parkingLot.park("Reg No", "White");
        assertEquals(parkingLot.getSlotNumbersFromColor("Black").size(),0);
    }
}
