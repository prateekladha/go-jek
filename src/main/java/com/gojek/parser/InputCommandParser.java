package com.gojek.parser;

import com.gojek.parkingLot.ParkingLot;
import com.gojek.service.ParkingService;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class InputCommandParser {

    public Map<String, Method> mapParser;

    public InputCommandParser(){
        mapParser = new HashMap<String, Method>();
        try{
            populateDataInMap();
        }catch (NoSuchMethodException e){
            e.printStackTrace();
        }
    }

    /**
     * Populate all possible commands in map
     * @throws NoSuchMethodException
     */
    private void populateDataInMap() throws NoSuchMethodException {
        mapParser.put("create_parking_lot", ParkingService.class.getMethod("createParkingLot", String.class));
        mapParser.put("park", ParkingService.class.getMethod("park", String.class, String.class));
        mapParser.put("leave", ParkingService.class.getMethod("leave", String.class));
        mapParser.put("status", ParkingService.class.getMethod("status"));
        mapParser.put("registration_numbers_for_cars_with_colour", ParkingService.class.getMethod("getRegistrationNumbersFromColor", String.class));
        mapParser.put("slot_numbers_for_cars_with_colour", ParkingService.class.getMethod("getSlotNumbersFromColor", String.class));
        mapParser.put("slot_number_for_registration_number", ParkingService.class.getMethod("getSlotNumberFromRegistrationNumber", String.class));
    }

}
