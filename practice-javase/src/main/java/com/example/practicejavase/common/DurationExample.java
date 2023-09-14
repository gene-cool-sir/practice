package com.example.practicejavase.common;


import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.time.LocalDateTime;

public class DurationExample {

    public static void main(String[] args) {
        LocalDate now = org.joda.time.LocalDate.now();
        System.out.println("Current Date: " + now);

        // 日期差
        LocalDate date1 = new org.joda.time.LocalDate(2022, 1, 1);
        LocalDate date2 = new LocalDate(2022, 12, 31);
        Days days = Days.daysBetween(date1, date2);
        System.out.println(days.getDays() + "   " + days.getFieldType().getName());

        //
        LocalDate date = new LocalDate(2022, 5, 20);
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        String formattedDate = formatter.print(date);

        System.out.println("Formatted Date: " + formattedDate);

        Duration duration = Duration.standardHours(2); // 创建一个2小时的持续时间

        System.out.println("持续时间的毫秒数: " + duration.getMillis());
        System.out.println("持续时间的秒数: " + duration.getStandardSeconds());
        System.out.println("持续时间的分钟数: " + duration.getStandardMinutes());
        System.out.println("持续时间的小时数: " + duration.getStandardHours());

        DateTime start = new DateTime(2022, 1, 1, 0, 0, 0);
        DateTime end = new DateTime(2022, 1, 1, 12, 0, 0);

        Duration duration1 = new Duration(start, end);

        System.out.println("持续时间的毫秒数: " + duration1.getMillis());
        System.out.println("持续时间的秒数: " + duration1.getStandardSeconds());
        System.out.println("持续时间的分钟数: " + duration1.getStandardMinutes());
        System.out.println("持续时间的小时数: " + duration1.getStandardHours());

        /**------------------------------java8--------------------------->
         *
         */
        System.out.println("----------------java8----------------------");
        java.time.Duration duration3 = java.time.Duration.ofHours(2); // 创建一个2小时的持续时间

        System.out.println("持续时间的毫秒数: " + duration3.toMillis());
        System.out.println("持续时间的秒数: " + duration3.getSeconds());
        System.out.println("持续时间的分钟数: " + duration3.toMinutes());
        System.out.println("持续时间的小时数: " + duration3.toHours());

        LocalDateTime start1 = LocalDateTime.of(2022, 1, 1, 0, 0, 0);
        LocalDateTime end1 = LocalDateTime.of(2022, 1, 1, 12, 0, 0);

        java.time.Duration duration4 = java.time.Duration.between(start1, end1);

        System.out.println("持续时间的毫秒数: " + duration4.toMillis());
        System.out.println("持续时间的秒数: " + duration4.getSeconds());
        System.out.println("持续时间的分钟数: " + duration4.toMinutes());
        System.out.println("持续时间的小时数: " + duration4.toHours());

        /**-----------------------时区 ------------------------*/
        System.out.println("---------------时区设置-------------");
        DateTimeZone.setDefault(DateTimeZone.UTC); // 设置全局默认时区为 UTC

        DateTime dateTime = new DateTime(); // 使用默认时区创建 DateTime 对象
        System.out.println(dateTime); // 打印 UTC 时间

        DateTimeZone anotherTimeZone = DateTimeZone.forID("America/New_York");
        DateTime dateTimeInAnotherTimeZone = dateTime.withZone(anotherTimeZone); // 转换为另一个时区

        System.out.println(dateTimeInAnotherTimeZone); // 打印转换后的时间
    }
}
