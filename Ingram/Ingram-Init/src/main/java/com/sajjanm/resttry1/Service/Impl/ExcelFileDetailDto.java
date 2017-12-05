/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.Service.Impl;

import java.util.Date;

/**
 *
 * @author lifeo
 */
public class ExcelFileDetailDto {
    
    private String partNumber;
    private String partDesc;
    private String price;
    private Date batch;

    public Date getBatch() {
        return batch;
    }

    public void setBatch(Date batch) {
        this.batch = batch;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ExcelFileDetail{" + "partNumber=" + partNumber + ", partDesc=" + partDesc + ", price=" + price + '}';
    }
        
}