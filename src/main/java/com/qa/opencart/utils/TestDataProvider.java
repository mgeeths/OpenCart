package com.qa.opencart.utils;
import com.qa.opencart.utils.XlsReader;

import java.util.ArrayList;

public class TestDataProvider {
    //class var
    private static XlsReader xlsReader = new XlsReader
            ("C:\\Users\\browse\\Automation\\AutomateOpencart\\src\\resources\\testData\\OpenCart.xlsx");


    public ArrayList<Object[]> getRegistrationData(){
        //First create the array to store all the test data
        ArrayList<Object[]> registerDataArr = new ArrayList<>();

        //create a for loop to iterate through every item in the xlsheet
        for(int rowNum=2; rowNum<=xlsReader.getRowCount("Registeration Data"); rowNum++) {

            //Assign values to the params of this method
            String fName = xlsReader.getCellData("Registeration Data", "Firstname", rowNum);
            String lName = xlsReader.getCellData("Registeration Data", "Lastname", rowNum);
            //String eMail = xlsReader.getCellData("Registeration Data", "Email", rowNum);
            String telephone = xlsReader.getCellData("Registeration Data", "Telephone", rowNum);
            String pwd = xlsReader.getCellData("Registeration Data", "Password", rowNum);
            String subscribe = xlsReader.getCellData("Registeration Data", "Subscribe", rowNum);

            //add all the resp row data to the array created
            registerDataArr.add( new Object[]{fName, lName,telephone, pwd, subscribe});
        }
        return registerDataArr;
        }
    }

