package test;



import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;

/**
 * excel 缺失精度
 */
public class Excel {
    private static final String excelName="DoubleWithStringNumberic.xls";
    public static void main(String[] args) throws IOException {
        int double_idx=0;
        int double2String_idx=1;
        int string2double_idx=2;
        Workbook wb=new HSSFWorkbook();
        Sheet sheet=wb.createSheet("DoubleWithStringNumberic");
        Row row=sheet.createRow(1);
        Cell cell=row.createCell(double_idx);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(99.33);

        persistWorkbook(wb);

        travelSheet();

        System.out.println("Type: CELL_TYPE_NUMERIC==>CELL_TYPE_STRING");
        InputStream s=new FileInputStream(excelName);
        wb=new HSSFWorkbook(s);
        sheet=wb.getSheetAt(0);
        row=sheet.getRow(1);
        cell=row.getCell(0);
        Double d=cell.getNumericCellValue();

        cell=row.createCell(double2String_idx);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(String.valueOf(d));

        persistWorkbook(wb);
        s.close();

        travelSheet();

        System.out.println("Type: CELL_TYPE_STRING==>CELL_TYPE_NUMERIC");
        s=new FileInputStream(excelName);
        wb=new HSSFWorkbook(s);
        sheet=wb.getSheetAt(0);
        row=sheet.getRow(1);
        cell=row.getCell(double2String_idx);
        String double2String=cell.getStringCellValue();
        cell=row.createCell(string2double_idx);
        cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        cell.setCellValue(Double.parseDouble(double2String));
        persistWorkbook(wb);
        s.close();

        travelSheet();

    }

    private static void travelSheet() throws IOException {
        Workbook wb;
        Sheet sheet;
        InputStream s=new FileInputStream(excelName);
        wb=new HSSFWorkbook(s);
        sheet=wb.getSheetAt(0);
        for (Row row_temp : sheet) {
            for (Cell cell_temp : row_temp) {
                getCellValue(cell_temp);
            }
        }
        s.close();
    }

    private static void getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                System.out.println("CELL_TYPE_NUMERIC:"+cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                String stringCellValue = cell.getStringCellValue();
                System.out.println("CELL_TYPE_STRING:"+stringCellValue);
                System.out.println("toDouble:"+Double.parseDouble(stringCellValue));
                break;
            default:
                System.out.println("error");
                break;
        }

    }

    private static void persistWorkbook(Workbook wb)
            throws FileNotFoundException, IOException {
        OutputStream stream=new FileOutputStream(excelName);
        wb.write(stream);
        stream.flush();
        stream.close();
    }

}
