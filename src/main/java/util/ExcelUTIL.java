package util;

import java.util.ArrayList;

public interface ExcelUTIL {

    /**
     * 获取excel工作表的名字
     *
     * @param filePath 带文件名和文件后缀的完整路径
     * @return 返回所有名字列表
     */
    ArrayList<String> getWorksheetNames(String filePath);

    /**
     * 将二维列表转换成excel表格数据，并导出文件至 filePath
     *
     * @param value     二维list，value.get(0)表示第一行数据
     * @param filePath  带文件名和文件后缀的完整路径
     * @param sheetName 工作表名
     */
    void exportValueToExcel(ArrayList<ArrayList<String>> value, String filePath, String sheetName);

    /**
     * 获取 从指定开始行和开始列起，到最后一行最后一列 的数据
     * 默认第一个工作表
     *
     * @param filePath    带文件名和文件后缀的完整路径
     * @param startLine   开始行
     * @param startColumn 开始列
     * @return 二维列表数据
     */
    ArrayList<ArrayList<String>> getBlockContent(String filePath, int startLine, int startColumn);

    /**
     * 获取指定列的一列数据
     * 默认第一个工作表
     *
     * @param filePath 带文件名和文件后缀的完整路径
     * @param column   指定第几列，从1开始
     * @return -
     */
    ArrayList<String> getColumnContent(String filePath, int column);

    /**
     * 获取指定行的一行数据
     * 默认第一个工作表
     *
     * @param filePath  带文件名和文件后缀的完整路径
     * @param rowNumber 指定第几行，从1开始
     * @return -
     */
    ArrayList<String> getRowContent(String filePath, int rowNumber);

    /**
     * 获取指定单元格的数据
     * 默认第一个工作表
     *
     * @param filePath 带文件名和文件后缀的完整路径
     * @param rowIndex 指定行，从1开始
     * @param colIndex 指定列，从1开始
     * @return -
     */
    String getCellContent(String filePath, int rowIndex, int colIndex);
}
