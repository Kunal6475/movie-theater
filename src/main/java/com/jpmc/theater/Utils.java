package com.jpmc.theater;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Utils {

    //Object Mapper for serializing POJO objects to json String
    private static final ObjectMapper jsonMapper = new ObjectMapper();


    /***
     * Method to get duration in human readable format
     * @param duration Duration
     * @return Return duration in hour and minutes format
     *         Example: duartion 70 return : 1 hour 10 minutes
     */
    public static String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(hour);

        return String.format("%s hour%s %s minute%s", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    /***
     * Method to surround the string value provided with braces
     * @param value String that needs to be modified
     * @return String value surrounded by braces
     *         Example : John Return: (John)
     */
    public static String surroundWithBraces(String value) {
        return String.format("(%s)", value);
    }

    // (s) postfix should be added to handle plural correctly
    public static String handlePlural(long value) {
        if (value <= 1) {
            return "";
        }
        else {
            return "s";
        }
    }

    /***
     * Returns Json String representation of an object
     * @param obj Object that needs to be represented as json string
     * @return String
     */
    public static String getJsonString(Object obj){
        String jsonString = null;
        try {
           jsonString = jsonMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
