package com.brillio.unified_portal_onboarding_updated.service;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelGenerator {

    public void generateExcel(List<String> springBootDependencies, List<String> reactDependencies, String outputFilePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Dependencies");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Spring Boot Dependencies");
        headerRow.createCell(1).setCellValue("React Dependencies");

        // Fill dependencies in rows
        int rowIndex = 1;
        for (int i = 0; i < Math.max(springBootDependencies.size(), reactDependencies.size()); i++) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(i < springBootDependencies.size() ? springBootDependencies.get(i) : "N/A");
            row.createCell(1).setCellValue(i < reactDependencies.size() ? reactDependencies.get(i) : "N/A");
        }

        // Write the output to a file
        try (FileOutputStream fileOut = new FileOutputStream(outputFilePath)) {
            workbook.write(fileOut);
        } finally {
            workbook.close();
        }
    }
}
