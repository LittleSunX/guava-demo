package com.guava.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created Time 2022-10-26
 *
 * @author LittleSun
 * @version 1.0
 */
public class TestDate {
    public static void main(String[] args) throws ParseException {
//        String start = "20220925";
//        String end = "20220925";
//        List<String> stringList = getBetweenDates(start, end);
//        stringList.stream().distinct().forEach(System.out::println);
//        String str ="1";
//        List<String> strings = Collections.singletonList(str);
//        strings.forEach(System.out::println);
//        System.out.println(strings);
        if (Arrays.asList("1", "2", "3").contains(null)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }

    /**
     * 获取两个日期之间的日期
     *
     * @param start 开始日期
     * @param end   结束日期
     * @return 日期集合
     */
    private static List<String> getBetweenDates(String start, String end) throws ParseException {
        Date startData = new SimpleDateFormat("yyyyMMdd").parse(start); //定义起始日期
        Date endData = new SimpleDateFormat("yyyyMMdd").parse(end); //定义结束日期

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        List<String> buffer = new ArrayList<>();
        buffer.add(dateFormat.format(startData.getTime()));
        Calendar tempStart = Calendar.getInstance();

        tempStart.setTime(startData);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(endData);
        while (tempStart.before(tempEnd)) {
            buffer.add(dateFormat.format(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        buffer.add(dateFormat.format(endData.getTime()));
        return buffer;
    }
}
