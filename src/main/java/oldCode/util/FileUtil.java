package oldCode.util;

import java.io.File;
import java.io.IOException;

@Deprecated
public class FileUtil {
    private File file = null;

    /**
     * 在指定目录下创建一个文件，如果目录不存在则会先创建目录
     *
     * @param filePath - 包含完整路径和文件名
     * @return 创建成功返回true，否则返回false
     */
    public boolean createFile(String filePath) {
        int nameIndex = filePath.lastIndexOf("\\") + 1;
        String directory = filePath.substring(0, nameIndex);
        String fileName = filePath.substring(nameIndex);
        File dir = new File(directory);
        if (dir.exists() == false) {
            dir.mkdirs(); // the directory is created
        }
        file = new File(directory + "//" + fileName);

        try {
            if (file.exists()) {
                System.out.println("File exists!");
                return false;
            } else {
                file.createNewFile();
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除指定文件
     *
     * @param filePath - 包含完整路径和文件名
     * @return 创建成功返回true，否则返回false
     */
    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
            return true;
        } else {
            return false;
        }

    }
}
