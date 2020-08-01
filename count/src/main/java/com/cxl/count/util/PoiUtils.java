package com.cxl.count.util;

import com.cxl.count.entry.Entry;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PoiUtils {
    public static ResponseEntity<byte[]> exportExcel(List<Entry> entries){
        HttpHeaders headers=null;
        ByteArrayOutputStream baos=null;
        try {
            //1.创建Excel文档
            HSSFWorkbook workbook=new HSSFWorkbook();
            //创建Excel表单
            HSSFSheet sheet=workbook.createSheet("订单表");
            HSSFCellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
            //创建标题的显示样式
            HSSFCellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //定义列的宽度
            sheet.setColumnWidth(0,10*256);
            sheet.setColumnWidth(1,10*256);
            sheet.setColumnWidth(2,10*256);
            sheet.setColumnWidth(3,10*256);
            sheet.setColumnWidth(4,10*256);
            sheet.setColumnWidth(5,10*256);
            sheet.setColumnWidth(6,10*256);
            sheet.setColumnWidth(7,10*256);
            sheet.setColumnWidth(8,10*256);
            sheet.setColumnWidth(9,10*256);
            //5.设置表头
            HSSFRow headerRow = sheet.createRow(0);
            HSSFCell cell0 = headerRow.createCell(0);
            cell0.setCellValue("型号");
            cell0.setCellStyle(headerStyle);

            HSSFCell cell1 = headerRow.createCell(1);
            cell1.setCellValue("米数");
            cell1.setCellStyle(headerStyle);
            HSSFCell cell2 = headerRow.createCell(2);
            cell2.setCellValue("重量");
            cell2.setCellStyle(headerStyle);
            HSSFCell cell3 = headerRow.createCell(3);
            cell3.setCellValue("单价");
            cell3.setCellStyle(headerStyle);
            HSSFCell cell4 = headerRow.createCell(4);
            cell4.setCellValue("数量");
            cell4.setCellStyle(headerStyle);
            HSSFCell cell5 = headerRow.createCell(5);
            cell5.setCellValue("加工");
            cell5.setCellStyle(headerStyle);
            HSSFCell cell6 = headerRow.createCell(6);
            cell6.setCellValue("材料");
            cell6.setCellStyle(headerStyle);
            HSSFCell cell7 = headerRow.createCell(7);
            cell7.setCellValue("运费");
            cell7.setCellStyle(headerStyle);
            HSSFCell cell8 = headerRow.createCell(8);
            cell8.setCellValue("总重量");
            cell8.setCellStyle(headerStyle);
            HSSFCell cell9 = headerRow.createCell(9);
            cell9.setCellValue("总价");
            cell9.setCellStyle(headerStyle);

            for (int i = 0; i < entries.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                Entry entry=entries.get(i);
                row.createCell(0).setCellValue(entry.getModel());
                row.createCell(1).setCellValue(entry.getMeters());
                row.createCell(2).setCellValue(entry.getWeight());
                row.createCell(3).setCellValue(entry.getMalevolent());
                row.createCell(4).setCellValue(entry.getNumber());
                row.createCell(5).setCellValue(entry.getMachining());
                row.createCell(6).setCellValue(entry.getData());
                row.createCell(7).setCellValue(entry.getFreight());
                row.createCell(8).setCellValue(entry.getTotalWeight());
                row.createCell(9).setCellValue(entry.getTotalPrice());
            }
            headers=new HttpHeaders();
            headers.setContentDispositionFormData("attachment",new String("订单表.xls".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos=new ByteArrayOutputStream();
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(baos.toByteArray(),headers, HttpStatus.CREATED);
    }
}
