package com.guava.test;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HttpUtils {
    public static void send(String json, String url, String path, String fileName) throws IOException {
        url = url + "?request=" + json;
        URL url1 = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) url1.openConnection();
        connection.setRequestProperty("Content-type", "application; charset=UTF-8");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(50000);
        connection.setRequestMethod("GET");
        connection.connect();
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            InputStream inputStream = connection.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            FileOutputStream outputStream = new FileOutputStream(new File(path, fileName));
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } else {
            System.err.println("发送请求失败");
        }
    }
}
