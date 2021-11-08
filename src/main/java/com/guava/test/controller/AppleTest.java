package com.guava.test.controller;

import com.guava.test.entiey.Apple;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;

/**
 * @author LittleSun
 * @version 1.0
 * @date 2021-06-04 9:39
 */
@Slf4j
public class AppleTest {

    public static void main(String[] args) {
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);
        for (Apple apple : apples) {
            apple.setId(apple.getId()+1);
            log.info(apple.toString());
        }

    }

    public static List<Apple> map(List<Integer> list,
                                  IntFunction<Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer e : list) {
            result.add(f.apply(e));
        }
        return result;
    }

}
