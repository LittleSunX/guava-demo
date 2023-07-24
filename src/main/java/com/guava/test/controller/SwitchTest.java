package com.guava.test.controller;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author LittleSun
 * @version 1.0
 * @date 2021-07-14 14:10
 */
@Slf4j
public class SwitchTest {
    public static void main(String[] args) {

        //method("1");
    }


    public void test8(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int x;
        while ((x = inputStream.read()) != -1) {
            os.write(x);
        }
        os.flush();
        String s = new String(os.toByteArray(), StandardCharsets.UTF_8);
        System.out.println(s);
    }

    public void test9(HttpServletRequest request) {
        try {
            InputStream inputStream = request.getInputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            os.flush();
            String s = new String(os.toByteArray(), StandardCharsets.UTF_8);
            System.out.println(s);
        } catch (Throwable e) {
            e.getStackTrace();
        }
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
