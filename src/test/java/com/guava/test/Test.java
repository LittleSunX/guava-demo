package com.guava.test;

import com.guava.test.entiey.Apple;
import com.guava.test.entiey.TestUtil;
import junit.framework.TestCase;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created Time 2022-04-29
 *
 * @author LittleSun
 * @version 1.0
 */
public class Test {
    private List<String> testList;

//    @org.junit.Test
//    public String test(String respCode, String data) {
//        return ("0000".equals(respCode) && data != null) ? data : "";
//    }

//    public String test2(String respCode, Apple apple, Apple2 apple2) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("<a>").append("0000".equals(respCode) && apple.getName() != null ? apple.getName() : "").append("</a>")
//                .append("<b>").append("0000".equals(respCode) && apple.getName1() != null ? apple.getName1() : "").append("</b>")
//                .append("<c>").append("0000".equals(respCode) && apple.getName2() != null ? apple.getName2() : "").append("</c>")
//                .append("<d>").append("0000".equals(respCode) && apple.getName3() != null ? apple.getName3() : "").append("</d>")
//                .append("<e>").append("0000".equals(respCode) && apple.getName4() != null ? apple.getName4() : "").append("</e>")
//                .append("<f>").append("0000".equals(respCode) && apple2.getName1() != null ? apple2.getName1() : "").append("</f>")
//                .append("<g>").append("0000".equals(respCode) && apple2.getName2() != null ? apple2.getName2() : "").append("</g>")
//                .append("<h>").append("0000".equals(respCode) && apple2.getName3() != null ? apple2.getName3() : "").append("</h>")
//                .append("<n>").append("0000".equals(respCode) && apple2.getName4() != null ? apple2.getName4() : "").append("</n>");
//        return builder.toString();
//    }

    @org.junit.Test
    public void test2() throws InvocationTargetException, IllegalAccessException {
        String s1 = "QW111";
        String test48 = TestUtil.test48(s1, "111", "222");
        System.out.println(test48);
        TestCase.assertTrue(true);
    }


    @org.junit.Test
    public void test3() {
        String s1 = "QW111";
        String format = String.format("%-8s", s1);
        System.out.println(format);
        String test48 = TestUtil.test48(format, "111", "222");
        System.out.println(test48);
        TestCase.assertTrue(true);
    }

    @org.junit.Test
    public void test4() {
        String s1 = "QW111";
        String format = String.format("%-8s", s1);
        System.out.println(format);
        String test48 = TestUtil.test48(format, "111", "222");
        System.out.println(test48);
        TestCase.assertTrue(true);
    }

    @org.junit.Test
    public void test5() {
        Apple apple = new Apple();
        apple.setPrice(2.0);
        Apple buildApple = buildApple(apple);
        System.out.println("apple= " + apple);
        System.out.println("buildApple= " + buildApple);
        TestCase.assertTrue(true);
    }

    @org.junit.Test
    public void test6() throws ParseException {
        String dateTimeString = "20230306172722";
        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date dateTime = inputFormatter.parse(dateTimeString);

        SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = outputFormatter.format(dateTime);

        System.out.println(formattedDateTime);
    }


    public void test7(HttpServletRequest request) throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            System.out.println("不符合");
        }
        try {
            InputStream inputStream = request.getInputStream();
            byte[] bytes = new byte[request.getContentLength()];
            int read = inputStream.read(bytes);
            if (contentLength != read) {
                System.out.println("收到和读取到的长度不一致");
            }
            String s = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(s);
        } catch (Throwable e) {
            System.out.println("读取超时");
        }

    }

    private Apple buildApple(Apple apple) {
        Apple buildApple = new Apple();
        buildApple.setPrice(apple.getPrice());
        buildApple.setName("aaa");
        return buildApple;
    }

    @org.junit.Test
    public void test8() throws Exception {

        Map<String, String> map = new HashMap<>();
        String filed58 = "39101234567890SM0200MA301234567890123456789012345678";
        parseTlv(filed58, map);
        System.out.println(map.get("39"));
        System.out.println(map.get("SM"));
        System.out.println(map.get("MA"));
        System.out.println(map);
    }

    private void parseTlv(String field, Map<String, String> map) throws Exception {
        if (field != null && !"".equals(field)) {
            //获取标签
            String tagName = field.substring(0, 2);
            int tagLenEndIndex = 2 + getFieldTagLen(tagName);
            //获取value的字节长度
            int len = Integer.parseInt(field.substring(2, tagLenEndIndex));
            String tagValue = getTagValue(field.substring(tagLenEndIndex), len);
            map.put(tagName, tagValue);
            //获取value值的下标
            int valueExdIndex = Math.min(tagLenEndIndex + tagValue.length(), field.length());
            parseTlv(field.substring(valueExdIndex), map);
        }

    }

    private int getFieldTagLen(String tagName) throws Exception {
        int len;
        switch (tagName) {
            case "39":
                len = 2;
                break;
            case "SM":
                len = 2;
                break;
            case "MA":
                len = 2;
                break;
            default:
                throw new Exception("标签名【" + tagName + "】不存在");
        }
        return len;
    }

    private String getTagValue(String field, int len) throws UnsupportedEncodingException {
        if (len > 0 && len <= field.getBytes("GBK").length) {
            StringBuilder buffer = new StringBuilder();
            char c;
            for (int i = 0; i < len; i++) {
                c = field.charAt(i);
                buffer.append(c);
                if (String.valueOf(c).matches("[\u4e00-\u9fa5]")) {
                    --len;
                }
            }
            return buffer.toString();
        }
        return field;
    }

    @org.junit.Test
    public void test() throws UnsupportedEncodingException, ParseException {
        String dateStr = "";
        if (dateStr == null || dateStr.isEmpty()){
            dateStr ="";
        }else {
            Date date = new SimpleDateFormat("yyyyMMddHHmmss").parse(dateStr);
            dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        }
        System.out.println(dateStr);
        TestCase.assertTrue(true);
    }




}
