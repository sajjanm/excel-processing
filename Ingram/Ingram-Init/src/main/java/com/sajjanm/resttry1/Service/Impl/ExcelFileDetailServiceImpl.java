/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.Service.Impl;

import com.sajjanm.ingarm.init.util.EntityToResponseConverter;
import com.sajjanm.ingarm.response.ProductResponse;
import com.sajjanm.ingarm.response.SearchProductResponse;
import com.sajjanm.resttry1.DAO.ExcelFileDetailDAO;
import com.sajjanm.resttry1.Service.ExcelFileDetailService;
import com.sajjanm.resttry1.entity.ExcelFileDetail;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author lifeo
 */
@ApplicationScoped
public class ExcelFileDetailServiceImpl implements ExcelFileDetailService {

    @Inject
    private ExcelFileDetailDAO excelFileDetailDAO;

    @Override
    public int save(List<ExcelFileDetail> ex) {
//        System.out.println("Inside service impl function for save");
        int insert = getExcelFileDetailDAO().save(ex);
        return insert;
    }

    @Override
    public List<ExcelFileDetail> getAll() {
        return getExcelFileDetailDAO().getAll();
    }

    @Override
    public List<ExcelFileDetail> manageSearch(String partDesc, String partNumber, String price) {
        return getExcelFileDetailDAO().manageSearch(partDesc, partNumber, price);
    }

    @Override
    public SearchProductResponse searchProduct(String searchQuery) {
        List<ExcelFileDetail> efds = getExcelFileDetailDAO().manageSearch(searchQuery, searchQuery, searchQuery);
        SearchProductResponse searchProductResponses = new SearchProductResponse();
        if (efds.isEmpty() || (efds == null)) {
            searchProductResponses.setMessage("Sorry, No product found!");
            searchProductResponses.setResult("error");
            searchProductResponses.setSearchVal(searchQuery);
        } else {
            searchProductResponses.setMessage("Product result list");
            searchProductResponses.setResult("success");
            searchProductResponses.setSearchVal(searchQuery);
            List<ProductResponse> productLists = new ArrayList<>();
            for (ExcelFileDetail efd : efds) {
                productLists.add(EntityToResponseConverter.getProductResponse(efd));
            }
            searchProductResponses.setProducts(productLists);
        }
        return searchProductResponses;
    }

    @Override
    public SearchProductResponse getPopularProduct() {
        List<ExcelFileDetail> efds = getExcelFileDetailDAO().popularProducts();
        SearchProductResponse searchProductResponses = new SearchProductResponse();
        if (efds.isEmpty() || (efds == null)) {
            searchProductResponses.setMessage("Sorry, No product found!");
            searchProductResponses.setResult("error");
            searchProductResponses.setSearchVal("Popular Products");
        } else {
            searchProductResponses.setMessage("Product result list");
            searchProductResponses.setResult("success");
            searchProductResponses.setSearchVal("Popular Products");
            List<ProductResponse> productLists = new ArrayList<>();
            for (ExcelFileDetail efd : efds) {
                productLists.add(EntityToResponseConverter.getProductResponse(efd));
            }
            searchProductResponses.setProducts(productLists);
        }
        return searchProductResponses;

    }

    /**
     * @return the excelFileDetailDAO
     */
    public ExcelFileDetailDAO getExcelFileDetailDAO() {
        return excelFileDetailDAO;
    }

    /**
     * @param excelFileDetailDAO the excelFileDetailDAO to set
     */
    public void setExcelFileDetailDAO(ExcelFileDetailDAO excelFileDetailDAO) {
        this.excelFileDetailDAO = excelFileDetailDAO;
    }

}
