import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
public class ExcelFileUpdateExample1 {
    public static void main(String[] args) {
        String excelFilePath = "CardHolders.xlsx";
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet1 = workbook.getSheetAt(0);
            Object[][] bookData = {
                    {"The Passionate Programmer", "Chad Fowler", 16},
                    {"Software Craftmanship", "Pete McBreen", 26},
                    {"The Art of Agile Development", "James Shore", 32},
                    {"Continuous Delivery", "Jez Humble", 41},
            };
            int rowCount = sheet1.getLastRowNum();
            for (Object[] aBook : bookData) {
                Row row = sheet1.createRow(++rowCount); 
                int columnCount = 0;
                Cell cell = row.createCell(columnCount); 
                cell.setCellValue(rowCount);
                for (Object field : aBook) {
                    cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }
            }
            inputStream.close();
 
            FileOutputStream outputStream = new FileOutputStream("CardHolders.xlsx");
            System.out.println("Account details added successfully !!");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
}