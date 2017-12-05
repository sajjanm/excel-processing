/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.resources;

import com.sajjanm.resttry1.Service.ExcelFileDetailService;
import com.sajjanm.resttry1.entity.ExcelFileDetail;
import com.sajjanm.resttry1.request.SearchRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author Sajjan Mishra
 */
@Path("/excel")
public class UploadExcel {

    @Inject
    private ExcelFileDetailService excelFileDetailService;

    private static Row row;

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response test() {
        System.out.println("test::::::::::::::;");
        String output = "<h1>Hello World!<h1>"
                + "<p>RESTful Service is running ... <br>Ping @ </p<br>";

        return Response.status(200).entity(output).build();
    }

    @POST
    @Path("/UPLOAD")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadExcel(
            @FormDataParam("file") InputStream inputStream,
            @FormDataParam("file") FormDataContentDisposition contentDisposition) {

        List<ExcelFileDetail> excelFileDetailList = new ArrayList<>();

        try {

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = null;

            for (Sheet s : workbook) {
                if (s.getSheetName().equalsIgnoreCase("STDPRICE_FULL Sample")) {
                    sheet = s;
                }
            }

            if (sheet == null) {
                return Response.status(200).entity("The title of sheet was not matched").build();
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
            inputStream.close();

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (Exception e) {
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
            }
        }
        String output;
        if (excelFileDetailService.save(excelFileDetailList) == 0) {
//            output = "total size was : " + excelFileDetailList.size();
            return Response.ok(excelFileDetailService.getPopularProduct().getProducts().size()).build();
        } else {
            output = "Something went wrong. Please try again later!";
        }

        return Response.status(200).entity(output).build();
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

    @GET
    @Path("/SEARCH")
    public Response ManageSearch(@DefaultValue(value = "") @QueryParam("search") String partDesc,
            @DefaultValue(value = "") @QueryParam("search") String partNumber,
            @DefaultValue(value = "") @QueryParam("price") String price) {
        return Response.ok(excelFileDetailService.manageSearch(partDesc, partNumber, price)).build();
    }

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/SEARCH/searchProduct")
    @POST
    public Response searchProduct(SearchRequest request) {
        System.out.println("search value is  " + request.getSearchValue());
        return Response.ok(excelFileDetailService.searchProduct(request.getSearchValue())).build();
    }

    @Path("/SEARCH/getPopularProducts")
    @POST
    public Response getPopularProduct() {
        return Response.ok(excelFileDetailService.getPopularProduct()).build();
    }
}
