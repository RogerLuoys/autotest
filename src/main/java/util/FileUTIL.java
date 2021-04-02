package util;

public interface FileUTIL {

    /**
     * 在指定目录下创建一个文件，如果目录不存在则会先创建目录
     *
     * @param filePath - 包含完整路径和文件名
     * @return 创建成功返回true，否则返回false
     */
    boolean createFile(String filePath);

    /**
     * 删除指定文件
     *
     * @param filePath - 包含完整路径和文件名
     * @return 创建成功返回true，否则返回false
     */
    boolean deleteFile(String filePath);
}
