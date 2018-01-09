/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.Service.Impl;

import com.sajjanm.ingarm.init.util.EntityToResponseConverter;
import com.sajjanm.ingarm.request.SearchRequestNew;
import com.sajjanm.ingarm.response.ProductResponse;
import com.sajjanm.ingarm.response.SearchProductResponse;
import com.sajjanm.resttry1.DAO.SearchRequestDAO;
import com.sajjanm.resttry1.Service.SearchRequestService;
import com.sajjanm.resttry1.entity.ExcelFileDetail;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author sajjan
 */
@ApplicationScoped
public class SearchRequestServiceImpl implements SearchRequestService {

    @Inject
    private SearchRequestDAO searchRequestDAO;

    @Override
    public SearchProductResponse productLookup(SearchRequestNew searchRequest) {

        List<ExcelFileDetail> efds = null;
        String searchVal = null;

        if (searchRequest.getProductID() != null) {
            // call the getBYID function
            System.out.println("call the getBYID function");
            efds = new ArrayList<>();
            efds.add(searchRequestDAO.getByPartNumber(searchRequest.getProductID()));
            searchVal = searchRequest.getVendor();
        } else if ((searchRequest.getSearchValue() == null || searchRequest.getSearchValue().isEmpty()) && (searchRequest.getVendor() != null || !searchRequest.getVendor().isEmpty())) {
            // call getByVendorFunction
            System.out.println("getByVendorFunction");
            System.out.println("vendor search quote :::: " + searchRequest.getVendor());
            efds = searchRequestDAO.getByVendor(searchRequest);
            searchVal = searchRequest.getVendor();
        } else if ((searchRequest.getSearchValue() != null || !searchRequest.getSearchValue().isEmpty()) && (searchRequest.getVendor() == null || searchRequest.getVendor().isEmpty())) {
            // call normal search function
            System.out.println("normal search function");
            System.out.println("search request was :: " + searchRequest.getSearchValue());
            efds = searchRequestDAO.getBySearchQuery(searchRequest);
            searchVal = searchRequest.getSearchValue();
        } else {
            // call the getPopularProduct function
            System.out.println("getPopularProduct");
            efds = searchRequestDAO.getPopularProducts(searchRequest);
            searchVal = "Popular Products";
        }

        SearchProductResponse searchProductResponses = new SearchProductResponse();
        if (efds.isEmpty() || (efds == null)) {
            searchProductResponses.setMessage("Sorry, No product found!");
            searchProductResponses.setResult("error");
            searchProductResponses.setSearchVal(searchVal);
        } else {
            searchProductResponses.setMessage("Product result list");
            searchProductResponses.setResult("success");
            searchProductResponses.setSearchVal(searchVal);
            List<ProductResponse> productLists = new ArrayList<>();
            for (ExcelFileDetail efd : efds) {
                productLists.add(EntityToResponseConverter.getProductResponse(efd));
            }
            searchProductResponses.setProducts(productLists);
        }

        return searchProductResponses;
    }

}
