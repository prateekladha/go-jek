package unitTests;

import com.gojek.parkingLot.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusTests {

    private ParkingLot parkingLot;

    @Before
    public void setup(){
        parkingLot = new ParkingLot();
    }

    @Test
    public void test_LotNotInitalized(){
        assertEquals(parkingLot.status(), null);
    }

    @Test
    public void test_EmptyLot_Status(){
        parkingLot.createParkingLot(10);
        assertEquals(parkingLot.status().size(), 0);
    }

    @Test
    public void test_Status_WithCarParked(){
        parkingLot.createParkingLot(10);
        parkingLot.park("Reg No. 1", "White");
        assertEquals(parkingLot.status().size(), 1);
    }

}
