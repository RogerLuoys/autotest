package util.impl;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import util.ExcelUTIL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

//未调试好，不可用
public class ExcelUTILXSSFImpl {

    private InputStream inputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;


    private void initExcel(String excelFilePath) {
        try {
//            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(excelFilePath));
            this.inputStream = new FileInputStream(excelFilePath);
            this.workbook = new XSSFWorkbook(inputStream);
        } catch (Exception e) {
            System.out.println("读取文件失败: " + excelFilePath);
        }
    }

    private void closeExcel() {
        try {
            inputStream.close();
        } catch (Exception e) {
            System.out.println("关闭输入流失败 = " + e);
        }
    }

    /**
     * 获取某工作表的总行数
     *
     * @param worksheetName -
     * @return -
     */
    private int getRowCount(String worksheetName) {
        sheet = workbook.getSheet(worksheetName);
        return sheet.getLastRowNum() + 1;
    }

    /**
     * 获取某工作表的总列数
     *
     * @param worksheetName -
     * @return -
     */
    private int getColumnCount(String worksheetName) {
        int max = -1;
        sheet = workbook.getSheet(worksheetName);
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            if (row != null && row.getPhysicalNumberOfCells() > max) {
                max = row.getPhysicalNumberOfCells();
            }
        }
        return max;
    }

}