package com.resttest;

import org.apache.commons.collections4.ListUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by Владислав on 16.03.2017.
 */
public class DateTimeTest {

    public static void main(String[] args) throws ParseException {

        //Дата запрашивания задания[Приходит в виде String dd.MM.yyyy HH:mm]
        Date currentDate = new Date();
        //Период активности задания в двух датах
        String activeFrom = "17.03.2017 19:25";
        String activeTo = "18.03.2017 19:19";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        System.out.println("Активен от: " + simpleDateFormat.parse(activeFrom).getTime() + " (мс)");
        System.out.println("Активен от: " + simpleDateFormat.format(simpleDateFormat.parse(activeFrom).getTime()) + " (дата)");
        System.out.println("Активен до: " + simpleDateFormat.parse(activeTo).getTime() + " (мс)");
        System.out.println("Активен от: " + simpleDateFormat.format(simpleDateFormat.parse(activeTo).getTime()) + " (дата)");
        String valueOfCurrentDate = simpleDateFormat.format(currentDate.getTime());
        System.out.println(simpleDateFormat.parse(activeTo).before(simpleDateFormat.parse(valueOfCurrentDate)));
        if(!simpleDateFormat.parse(activeTo).before(simpleDateFormat.parse(valueOfCurrentDate))) {
            System.out.println("Срок активности задания находится в периоде активности");
        } else {
            System.out.println("Срок активности задания не находится в периоде активности");
        }
        //Проверка активности задания

    }



}
