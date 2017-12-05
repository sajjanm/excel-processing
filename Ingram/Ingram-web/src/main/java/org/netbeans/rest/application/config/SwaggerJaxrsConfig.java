///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package org.netbeans.rest.application.config;
//
//import com.wordnik.swagger.config.ConfigFactory;
//import com.wordnik.swagger.config.ScannerFactory;
//import com.wordnik.swagger.config.SwaggerConfig;
//import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
//import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
//import com.wordnik.swagger.reader.ClassReaders;
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//
///**
// *
// * @author Sajjan Mishra
// */
//@WebServlet(name = "SwaggerJaxrsConfig", loadOnStartup = 1)
//public class SwaggerJaxrsConfig extends HttpServlet {
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        try {
//            super.init(config);
//            SwaggerConfig swaggerConfig = new SwaggerConfig();
//            ConfigFactory.setConfig(swaggerConfig);
//            swaggerConfig.setBasePath("http://localhost:8080/smartCard-client-web/api");
//            swaggerConfig.setApiVersion("1.0.0");
//            ScannerFactory.setScanner(new DefaultJaxrsScanner());
//            ClassReaders.setReader(new DefaultJaxrsApiReader());
//        } catch (Exception e) {
////            logger.log(Level.SEVERE, "Error on swagger configuration.", e);
//            e.printStackTrace();
//        }
//    }
//
//}
