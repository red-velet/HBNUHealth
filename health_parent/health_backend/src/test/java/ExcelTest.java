import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Author: SayHello
 * @Date: 2023/3/2 13:27
 * @Introduction:
 */
public class ExcelTest {
    @Test
    public void test() throws IOException {
        String filePath = "C:\\Users\\SayHello\\Desktop\\test.xlsx";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        //获取excel文件
        XSSFWorkbook excel = new XSSFWorkbook(fileInputStream);

        //获取文件内的第一个sheet
        XSSFSheet sheet1 = excel.getSheetAt(0);

        //遍历sheet，每个元素都是一行数据
        for (Row row : sheet1) {
            //遍历row，每个元素都是一格数据
            for (Cell cell : row) {
                String value = cell.getStringCellValue();
                System.out.print(value + " ");
            }
            System.out.println("\n行分割线-----------------");
        }

        //关闭资源
        fileInputStream.close();
        excel.close();
    }

    @Test
    public void testExcludeData() throws IOException {
        String filePath = "C:\\Users\\SayHello\\Desktop\\test.xlsx";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        //获取excel文件
        XSSFWorkbook excel = new XSSFWorkbook(fileInputStream);

        //获取文件内的第一个sheet
        XSSFSheet sheet1 = excel.getSheetAt(0);

        //遍历sheet，每个元素都是一行数据
        for (Row row : sheet1) {
            //遍历row，每个元素都是一格数据
            for (Cell cell : row) {
                String value = cell.getStringCellValue();
                System.out.print(value + " ");
            }
            System.out.println("\n行分割线-----------------");
        }

        //关闭资源
        fileInputStream.close();
        excel.close();
    }
}
