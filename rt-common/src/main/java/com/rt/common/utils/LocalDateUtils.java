package com.rt.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @author sh
 * @version 1.0
 * @date 2019/2/26 11:03
 */
public class LocalDateUtils {

    /**
     * 获取今天的日期
     *
     * @return
     */
    public static LocalDate getToday() {
        return LocalDate.now();
    }

    /**
     * 今天是几号
     *
     * @return
     */
    public static int dayofMonth() {
        LocalDate today = LocalDateUtils.getToday();
        int dayOfMonth = today.getDayOfMonth();
        return dayOfMonth;
    }

    /**
     * 获取day天后的日期
     *
     * @param daysToAdd
     * @return
     */
    public static LocalDate nextDay(int daysToAdd) {
        LocalDate today = LocalDateUtils.getToday();
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate dayOfNext = lastDayOfThisMonth.plusDays(daysToAdd);
        return dayOfNext;
    }

    /**
     * 获取和日期相差天数
     *
     * @param startLocalDate
     * @param endLocalDate
     * @return
     */
    public static long between(LocalDate startLocalDate, LocalDate endLocalDate) {
        return endLocalDate.toEpochDay() - startLocalDate.toEpochDay();
    }

    /**
     * 判断日期是否在当前日期之后
     *
     * @param localDate
     * @return
     */
    public static boolean isLater(LocalDate localDate) {
        return localDate.toEpochDay() - LocalDateUtils.getToday().toEpochDay() > 0 ? true : false;
    }


    /**
     * 格式化日期
     *
     * @param localDate
     * @param pattern
     * @return
     */
    public static String localDateToString(LocalDate localDate, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDate.format(dateTimeFormatter);
    }

    public static void main(String[] args) {
//        LocalDate localDate = nextDay(60);
//        System.out.println(localDate.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
        LocalDate localDate = LocalDate.of(2019, 5, 1);
        boolean before = LocalDateUtils.isLater(localDate);
        System.out.println(before);
    }


}
