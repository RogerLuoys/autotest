package connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;


public class ExcelUtil {

    // private static String excelFile = ConfigUtil.getProperty("excelFile1");
    private String excelFileName;
    private InputStream inputStream;
    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private HSSFRow row;
    private HSSFCell cell;

    public ExcelUtil(String excelFileName){
        try {
            this.excelFileName = excelFileName;
            inputStream = new FileInputStream(excelFileName);
            workbook = new HSSFWorkbook(inputStream);
        } catch (Exception e) {
            System.out.println("Can't find the file : "+excelFileName);
        }
    }

    // Get a list of worksheet
    public ArrayList<String> getWorksheetNames() {
        ArrayList<String> sheets = new ArrayList<String>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheets.add(workbook.getSheetName(i));
        }
        return sheets;
    }

    public boolean isSheetExist(String sheetName) {
        boolean aux = false;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (workbook.getSheetName(i).equals(sheetName)) {
                aux = true;
                break;
            }
        }
        return aux;
    }

    public int getSheetCount() {
        return workbook.getNumberOfSheets();
    }

    public String getFileName() {
        return excelFileName;
    }

    public long getFileSize(){
        File f = new File(excelFileName);
        long size = f.length();
        System.out.println("::::::SIZE="+size);
        return size;
    }

    public int getRowCount(String worksheetName) {

        sheet = workbook.getSheet(worksheetName);
		/*if (sheet != null) {
			int lastRowNum = sheet.getLastRowNum();

			// System.out.println("Last row: " + lastRowNum);
			row = sheet.getRow(lastRowNum);
			cell = row.getCell((short) 0);
			while (cell == null) {
				lastRowNum--;
				row = sheet.getRow(lastRowNum);
				cell = row.getCell((short) 0);

			}
			return lastRowNum;
		} else
			return -1;
		 */
        return sheet.getPhysicalNumberOfRows();
    }

    // Get the last row number in target sheet
    public int getMaxRowCount(String worksheetName) {
        sheet = workbook.getSheet(worksheetName);
        return sheet.getLastRowNum()+1;
    }

    public int getColumnCount(String worksheetName) {
        int max = -1;
        sheet = workbook.getSheet(worksheetName);
        int rowCount = getRowCount(worksheetName);
        for (int i=0;i<5 && i<rowCount;i++)
        {
            row = sheet.getRow(i);
            if (row != null && row.getPhysicalNumberOfCells()>max) {
                max=row.getPhysicalNumberOfCells();
            }
        }
        return max;

    }

    // Get the last column number in target sheet
    public int getMaxColumnCount(String worksheetName) {
        int max = -1;
        sheet = workbook.getSheet(worksheetName);
        for (int i=0;i<=sheet.getLastRowNum();i++) {
            row = sheet.getRow(i);
            if (row != null && row.getPhysicalNumberOfCells()>max) {
                max=row.getPhysicalNumberOfCells();
            }
        }
        return max;
    }

    /**
     * Export the value to excel file: Documents\ExportedValues.xls
     * @param value
     */
    @SuppressWarnings("deprecation")
    public void exportValueToExcel(ArrayList<ArrayList<String>> value) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("New Sheet");
        String fileLocal = System.getenv("UserProfile") + "\\My Documents\\ExportedValues.xls";
        for (int i = 0; i < value.size(); i++) {
            HSSFRow row = sheet.createRow((int) i);
            for (int j = 0; j < value.get(0).size(); j++) {
                HSSFCell cell = row.createCell((short) j);
                cell.setCellType(CellType.STRING);
                cell.setCellValue(value.get(i).get(j));
            }
        }
        try {
            FileOutputStream fout = new FileOutputStream(fileLocal);
            wb.write(fout);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<String>> getBlockContent(String worksheetName, int startLine, int startColumn, int endLine, int endColumn) {

        ArrayList<ArrayList<String>> componentsBlock = new ArrayList<ArrayList<String>>();
        sheet = workbook.getSheet(worksheetName);
        int cellCount = getColumnCount(worksheetName);
        for (int i=startLine-1;i<endLine;i++)
        {
            row = sheet.getRow(i);

            ArrayList <String> componentsRow = new ArrayList<String>();
            for(int  j = startColumn ; j <=endColumn && j <= cellCount ; j++ ){
                int columnID = j-1;
                try {
                    if( row != null)
                        cell = row.getCell((short) columnID);
                    else
                        cell = null;

                    if (cell==null) {
                        componentsRow.add("");
                    } else{
                        String oneCell = cell.toString();
                        componentsRow.add(oneCell);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            componentsBlock.add(componentsRow);
        }
        return componentsBlock;
    }

    /**
     *  Description
     *  This method gets sheet value From startLine & startColumn TO max line & max column
     *
     * @param
     */
    public ArrayList<ArrayList<String>> getBlockContent(String workSheetName, int startLine, int startColumn) {

        ArrayList<ArrayList<String>> componentsBlock = new ArrayList<ArrayList<String>>();
        sheet = workbook.getSheet(workSheetName);
        int endLine = getMaxRowCount(workSheetName);
        int endColumn = getMaxColumnCount(workSheetName);
        for (int i = startLine - 1; i < endLine; i++) {
            row = sheet.getRow(i);
            ArrayList <String> componentsRow = new ArrayList<String>();
            for(int  j = startColumn - 1; j < endColumn; j++ ){
                try {
                    if( row != null)
                        cell = row.getCell((short) j);
                    else
                        cell = null;
                    // Assign value
                    if (cell==null)
                        componentsRow.add("");
                    else
                        componentsRow.add(cell.toString());
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            componentsBlock.add(componentsRow);
        }
        return componentsBlock;
    }

    public ArrayList<String> getColumnContent(String worksheetName, int startLine, int endLine, int column) {

        ArrayList<String> componentsBlock = new ArrayList<String>();
        sheet = workbook.getSheet(worksheetName);
        for (int i=startLine-1;i<endLine;i++)
        {
            row = sheet.getRow(i);
            try{
                int columnID = column - 1;
                cell = row.getCell((short) columnID);
                if (cell == null) {
                    componentsBlock.add("");
                } else{
                    String oneCell = cell.toString();
                    componentsBlock.add(oneCell);
                }
            }catch (Exception e) {
                System.out.println(e);
            }
        }
        return componentsBlock;
    }

    // Get excel content by index row
    public ArrayList<String> getRowContent(String worksheetName, int startColumn, int endColumn, int rowNumber) {

        ArrayList<String> componentsBlock = new ArrayList<String>();
        sheet = workbook.getSheet(worksheetName);
        for (int i = startColumn - 1; i < endColumn; i++)
        {
            row = sheet.getRow(rowNumber - 1);
            try{
                cell = row.getCell((short) i);
                if (cell == null) {
                    componentsBlock.add("");
                } else{
                    String oneCell = cell.toString();
                    componentsBlock.add(oneCell);
                }
            }catch (Exception e) {
                System.out.println(e);
            }
        }
        return componentsBlock;
    }

    // Get the format style of one cell
    public int getCellFormat(String workSheetName, int rowIndex, int colIndex){
        sheet = workbook.getSheet(workSheetName);
        row = sheet.getRow(rowIndex-1);
        cell = row.getCell((short) (colIndex-1));
        int cellStyle = cell.getCellStyle().getDataFormat();
        return cellStyle;
    }

    public boolean isSheetProtect(String sheetName){
        sheet = workbook.getSheet(sheetName);
        boolean aux = sheet.getProtect();
        return aux;
    }

    public void closeExcel(){
        try{
            inputStream.close();
            closeExcelProcess();
        } catch (Exception e) {
            System.out.println("Exception e = "+e);
        }
    }

    // Close all task about excel.
    public void closeAllExcel(){
        try{
            inputStream.close();
            Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE /T");
            Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE*32 /T");
            Runtime.getRuntime().exec("taskkill /F /IM XLVIEW.EXE /T");
        } catch (Exception e) {
            System.out.println("Exception e = "+e);
        }
    }

    public void closeStream(){
        try{
            inputStream.close();
        } catch (Exception e) {
            System.out.println("Exception e = "+e);
        }
    }

    public void closeExcelProcess(){
        try {
            Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE /T");
            Runtime.getRuntime().exec("taskkill /F /IM EXCEL.EXE*32 /T");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public ArrayList<String> getCsvContent() {
        ArrayList<String> content = new ArrayList<String>();
        try {
            File csv = new File(excelFileName);
            BufferedReader br = new BufferedReader(new FileReader(csv));

            String line = "";
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");
                while (st.hasMoreTokens()) {
                    content.add(st.nextToken().trim());
                }
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    //	public static void main(String args[]) throws IOException {
    // ExcelUtil.getRowNum("Turnover Sensitive");
    //	}

}
