///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.sajjanm.resttry1.resources;
//
//import com.sajjanm.resttry1.Service.ExcelFileDetailService;
//import com.sajjanm.resttry1.request.SearchRequest;
//import javax.inject.Inject;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
///**
// *
// * @author Sajjan Mishra
// */
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//@Path("/searchProduct")
//public class ExcelRequest {
//
//    @Inject
//    private ExcelFileDetailService excelFileDetailService;
//
//    @GET
//    @Path("/test")
//    public Response test() {
//        System.out.println("test::::::::::::::;");
//        String output = "<h1>Hello World!<h1>"
//                + "<p>RESTful Service is running ... <br>Ping @ </p<br>";
//        return Response.status(200).entity(output).build();
//    }
//
////    @POST
////    public Response searchProduct(SearchRequest request) {
////        System.out.println("search value is  " +request.getSearchValue());
////        return Response.ok(excelFileDetailService.searchProduct(request.getSearchValue())).build();
////    }
//    
//    @GET
//    public Response getPopularProduct(){
//        return null;
//    }
//}
