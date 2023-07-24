package com.guava.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created Time 2023-03-09
 *
 * @author LittleSun
 * @version 1.0
 */
public class TestData {

    private static final String APP_RESP_BODY = "appRespBody";
    private static final String TOKEN = "token";
    private static final String BIZ_DTO = "bizDTO";
    private static final String IMAGE_STORAGE_MECH = "imageStorageMech";
    private static final String PAGE_INFO_LIST = "pageInfoList";
    private static final String PAGE_DTO = "pageDTO";
    private static final String PAGE_URI = "pageUri";
    private static final String FILE_NAME = "fileName";

    @Test
    public void test() {
        String s1 = "s1";
        String s2 = null;
        String s3 = "";
        String s4 = "2";

        if (isValid(s1, s2, s3, s4)) {
            addToQueueAndPrint("11");
        }
        TestCase.assertTrue(true);
    }

    /**
     * 判断四个字符串是否满足特定条件
     *
     * @param s1 第一个字符串
     * @param s2 第二个字符串
     * @param s3 第三个字符串
     * @param s4 第四个字符串
     * @return 如果满足条件，返回 true；否则，返回 false
     */
    private static boolean isValid(String s1, String s2, String s3, String s4) {
        return Objects.nonNull(s1)
                && Objects.isNull(s2)
                && isNullOrEmpty(s3)
                && !"1".equals(s4);
    }

    /**
     * 检查一个字符串是否为 null 或空字符串
     *
     * @param str 要检查的字符串
     * @return 如果字符串为 null 或空字符串，返回 true；否则，返回 false
     */
    private static boolean isNullOrEmpty(String str) {
        return Objects.isNull(str) || str.isEmpty();
    }

    /**
     * 向队列中添加一个元素并打印队列
     *
     * @param value 要添加到队列中的值
     */
    private static void addToQueueAndPrint(String value) {
        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>();
        queue.add(value);
        System.out.println(queue);
    }

    @Test
    public void test2() {
        String str = "4";
        List<String> validValues = Arrays.asList("1", "2", "3");

        if (validValues.stream().noneMatch(str::equals)) {
            System.out.println("字符串不等于 '1', '2' 或 '3'");
        } else {
            System.out.println("字符串等于 '1', '2' 或 '3'");
        }
        TestCase.assertTrue(true);
    }

    @Test
    public void test3() {
        String str = "1";
        List<String> validValues = Arrays.asList("1", "2", "3");

        if (!validValues.contains(str)) {
            System.out.println("字符串不等于 '1', '2' 或 '3'");
        } else {
            System.out.println("字符串等于 '1', '2' 或 '3'");
        }
        TestCase.assertTrue(true);
    }

    @Test
    public void test4() {
        String str = "12345678901234567890123456789011111111111111111111111111111111111111";
        String paddedStr = leftPadWithZeros(str, 17);
        System.out.println(paddedStr); // 输出 "00123"
        TestCase.assertTrue(true);
    }

    public static String leftPadWithZeros(String input, int totalLength) {
        return String.format("%0" + totalLength + "d", Long.parseLong(input));
    }

    @Test
    public void test5() {
        String reg = "^[A-Z0-9]{15}([A-Z0-9]{3})?$";
        String s1 = "111ZAD111111111111";
        if (!s1.matches(reg)) {
            System.out.println("不符合");
        } else {
            System.out.println("符合");
        }
        TestCase.assertTrue(true);
    }

