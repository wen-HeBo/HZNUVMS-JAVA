package com.hznuvms.util;

import com.hznuvms.entity.RecordVolunteerhours;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PoiUtils {

    //这是解析上传的Excel文件为对象集合，从而批量添加数据的方法
    public static List<RecordVolunteerhours> parseFileList(MultipartFile file) throws IOException {
        List<RecordVolunteerhours> recordVolunteerhours=new ArrayList<>();
        String FileName = file.getOriginalFilename();
        String FileType = FileName.substring(FileName.lastIndexOf("."));

        if (FileType.equals(".xlsx")){
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(0);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();//获取表单所有的行

            for (int i = 1; i < physicalNumberOfRows; i++) {
                XSSFRow row = sheet.getRow(i);
                RecordVolunteerhours h1=new RecordVolunteerhours();

                XSSFCell c0 = row.getCell(0);
                h1.setAId((int) c0.getNumericCellValue());

                XSSFCell c2 = row.getCell(2);
                h1.setVStudentid(c2.getStringCellValue());

                XSSFCell c4 = row.getCell(4);
                h1.setVHours((float) c4.getNumericCellValue());

                XSSFCell c5 = row.getCell(5);
                h1.setVTime( LocalDate.parse(c5.getStringCellValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));


                recordVolunteerhours.add(h1);
            }
        }
        else if (FileType.equals(".xls")) {
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();//获取表单所有的行

            for (int i = 1; i < physicalNumberOfRows; i++) {
                HSSFRow row = sheet.getRow(i);
                RecordVolunteerhours h1=new RecordVolunteerhours();

                HSSFCell c0 = row.getCell(0);
                h1.setAId((int) c0.getNumericCellValue());

                HSSFCell c2 = row.getCell(2);
                h1.setVStudentid(c2.getStringCellValue());

                HSSFCell c4 = row.getCell(4);
                h1.setVHours((float) c4.getNumericCellValue());

                HSSFCell c5 = row.getCell(5);
                h1.setVTime( LocalDate.parse(c5.getStringCellValue(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

                recordVolunteerhours.add(h1);
            }
        }


        return recordVolunteerhours;
    }
}
