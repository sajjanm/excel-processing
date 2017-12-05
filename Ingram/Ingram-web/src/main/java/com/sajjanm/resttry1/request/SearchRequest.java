/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.request;

/**
 *
 * @author Sajjan Mishra
 */
public class SearchRequest {
    
    private String searchValue;
    private String application;
    private String agent;

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    
    
}
