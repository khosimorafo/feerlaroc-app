package co.moonmonkeylabs.quantum.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 2016/01/28.
 */
public class DateService {

    SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");

    public DateService(){}

    public DateService(SimpleDateFormat format){
        this.format = format;
    }

    public String getDate(String str_date){
        try {
            Date _date = format.parse(str_date);
            return format.format(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDate(Date date){
        return  format.format(date);
    }

    public String getCurrentDate(){
        return format.format(new Date());
    }

}
