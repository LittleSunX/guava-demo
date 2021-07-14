package com.guava.test.controller;

import lombok.extern.slf4j.Slf4j;

/**
 * @author LittleSun
 * @version 1.0
 * @date 2021-07-14 14:10
 */
@Slf4j
public class SwitchTest {
    public static void main(String[] args) {
        method("null");
    }

    public static void method(String param) {
        if (param == null) {
            log.info("参数不能为空");
            return;
        }
        switch (param) {
            // 肯定不是进入这里
            case "sth":
                log.info("it's sth");
                break;
            // 也不是进入这里
            case "null":
                log.info("it's null");
                break;
            // 也不是进入这里
            default:
                log.info("default");
        }
    }

}