    //    @Test
//    public Map<String,String> test6(String respStr) {
//        Map<String,String> map =new HashMap<>();
//        JSONObject jsonObject = JSONObject.fromObject(respStr);
//        JSONObject respHeader = JSONObject.fromObject(jsonObject.get("respHeader"));
//        for (Object elem : respHeader.entrySet()) {
//            map.put(((Map.Entry) elem).getKey().toString(),((Map.Entry) elem).getValue().toString());
//        }
//        JSONObject respBody = JSONObject.fromObject(jsonObject.get("respBody"));
//        for (Object elem : respBody.entrySet()) {
//            map.put(((Map.Entry) elem).getKey().toString(),((Map.Entry) elem).getValue().toString());
//        }
//
//        return map;
//    }
    public Map<String, String> test6(String respStr) {
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObject = JSON.parseObject(respStr);
        JSONObject respHeader = jsonObject.getJSONObject("respHeader");
        for (Map.Entry<String, Object> entry : respHeader.entrySet()) {
            map.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
        JSONObject respBody = jsonObject.getJSONObject("respBody");
        for (Map.Entry<String, Object> entry : respBody.entrySet()) {
            map.put(entry.getKey(), String.valueOf(entry.getValue()));
        }

        return map;
    }

    public Map<String, String> getSpecificValues(Map<String, String> map) {
        Map<String, String> result = new HashMap<>();
        String appRespBodyStr = map.get("appRespBody");
        if (appRespBodyStr != null) {
            JSONObject appRespBody = JSON.parseObject(appRespBodyStr);
            String token = appRespBody.get("token").toString();
            JSONObject bizDTO = appRespBody.getJSONObject("bizDTO");
            String imageStorageMech = bizDTO.get("imageStorageMech").toString();
            String pageInfoListStr = appRespBody.getJSONObject("bizDTO").getString("pageInfoList");
            if (pageInfoListStr != null) {
                JSONArray pageInfoList = JSON.parseArray(pageInfoListStr);
                if (pageInfoList.size() > 0) {
                    JSONObject firstPage = pageInfoList.getJSONObject(0);
                    JSONObject pageDTO = firstPage.getJSONObject("pageDTO");
                    result.put("pageUri", pageDTO.getString("pageUri"));
                    result.put("fileName", pageDTO.getString("fileName"));
                }
            }
            result.put("token", token);
            result.put("imageStorageMech", imageStorageMech);
        }
        return result;
    }

    @Test
    public void test7() {
        String jsonStr = "{\n" +
                "  \"respHeader\":{\n" +
                "    \"A1\":\"\",\n" +
                "    \"A2\":\"\",\n" +
                "    \"A3\":\"\",\n" +
                "    \"A4\":\"\"\n" +
                "  },\n" +
                "  \"respBody\":{\n" +
                "    \"errCode\":\"\",\n" +
                "    \"errSysCode\":\"\",\n" +
                "    \"errDeployUnit\":\"\",\n" +
                "    \"errSerNo\":\"\",\n" +
                "    \"errMsg\":\"\",\n" +
                "    \"appRespBody\":{\n" +
                "      \"zpk\": \"string\",\n" +
                "      \"token\": \"string\",\n" +
                "      \"bizDTO\":{\n" +
                "        \"achrSec\":\"string\",\n" +
                "        \"bizMetadata\":\"string\",\n" +
                "        \"createTime\":\"\",\n" +
                "        \"pkuuid\":\"050F47B2A7FB11ECA9331A72A1BE977F\",\n" +
                "        \"security\":\"00\",\n" +
                "        \"uniqMetadata\":\"55682F930B888197969BD164D48E1FB9\",\n" +
                "        \"versionLabel\":\"1.0\",\n" +
                "        \"fileTransferServerIp\":\"string\",\n" +
                "        \"fileTransferServerPort\":\"string\",\n" +
                "        \"filePath\":\"string\",\n" +
                "        \"certNo\":\"110101199006286192\",\n" +
                "        \"certType\":\"01\",\n" +
                "        \"custName\":\"张十三\",\n" +
                "        \"custNo\":\"114485741\",\n" +
                "        \"dataType\":\"0001\",\n" +
                "        \"expireDate\":\"2019-08-01T12:12:12+0000\",\n" +
                "        \"imageLibraryIdent\":\"PI\",\n" +
                "        \"imageStorageMech\":\"00001\",\n" +
                "        \"isCompress\":\"N\",\n" +
                "        \"isCurrentVersion\":\"Y\",\n" +
                "        \"pageInfoList\":[\n" +
                "          {\"pageDTO\":{\n" +
                "            \"docIndex\":1,\n" +
                "            \"fileName\":\"page1.jpg\",\n" +
                "            \"pageFlag\":\"F\",\n" +
                "            \"pageUuid\":\"050F47B2A7FB11ECA9331A72A1BE977F\",\n" +
                "            \"pageIndex\":0,\n" +
                "            \"pageUri\":\"http://xxxx\",\n" +
                "            \"isHavePostil\":\"Y\"\n" +
                "          },\n" +
                "            \"postilDTO\":{\n" +
                "              \"fileName\":\"pos1.jpg\",\n" +
                "              \"remark\":\"hhhh\",\n" +
                "              \"postilUri\":\"http://xxxx\"\n" +
                "            }\n" +
                "          }\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  },\n" +
                "  \"respAttach\":\"\",\n" +
                "  \"respMsgAuth\":\"\"\n" +
                "}";
        Map<String, String> map = test6(jsonStr);
        System.out.println(map);
        Map<String, String> specificValues2 = getSpecificValues2(map);
        System.out.println(specificValues2);
    }

    public Map<String, String> getSpecificValues2(Map<String, String> map) {
        Map<String, String> result = new HashMap<>();

        String appRespBodyStr = map.get(APP_RESP_BODY);
        if (appRespBodyStr != null) {
            JSONObject appRespBody;
            try {
                appRespBody = JSON.parseObject(appRespBodyStr);
            } catch (Exception e) {
                throw new RuntimeException("Error parsing appRespBody: " + e.getMessage());
            }

            String token = appRespBody.getString(TOKEN);
            result.put(TOKEN, token);

            JSONObject bizDTO = appRespBody.getJSONObject(BIZ_DTO);
            if (bizDTO != null) {
                String imageStorageMech = bizDTO.getString(IMAGE_STORAGE_MECH);
                result.put(IMAGE_STORAGE_MECH, imageStorageMech);

                JSONArray pageInfoList = bizDTO.getJSONArray(PAGE_INFO_LIST);
                if (pageInfoList != null && !pageInfoList.isEmpty()) {
                    JSONObject firstPage = pageInfoList.getJSONObject(0);
                    JSONObject pageDTO = firstPage.getJSONObject(PAGE_DTO);
                    result.put(PAGE_URI, pageDTO.getString(PAGE_URI));
                    result.put(FILE_NAME, pageDTO.getString(FILE_NAME));
                }
            }
        }
        return result;
    }

    @Test
    public void test8() {
        File file = new File("C:\\Users\\LittleSun\\Desktop", "Dxiag.txt");
        if (file.exists()) {
            System.out.println("存在文件包含" + file.getName() + "的文件");
        } else {
            System.out.println("文件不存在");
        }

        TestCase.assertTrue(true);
    }
//    @Test
//    public void test9(){
//        List<Predicate> list = new ArrayList<>();
//        if (discountIdList !=null) {
//            IN<String> in = criteriaBuilder.in(root.get("discountId").as(String.class));
//            for (String str: discountIdList){
//                in.value(str);
//            }
//            list.add(in);
//        }
//        TestCase.assertTrue(true);
//    }

}

class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        // 你的文件路径
        String filePath = "localFilePath";
        File file = new File(filePath, "gda.png");

        // 检查文件是否存在
        if (file.exists()) {
            // 设置响应内容类型为下载类型
            response.setContentType("application/octet-stream");
            // 设置下载的文件名
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

            // 通过文件路径获取文件的输入流
            FileInputStream fis = new FileInputStream(file);
            // 获取响应的输出流
            OutputStream os = response.getOutputStream();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }

            fis.close();
            os.close();
        } else {
            // 如果文件不存在，你可以在这里处理
            response.getWriter().write("File not found.");
        }
    }
}

