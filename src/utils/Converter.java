/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class Converter {

    static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToDate(String dateStr) {

        try {
            Date date = dateFormatter.parse(dateStr);
        } catch (ParseException ex) {
            Logger.getLogger(Converter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String dateToString(Date date) {
        return dateFormatter.format(date);
    }
}
