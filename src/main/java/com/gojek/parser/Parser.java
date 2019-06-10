package com.gojek.parser;

import com.gojek.service.ParkingService;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Parser {

    private InputCommandParser parser;
    static ParkingService parkingService;
    private Method method;
    private BufferedReader bufferedReader;

    public Parser(){
        parser = new InputCommandParser();
        parkingService = new ParkingService();
    }

    /**
     * Function to be called in case of Interactive mode
     * @param text
     */
    public void parseText(String text){
        String[] textData = text.split(" ");

        if(textData.length < 1 || textData.length > 3){
            // Blank input | Invalid Input
            System.out.println("Invalid Input");
        }
        else if(textData.length == 1){
            // Status
            try{
                method = parser.mapParser.get(textData[0]);
                if(method != null){
                    method.invoke(parkingService);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        else if(textData.length == 2){
            // Leave | registration_numbers_for_cars_with_colour | slot_numbers_for_cars_with_colour | slot_number_for_registration_number
            try{
                method = parser.mapParser.get(textData[0]);
                if(method != null){
                    method.invoke(parkingService, textData[1]);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        else if(textData.length == 3){
            // park
            try{
                method = parser.mapParser.get(textData[0]);
                if(method != null){
                    method.invoke(parkingService, textData[1], textData[2]);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Function to be called in case of File mode
     * @param filePath
     */
    public void parseFile(String filePath){
        File file = new File(filePath);
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String input;
            try {
                while ((input = bufferedReader.readLine()) != null) {
                    parseText(input.trim());
                }
            } catch (IOException ex) {
                System.out.println("Error in reading the input file.");
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found in the path specified.");
            e.printStackTrace();
        }
    }

}
