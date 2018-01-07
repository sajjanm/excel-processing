/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author lifeo
 */
@Entity
@Table(name = "EXCEL_FILE_DETAIL")
public class ExcelFileDetail {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "PART_NUMBER", unique = true)
    private String partNumber;
    
    @Column(name = "PART_DESC")
    private String partDesc;
    
    @Column(name = "PRICE")
    private String price;
    
    @Column(name = "BATCH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date batch;
    
    @Column(name = "CUSTOMER_PRICE_CHANGE_FLAG")
    private String customerPriceChangeFlag;
    
    @Column(name = "VENDOR_PART_NUMBER")
    private String vendorPartNumber;
    
    @Column(name = "LONG_DESCRIPTION")
    private String longDescription;
    
    @Column(name = "VENDOR")
    private String vendor;

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getBatch() {
        return batch;
    }

    public void setBatch(Date batch) {
        this.batch = batch;
    }

    public String getCustomerPriceChangeFlag() {
        return customerPriceChangeFlag;
    }

    public void setCustomerPriceChangeFlag(String customerPriceChangeFlag) {
        this.customerPriceChangeFlag = customerPriceChangeFlag;
    }

    public String getVendorPartNumber() {
        return vendorPartNumber;
    }

    public void setVendorPartNumber(String vendorPartNumber) {
        this.vendorPartNumber = vendorPartNumber;
    }

}
