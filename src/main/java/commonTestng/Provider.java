package commonTestng;

import common.FileUtil;

public class Provider {

    /**
     * 获取excel中，第一个工作表的数据
     *
     * @param filePath excel文件全路径
     * @return 可用于数据驱动
     */
    public static Object[][] getData(String filePath) {
        String[][] data = FileUtil.getExcelContent(filePath, null, 1, 0);
//        for (int i = 0; i < data.length; i++) {
//            if (data[i][0].equalsIgnoreCase("Y")) {
//                data[i] = data[i+1];// ??
//            }
//        }
        return data;
    }


    /**
     * 获取excel中，指定工作表的数据
     *
     * @param filePath excel文件全路径
     * @param env      工作表名
     * @return 可用于数据驱动
     */
    public static String[][] getData(String filePath, String env) {
        return FileUtil.getExcelContent(filePath, env, 1, 0);
    }

}
