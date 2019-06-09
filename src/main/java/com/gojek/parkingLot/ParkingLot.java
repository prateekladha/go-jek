package com.gojek.parkingLot;

import com.gojek.constants.LeaveEnum;
import com.gojek.vehicle.Car;
import com.gojek.vehicle.Vehicle;

import java.util.*;

public class ParkingLot {

    int NUMBER_OF_LOTS = 0;

    List<Integer> availableSlots;
    Map<Integer, Vehicle> slotCarMapping;
    Map<String, Integer> slotRegistrationMapping;
    Map<String, ArrayList<String>> colorRegistrationMapping;

    public void createParkingLot(int countLots){
        this.NUMBER_OF_LOTS = countLots;

        this.availableSlots = new ArrayList<Integer>();
        for (int i = 1; i <= this.NUMBER_OF_LOTS ; i++) {
            availableSlots.add(i);
        }

        this.colorRegistrationMapping = new HashMap<String, ArrayList<String>>();
        this.slotCarMapping = new HashMap<Integer, Vehicle>();
        this.slotRegistrationMapping = new HashMap<String, Integer>();
    }

    public LeaveEnum leave(int slotNumber){
       if(this.slotCarMapping.size() > 0) {

           Vehicle carInfo = slotCarMapping.get(slotNumber);

           if (carInfo != null) {
               slotRegistrationMapping.remove(carInfo.getRegistrationNumber());
               slotCarMapping.remove(slotNumber);

               ArrayList<String> registrationList = colorRegistrationMapping.get(carInfo.getColor());

               if (registrationList.contains(carInfo.getRegistrationNumber())) {
                   registrationList.remove(carInfo.getRegistrationNumber());
               }

               availableSlots.add(slotNumber);
               return LeaveEnum.LEAVE_SUCCESS;
           } else {
               return LeaveEnum.LEAVE_SLOT_FREE;
           }
       }
       else
           return LeaveEnum.LEAVE_LOT_EMPTY;
    }
    public List getRegistrationNumbersFromColor(String color){
        return colorRegistrationMapping.get(color);
    }

    public int park(String registrationNumber, String color){

        Collections.sort(availableSlots);
        int slot = availableSlots.get(0);
        Vehicle newCar = new Car(registrationNumber, color);

        slotCarMapping.put(slot, newCar);
        slotRegistrationMapping.put(registrationNumber,slot);

        if(colorRegistrationMapping.get(color) != null){
            ArrayList<String> registrationNumbers = colorRegistrationMapping.get(color);
            registrationNumbers.add(registrationNumber);
            colorRegistrationMapping.put(color, registrationNumbers);
        }else{
            ArrayList<String> registrationNumbers = new ArrayList<String>();
            registrationNumbers.add(registrationNumber);
            colorRegistrationMapping.put(color, registrationNumbers);
        }

        availableSlots.remove(0);

        return slot;

    }

    public Map status(){
        return slotCarMapping;
    }

    public List getSlotNumbersFromColor(String color){
        List<Integer> slots = new ArrayList<Integer>();

        if(colorRegistrationMapping.get(color) != null){
            ArrayList<String> registrationList = colorRegistrationMapping.get(color);

            for (int i = 0; i < registrationList.size(); i++) {
                slots.add(slotRegistrationMapping.get(registrationList.get(i)));
            }

            Collections.sort(slots);
            return slots;

        }else{
            return slots;
        }
    }

    public Integer getSlotNumberFromRegistrationNumber(String registrationNumber) {
        if(slotRegistrationMapping.get(registrationNumber) != null){
            return slotRegistrationMapping.get(registrationNumber);
        }else{
            return -1;
        }
    }

    public int getNUMBEROFLOTS(){
        return this.NUMBER_OF_LOTS;
    }

    public int getAvailableSlotsCount(){
        return this.availableSlots.size();
    }

    public boolean isLotInitialized(){
        if(this.NUMBER_OF_LOTS == 0){
            return false;
        }else{
            return true;
        }
    }


}
