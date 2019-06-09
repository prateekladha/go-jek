package unitTests;

import com.gojek.constants.LeaveEnum;
import com.gojek.parkingLot.ParkingLot;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LeaveCarParkingTest {

    private ParkingLot parkingLot;

    @Before
    public void setup(){
        parkingLot = new ParkingLot();
    }

    @Test
    public void test_Leave_EmptyLot(){
        parkingLot.createParkingLot(10);
        LeaveEnum leaveEnum = parkingLot.leave(2);
        assertEquals(leaveEnum, LeaveEnum.LEAVE_LOT_EMPTY);
    }

    @Test
    public void test_Leave_FreeSlot(){
        parkingLot.createParkingLot(5);
        parkingLot.park("Reg No 1", "White");
        LeaveEnum leaveEnum = parkingLot.leave(2);
        assertEquals(leaveEnum, LeaveEnum.LEAVE_SLOT_FREE);
    }

    @Test
    public void test_Leave_OccupiedSlot(){
        parkingLot.createParkingLot(5);
        parkingLot.park("Reg No 1", "White");
        assertEquals(parkingLot.leave(1), LeaveEnum.LEAVE_SUCCESS);
    }

}
