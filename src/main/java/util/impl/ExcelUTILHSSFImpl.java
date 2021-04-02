package util.impl;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import util.ExcelUTIL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ExcelUTILHSSFImpl implements ExcelUTIL {

    private InputStream inputStream;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCell cell;


    private void initExcel(String excelFilePath) {
        try {
            this.inputStream = new FileInputStream(excelFilePath);
            this.workbook = new HSSFWorkbook(inputStream);
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
     * 获取第一个工作表的总行数
     *
     * @return -
     */
    private int getRowCount() {
        sheet = workbook.getSheetAt(0);
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

    /**
     * 获取第一个工作表的总列数
     *
     * @return -
     */
    private int getColumnCount() {
        int max = -1;
        sheet = workbook.getSheetAt(0);
        for (int i = 0; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            if (row != null && row.getPhysicalNumberOfCells() > max) {
                max = row.getPhysicalNumberOfCells();
            }
        }
        return max;
    }

    @Override
    public ArrayList<String> getWorksheetNames(String filePath) {
        initExcel(filePath);
        ArrayList<String> sheets = new ArrayList<String>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheets.add(workbook.getSheetName(i));
        }
        sheet = workbook.getSheetAt(1);
        String name = sheet.getSheetName();
        closeExcel();
        return sheets;
    }

    @Override
    public void exportValueToExcel(ArrayList<ArrayList<String>> value, String filePath, String sheetName) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        for (int i = 0; i < value.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            for (int j = 0; j < value.get(0).size(); j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(value.get(i).get(j));
            }
        }
        try {
            FileOutputStream newFile = new FileOutputStream(filePath);
            wb.write(newFile);
            newFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<ArrayList<String>> getBlockContent(String filePath, int startLine, int startColumn) {
        initExcel(filePath);
        ArrayList<ArrayList<String>> componentsBlock = new ArrayList<ArrayList<String>>();
        //默认第一个工作表
        sheet = workbook.getSheetAt(0);
        int endLine = getRowCount();
        int endColumn = getColumnCount();
        for (int i = startLine - 1; i < endLine; i++) {
            row = sheet.getRow(i);
            ArrayList<String> componentsRow = new ArrayList<String>();
            for (int j = startColumn - 1; j < endColumn; j++) {
                try {
                    if (row != null)
                        cell = row.getCell(j);
                    else
                        cell = null;
                    // Assign value
                    if (cell == null)
                        componentsRow.add("");
                    else
                        componentsRow.add(cell.toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            componentsBlock.add(componentsRow);
        }
        closeExcel();
        return componentsBlock;
    }

    @Override
    public ArrayList<String> getColumnContent(String filePath, int column) {
        initExcel(filePath);
        ArrayList<String> componentsBlock = new ArrayList<String>();
        sheet = workbook.getSheetAt(0);
        for (int i = 0; i < getRowCount(); i++) {
            row = sheet.getRow(i);
            try {
                int columnID = column - 1;
                cell = row.getCell((short) columnID);
                if (cell == null) {
                    componentsBlock.add("");
                } else {
                    String oneCell = cell.toString();
                    componentsBlock.add(oneCell);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        closeExcel();
        return componentsBlock;
    }

    // Get excel content by index row
    @Override
    public ArrayList<String> getRowContent(String filePath, int rowNumber) {
        initExcel(filePath);
        ArrayList<String> componentsBlock = new ArrayList<String>();
        sheet = workbook.getSheetAt(0);
        for (int i = 0; i < getColumnCount(); i++) {
            row = sheet.getRow(rowNumber - 1);
            try {
                cell = row.getCell((short) i);
                if (cell == null) {
                    componentsBlock.add("");
                } else {
                    String oneCell = cell.toString();
                    componentsBlock.add(oneCell);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        closeExcel();
        return componentsBlock;
    }

    // Get the format style of one cell
    @Override
    public String getCellContent(String filePath, int rowIndex, int colIndex) {
        initExcel(filePath);
        String cellContent;
        sheet = workbook.getSheetAt(0);
        row = sheet.getRow(rowIndex - 1);
        cell = row.getCell(colIndex - 1);
        cellContent = cell.toString();
        closeExcel();
        return cellContent;
    }

}
