/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.ingarm.init.util;

//import com.sajjanm.resttry1.Service.ExcelFileDetailService;
//import com.sajjanm.resttry1.entity.ExcelFileDetail;
import com.sajjanm.resttry1.Service.ExcelFileDetailService;
import com.sajjanm.resttry1.entity.ExcelFileDetail;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Sajjan Mishra
 */
@ApplicationScoped
public class ProcessExcel {

    @Inject
    private ExcelFileDetailService excelFileDetailService;

    private static Row row;

    public String process(String fileNamee) {
        List<ExcelFileDetail> excelFileDetailList = new ArrayList<>();

        try {

            FileInputStream excelFile;
            excelFile = new FileInputStream(new File(fileNamee));

            Workbook workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = null;
            for (Sheet s : workbook) {
                if (s.getSheetName().equalsIgnoreCase("STDPRICE_FULL Sample")) {
                    sheet = s;
                }
            }
            if (sheet == null) {
                return "The title of sheet was not matched";
            }

            Iterator< Row> rowIterator = sheet.iterator();
            Map<String, Integer> map = new HashMap<>();

            if (rowIterator.hasNext()) {
                row = rowIterator.next();

                Iterator< Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cellTitle = cellIterator.next();
                    switch (cellTitle.getStringCellValue()) {
                        case "Ingram Part Number":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "Ingram Part Description":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "Retail Price":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "Customer Price Change Flag":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "Vendor Part Number":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "Vendor Name":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                        case "Material Long Description":
                            map.put(cellTitle.getStringCellValue(), cellTitle.getColumnIndex());
                            break;
                    }
                }
            }

            while (rowIterator.hasNext()) {
                ExcelFileDetail excelFileDetail = new ExcelFileDetail();
                row = rowIterator.next();
                Iterator< Cell> cellIterator = row.cellIterator();
                DataFormatter formatter = new DataFormatter(); //creating formatter using the default locale
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    for (Map.Entry<String, Integer> entry : map.entrySet()) {
                        if (cell.getColumnIndex() == entry.getValue()) {
//                            setValueOfClass(excelFileDetail, entry.getKey(), cell.getStringCellValue());
                            setValueOfClass(excelFileDetail, entry.getKey(), (String) formatter.formatCellValue(cell));
                        }
                    }
                }
                excelFileDetail.setBatch(new Date());
                excelFileDetailList.add(excelFileDetail);
            }
            String output;
            excelFile.close();

            System.out.println(excelFileDetailList.size() + " no of records found.");
            if (excelFileDetailService.save(excelFileDetailList) == 0) {
                output = "total size of data was : " + excelFileDetailList.size();
            } else {
                output = "Something went Wrong. Please try again later!";
            }
            return output;

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "ERROR!!!";

    }

    private void setValueOfClass(ExcelFileDetail fileDetail, String fieldName, Object fieldValue) {
        switch (fieldName) {
            case "Ingram Part Number":
                fileDetail.setPartNumber((String) fieldValue);
                break;
            case "Ingram Part Description":
                fileDetail.setPartDesc((String) fieldValue);
                break;
            case "Retail Price":
                fileDetail.setPrice((String) fieldValue);
                break;
            case "Customer Price Change Flag":
                fileDetail.setCustomerPriceChangeFlag((String) fieldValue);
                break;
            case "Vendor Part Number":
                fileDetail.setVendorPartNumber((String) fieldValue);
                break;
            case "Vendor Name":
                fileDetail.setVendor((String) fieldValue);
                break;
            case "Material Long Description":
                fileDetail.setLongDescription((String) fieldValue);
                break;
        }
    }
}
