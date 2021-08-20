package com.guava.test;

import com.guava.test.entiey.Apple;
import junit.framework.TestCase;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author LittleSun
 * @version 1.0
 * @date 2021-08-06 14:39
 */
public class StringEscapeUtilsTest {
    List<Apple> apples = Arrays.asList(new Apple(1, "红苹果", 1.0, 2.0),
            new Apple(1, "<a>蓝苹果</a>", 1.0, 2.0),
            new Apple(1, "<a>绿苹果</a>", 1.0, 2.0));

    @Test
    public void testStringEscape() {
        for (Apple apple : apples) {
            //转义html代码
            apple.setName(StringEscapeUtils.escapeHtml4(apple.getName()));
            System.out.println(apple);
        }
        TestCase.assertTrue(true);
    }
}
