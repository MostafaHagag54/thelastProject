package com.automation.util;

import com.automation.testBase.TestBase;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils extends TestBase {
    public TestUtils() throws IOException {
        super();
    }
public static String snapShotName;
    public static void takeSnapShot(String name) throws IOException {//MM-dd-hh-mm-ss"
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");

        snapShotName=name+"-"+dateFormat.format(new Date());
        File srcFile=( (TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,new File("snapShots\\"+snapShotName+".png"));

    }
    public static Object[][] getDataFromExcelSheet(String SheetName) throws IOException {


        File file= new File("D:\\example\\info.xlsx");
        FileInputStream fileInputStream= new FileInputStream(file);


        XSSFWorkbook workbook= new XSSFWorkbook(fileInputStream);
        Sheet sheet= workbook.getSheet(SheetName);
        String familyName= sheet.getRow(2).getCell(1).toString();
        int rows= sheet.getLastRowNum();
        int cols= sheet.getRow(0).getLastCellNum();

        Object data[][]= new Object[rows][cols];
        for (int i=0; i<rows; i++)
        {
            for (int k=0; k<cols; k++)
            {
                data[i][k]=sheet.getRow(i).getCell(k).toString();
            }
        }
        System.out.println(data[2][1]);
        workbook.close();
        return data;

    }

}
