package common;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

public class FileUtil {

    /**
     * 获取 从指定开始行和开始列起，到最后一行最后一列 的数据
     * 默认第一个工作表
     *
     * @param filePath    带文件名和文件后缀的完整路径
     * @param startLine   开始行，0开始
     * @param startColumn 开始列，0开始
     * @return 二维列表数据
     */
    public static String[][] getExcelContent(String filePath, String sheetName, int startLine, int startColumn) {
        InputStream inputStream;
        HSSFWorkbook workbook;
        HSSFSheet sheet;
        HSSFRow row;
        HSSFCell cell;
        try {
            // 初始化
            inputStream = new FileInputStream(filePath);
            workbook = new HSSFWorkbook(inputStream);
            sheet = sheetName == null ? workbook.getSheetAt(0) : workbook.getSheet(sheetName);
            // 获取行数和第一行的列数
            int endLine = sheet.getLastRowNum() + 1;
            int endColumn = sheet.getRow(0).getPhysicalNumberOfCells();
            String[][] allData = new String[endLine - startLine][endColumn - startColumn];
            // 获取工作表的数据，不一定是第0行
            for (int i = startLine; i < endLine; i++) {
                row = sheet.getRow(i);
                for (int j = startColumn; j < endColumn; j++) {
                    cell = row == null ? null : row.getCell(j);
                    // 数组要从第0行第0列开始
                    allData[i - startLine][j - startColumn] = cell == null ? "" : cell.toString();
                }
            }
            // 关闭资源
            inputStream.close();
            return allData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
