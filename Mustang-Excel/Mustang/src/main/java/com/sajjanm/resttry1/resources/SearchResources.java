/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.resources;

import com.sajjanm.ingarm.request.SearchRequestNew;
import com.sajjanm.resttry1.Service.ExcelFileDetailService;
import com.sajjanm.resttry1.Service.SearchRequestService;
import com.sajjanm.resttry1.request.SearchRequest;
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

/**
 *
 * @author sajjan
 */
@Path("/productLookup")
public class SearchResources {

    @Inject
    private ExcelFileDetailService excelFileDetailService;
    
    @Inject
    private SearchRequestService requestService;

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
    
    @POST
    public Response productLookup(SearchRequestNew request){
        return Response.ok(requestService.productLookup(request)).build();
    }
}
