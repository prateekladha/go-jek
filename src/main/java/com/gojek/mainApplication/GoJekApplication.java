package com.gojek.mainApplication;

import com.gojek.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoJekApplication {

    public static void main(String[] args) {

        Parser parser = new Parser();
        BufferedReader bufferedReader;
        String inputText;

        switch (args.length){
            case 0:

                // Interactive mode
                System.out.println("Enter 'exit' to quit");

                while(true){
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                        inputText = bufferedReader.readLine();
                        if(inputText.equalsIgnoreCase("exit")){
                            break;
                        }
                        else if (inputText.trim().length() == 0 || inputText == null){
                            continue;
                        }
                        else{
                            parser.parseText(inputText.trim());
                        }
                    } catch (IOException e) {
                        System.out.println("Some issue in input string");
                        e.printStackTrace();
                    }
                }

                break;
            case 1:
                // File mode
                parser.parseFile(args[0]);
                break;
            default:
                System.out.println("Invalid input");
        }

    }
}
