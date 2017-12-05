/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.ingarm.init.util;

import com.sajjanm.ingarm.response.ProductResponse;
import com.sajjanm.resttry1.entity.ExcelFileDetail;

/**
 *
 * @author Sajjan Mishra
 */
public class EntityToResponseConverter {
    
    public static ProductResponse getProductResponse(ExcelFileDetail excelFileDetail){
        ProductResponse product = new ProductResponse();
        
        product.setProductID(excelFileDetail.getId());
        product.setDescription(excelFileDetail.getPartDesc());
        product.setPartNumber(excelFileDetail.getPartNumber());
        product.setPrice(excelFileDetail.getPrice());
        product.setLongDescription(excelFileDetail.getLongDescription());
        product.setVendor(excelFileDetail.getVendor());
        
        return product;
    }
    
}
