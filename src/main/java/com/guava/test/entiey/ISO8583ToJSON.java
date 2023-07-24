//package com.guava.test.entiey;
//
//
//import com.alibaba.fastjson.JSONObject;
//import org.jpos.iso.ISOException;
//import org.jpos.iso.ISOMsg;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ISO8583ToJSON {
//
//    public static ISOMsg parseISO8583Message(String isoMessage) throws ISOException {
//        ISOMsg isoMsg = new ISOMsg();
//
//        // 解析报文头部
//        int index = 0;
//        String header = isoMessage.substring(index, index + 4);
//        index += 4;
//
//        // 解析报文类型
//        String mti = isoMessage.substring(index, index + 4);
//        isoMsg.setMTI(mti);
//        index += 4;
//
//        // 解析数据元素
//        Map<String, String> fieldMap = new HashMap<>();
//        String remainingMessage = isoMessage.substring(index);
//        while (!remainingMessage.isEmpty()) {
//            remainingMessage = parseTlv(remainingMessage, fieldMap);
//        }
//
//        // 将字段映射到 JSON 对象中
//        JSONObject jsonObject = new JSONObject();
//        for (Map.Entry<String, String> entry : fieldMap.entrySet()) {
//            switch (entry.getKey()) {
//                case "39":
//                    jsonObject.put(entry.getKey(), entry.getValue());
//                    break;
//                case "SM":
//                    jsonObject.put(entry.getKey(), entry.getValue());
//                    break;
//                case "MA":
//                    jsonObject.put(entry.getKey(), entry.getValue());
//                    break;
//            }
//        }
//
//        return jsonObject;
//    }
//
//    private static String parseTlv(String field, Map<String, String> map) {
//        if (field != null && !field.isEmpty()) {
//            // 获取标签
//            String tagName = field.substring(0, 2);
//            int tagLenEndIndex = 2 + getFieldTagLen(tagName);
//
//            // 获取value的字节长度
//            int len = Integer.parseInt(field.substring(2, tagLenEndIndex));
//            String tagValue = field.substring(tagLenEndIndex, tagLenEndIndex + len);
//            map.put(tagName, tagValue);
//
//            // 获取value值的下标
//            int valueExdIndex = Math.min(tagLenEndIndex + tagValue.length(), field.length());
//            return field.substring(valueExdIndex);
//        }
//        return "";
//    }
//
//    private static int getFieldTagLen(String tagName) {
//        int len;
//        switch (tagName) {
//            case "39":
//                len = 2;
//                break;
//            case "SM":
//                len = 2;
//                break;
//            case "MA":
//                len = 2;
//                break;
//            default:
//                len = 2 + Integer.parseInt(tagName.substring(1));
//                break;
//        }
//        return len;
//    }
//    public static JSONObject convertISOMsgToJson(ISOMsg isoMsg) throws ISOException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("mti", isoMsg.getMTI());
//
//        for (int i = 1; i <= isoMsg.getMaxField(); i++) {
//            if (isoMsg.hasField(i)) {
//                jsonObject.put(String.valueOf(i), isoMsg.getString(i));
//            }
//        }
//
//        return jsonObject;
//    }
//}
