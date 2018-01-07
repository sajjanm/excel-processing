/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.DAO;

import com.sajjanm.ingarm.request.SearchRequestNew;
import com.sajjanm.resttry1.entity.ExcelFileDetail;
import java.util.List;

/**
 *
 * @author sajjan
 */
public interface SearchRequestDAO {

    public ExcelFileDetail getByPartNumber(String partNumber);

    public List<ExcelFileDetail> searchQuery(SearchRequestNew searchRequest);
    
    public List<ExcelFileDetail> getByVendor(SearchRequestNew searchRequest);

    public List<ExcelFileDetail> getPopularProducts(SearchRequestNew searchRequest);

}
