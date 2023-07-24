package com.guava.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BatchImport {
    public static void main(String[] args) {
        // 指定Excel文件路径
        String filePath = "C:\\Users\\LittleSun\\Desktop\\filed.xlsx";

        try {
            // 打开文件输入流
            FileInputStream fileInputStream = new FileInputStream(filePath);
            // 使用Apache POI库读取Excel文件
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 使用HashSet存储唯一的社会统一服务代码
            Set<String> uniqueCodes = new HashSet<>();
            // 使用ArrayList存储导入失败的社会统一服务代码
            List<String> failedCodes = new ArrayList<>();
            // 从第二行开始读取数据，假设第一行是标题行
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                // 获取当前行
                Row row = sheet.getRow(i);
                Cell cell = row.getCell(0);
                String code;
                // 检查单元格类型并获取相应的值   // 获取当前行的第一个单元格的值（社会统一服务代码）
                if (cell.getCellType() == CellType.STRING) {
                    code = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    code = String.valueOf((long) cell.getNumericCellValue());
                } else {
                    throw new IllegalArgumentException("第1列" + (i + 1) + "行单元格类型无效");
                }
                System.out.println("code: " + code);
                // 检查社会统一服务代码是否唯一
                if (!uniqueCodes.contains(code)) {
                    // 如果代码唯一，将其添加到uniqueCodes集合中
                    uniqueCodes.add(code);
                    // TODO: 在这里执行导入操作，例如将数据存储到数据库中
                } else {
                    // 如果代码重复，将其添加到failedCodes列表中
                    failedCodes.add(code);
                }
            }

            // 检查failedCodes列表是否为空
            if (failedCodes.isEmpty()) {
                // 如果为空，表示全部导入成功
                System.out.println("全部成功");
            } else {
                // 如果列表不为空，将失败的社会统一服务代码转换为逗号分隔的字符串
                String failedCodesString = String.join(", ", failedCodes);
                // 输出导入失败的社会统一服务代码
                System.out.println("导入失败的社会统一服务代码: " + failedCodesString);
            }

            // 关闭工作簿和文件输入流
            workbook.close();
            fileInputStream.close();
        } catch (IOException e) {
            // 如果发生异常，打印异常堆栈信息
            e.printStackTrace();
        }
    }
}
