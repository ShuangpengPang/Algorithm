package com.shuangpeng.Problem.p1301_1400;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1360NumberOfDaysBetweenTwoDates（日期之间隔几天）
 * @date 2024/2/27 6:11 PM
 */
public class Problem1360NumberOfDaysBetweenTwoDates {

    public int daysBetweenDates0(String date1, String date2) {
        return Math.abs(getDays(date1) - getDays(date2));
    }

    private int getDays(String date) {
        String[] strs = date.split("-");
        int year = Integer.parseInt(strs[0]) - 1, month = Integer.parseInt(strs[1]);
        int day = 365 * year + year / 4 - year / 100 + year / 400;
        year++;
        boolean leap = year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
        for (int i = 1; i < month; i++) {
            day += i == 2 ? (leap ? 29 : 28) : (i < 8 && (i & 1) == 1 || i >= 8 && (i & 1) == 0 ? 31 : 30);
        }
        return day + Integer.parseInt(strs[2]);
    }

    public int daysBetweenDates(String date1, String date2) {
        LocalDate localDate1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate localDate2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return (int) Math.abs(localDate1.toEpochDay() - localDate2.toEpochDay());
    }
}
