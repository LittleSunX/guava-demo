package com.guava.test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created Time 2022-02-16
 *
 * @author LittleSun
 * @version 1.0
 */
public class BuildDataUtil {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\tmp";
        BufferedWriter br = Files.newBufferedWriter(Paths.get(filePath + "\\C09911.26000000.epa"), StandardCharsets.UTF_8);
        int num =112232;
        for (int i = 1; i <= 100; i++) {
            num++;
            br.write(num+"|22|333|344|444|444||2222|||||||||||||");
            br.newLine();
        }
        br.write("Beee|||");
        br.close();
    }
}
