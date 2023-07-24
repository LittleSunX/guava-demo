package com.guava.test;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created Time 2023-03-06
 *
 * @author LittleSun
 * @version 1.0
 */
public class Test8583Message {

    @Test
    public void test() throws Exception {
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
            case "AR":
                len = 2;
                break;
            case "SM":
                len = 2;
                break;
            case "AD":
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
}
