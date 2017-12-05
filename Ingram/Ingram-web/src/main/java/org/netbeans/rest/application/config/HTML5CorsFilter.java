///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.netbeans.rest.application.config;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author sajjan.mishra
// */
//@WebFilter(filterName = "HTML5CorsFilter", urlPatterns = "/*")
//public class HTML5CorsFilter implements Filter {
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String domainName = request.getRemoteAddr();
//        HttpServletResponse res = (HttpServletResponse) response;
//        //for live server
////        if(domainName.equals("10.42.253.149")){
//        //for local
////        if (domainName.equals("0:0:0:0:0:0:0:1")) {
//            res.addHeader("Access-Control-Allow-Origin", "*");
////        } else {
////            res.addHeader("Access-Control-Allow-Origin", "test");
////        }
//        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
//        res.addHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        chain.doFilter(request, response);
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//}
