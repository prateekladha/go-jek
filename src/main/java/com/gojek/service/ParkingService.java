package com.gojek.service;

import com.gojek.constants.GoJekConstants;
import com.gojek.constants.LeaveEnum;
import com.gojek.parkingLot.ParkingLot;
import com.gojek.vehicle.Vehicle;

import java.util.List;
import java.util.Map;

public class ParkingService {

    private ParkingLot parkingLot;

    public ParkingService(){
        parkingLot = new ParkingLot();
    }

    public void createParkingLot(String countLots){
        try {
            int countLotsNumber = Integer.parseInt(countLots);
            if(countLotsNumber > 0) {
                parkingLot.createParkingLot(countLotsNumber);
                System.out.println("Created a parking lot with " + countLots + " slots");
            }
        }catch (Exception e){
            System.out.println(GoJekConstants.INVALID_SLOT_NUMBER);
            e.printStackTrace();
        }

    }

    public void leave(String slot){
        if(parkingLot.isLotInitialized()){
            try {
                LeaveEnum leaveEnum = parkingLot.leave(Integer.parseInt(slot));
                if(leaveEnum == LeaveEnum.LEAVE_SUCCESS){
                    System.out.println("Slot number " + slot + " is free");
                }
                else if(leaveEnum == LeaveEnum.LEAVE_SLOT_FREE){
                    System.out.println(GoJekConstants.NO_CAR_PARKED_SLOT);
                }
                else if(leaveEnum == LeaveEnum.LEAVE_LOT_EMPTY){
                    System.out.println(GoJekConstants.NO_CAR_PARKED);
                }
            }catch (Exception e){
                System.out.println(GoJekConstants.INVALID_SLOT_NUMBER);
                e.printStackTrace();
            }
        }else{
            System.out.println(GoJekConstants.LOT_NOT_INITIALIZED);
        }
    }

    public void getRegistrationNumbersFromColor(String registrationNumber){
        if(parkingLot.isLotInitialized()){
            List registrationList = parkingLot.getRegistrationNumbersFromColor(registrationNumber);
            if(registrationList == null || registrationList.size() == 0){
                System.out.println(GoJekConstants.NOT_FOUND);
            }
            else {
                int index;
                for (index = 0; index < registrationList.size() - 1; index++)
                    System.out.print(registrationList.get(index) + ", ");

                System.out.println(registrationList.get(index));
            }

        }else{
            System.out.println(GoJekConstants.LOT_NOT_INITIALIZED);
        }
    }

    public void park(String registrationNumber, String color){
        if(parkingLot.isLotInitialized()){
            if(parkingLot.getAvailableSlotsCount() == 0){
                System.out.println(GoJekConstants.NO_AVAILABLE_SLOTS);
            }else{
                int slotNumber = parkingLot.park(registrationNumber, color);
                System.out.println("Allocated slot number: " + slotNumber);
            }
        }
        else{
            System.out.println(GoJekConstants.LOT_NOT_INITIALIZED);
        }
    }

    public void status(){
        if(parkingLot.isLotInitialized()){
            Map<Integer, Vehicle> map = parkingLot.status();
            if(map.size() > 0){
                System.out.println("Slot No.\tRegistration No\tColour");

                for (int slot : map.keySet()){
                    Vehicle car = map.get(slot);
                    System.out.println(slot + "\t" + car.getRegistrationNumber() + "\t" + car.getColor());
                }
            }
            else
                System.out.println(GoJekConstants.NO_CAR_PARKED);
        }else{
            System.out.println(GoJekConstants.LOT_NOT_INITIALIZED);
        }
    }

    public void getSlotNumbersFromColor(String color){
        if(parkingLot.isLotInitialized()){
            List slots = parkingLot.getSlotNumbersFromColor(color);
            if(slots == null || slots.size() == 0){
                System.out.println(GoJekConstants.NOT_FOUND);
            }
            else{
               int i = 0;
                for (i = 0; i < slots.size() - 1; i++) {
                    System.out.print(slots.get(i) + ", ");
                }
                System.out.println(slots.get(i));
            }
        }
        else
            System.out.println(GoJekConstants.LOT_NOT_INITIALIZED);
    }

    public void getSlotNumberFromRegistrationNumber(String registrationNumber){
        if(parkingLot.isLotInitialized()){
            int slot = parkingLot.getSlotNumberFromRegistrationNumber(registrationNumber);
            if(slot != -1){
                System.out.println(slot);
            }else{
                System.out.println(GoJekConstants.NOT_FOUND);
            }
        }
        else
            System.out.println(GoJekConstants.LOT_NOT_INITIALIZED);
    }

}
