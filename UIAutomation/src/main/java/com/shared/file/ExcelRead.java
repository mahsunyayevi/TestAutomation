package com.shared.file;


import com.model.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.Iterator;


public class ExcelRead {
    private  final  String path = System.getProperty("user.dir");
    private  final String FILE_NAME = path + "/documents/users.xls";

    public User getUsersExcelRead() throws IOException {
        int i = 0;
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Workbook workbook = new HSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        User user = new User();

        Iterator<Row> iterator = datatypeSheet.iterator();

        while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                if (currentCell.getCellTypeEnum() == CellType.STRING) {
                    if (currentCell.getStringCellValue().contains("true")) {
                        i = currentCell.getRow().getRowNum();
                        Row row = datatypeSheet.getRow(i);
                        int colnumber = row.getLastCellNum();
                        for (int j = 0; j < colnumber; j++) {
                            Cell cell = row.getCell(j);
                            if (j == 0) {
                                user.setEmail(cell.getStringCellValue());
                            }
                            if (j == 1) {
                                user.setPassword(cell.getStringCellValue());
                            }
                            if (j == 2) {
                                user.setIsActive(Boolean.parseBoolean(cell.getStringCellValue()));
                            }
                        }
                        System.out.println(user.getEmail());
                    }
                }
            }
        }
        return user;
    }

}

