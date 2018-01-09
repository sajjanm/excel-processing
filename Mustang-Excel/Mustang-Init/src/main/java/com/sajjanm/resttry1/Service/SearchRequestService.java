/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.Service;

import com.sajjanm.ingarm.request.SearchRequestNew;
import com.sajjanm.ingarm.response.SearchProductResponse;

/**
 *
 * @author sajjan
 */
public interface SearchRequestService {

    public SearchProductResponse productLookup(SearchRequestNew searchRequest);
    
}
