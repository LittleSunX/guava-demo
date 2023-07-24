package com.guava.test;


import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created Time 2023-04-06
 *
 * @author LittleSun
 * @version 1.0
 */
public class Test8583 {
    @org.junit.Test
    public void test() throws UnsupportedEncodingException, ParseException {
        String input = "39101234567890SM0200MA30123456789012345678901234567824AA011";

//        // 解析输入字符串
//        JSONObject result = parseInputString(input);
//        // 输出解析后的JSON对象
//        System.out.println(result.toJSONString());
        Map<String, String> map = parseInputString2(input);
        System.out.println(map.get("39"));
        System.out.println(map.get("SM"));
        System.out.println(map.get("MA"));
        System.out.println(map.get("AA"));
        TestCase.assertTrue(true);
    }

    /**
     * 将8583报文转为json对象
     *
     * @param input 8583报文
     */
    private static JSONObject parseInputString(String input) {
        JSONObject jsonObject = new JSONObject();

        int index = 0;
        while (index < input.length()) {
            // 读取key（两个字符）
            String key = input.substring(index, index + 2);
            index += 2;

            // 读取value长度（两个字符）
            int length = Integer.parseInt(input.substring(index, index + 2));
            index += 2;

            // 读取value
            String value = input.substring(index, index + length);
            index += length;

            // 将键值对添加到JSON对象中
            jsonObject.put(key, value);
        }

        return jsonObject;
    }

    /**
     * 将8583报文转为map对象
     *
     * @param input 8583报文
     */
    private static Map<String, String> parseInputString2(String input) {
        Map<String, String> map = new HashMap<>();
        int index = 0;
        while (index < input.length()) {
            // 读取key（两个字符）
            String key = input.substring(index, index + 2);
            index += 2;

            // 读取value长度（两个字符）
            int length = Integer.parseInt(input.substring(index, index + 2));
            index += 2;

            // 读取value
            String value = input.substring(index, index + length);
            index += length;

            // 将键值对添加到map中
            map.put(key, value);
        }

        return map;
    }
}
