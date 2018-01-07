/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.DAO;

import com.sajjanm.resttry1.entity.ExcelFileDetail;
import java.util.List;

/**
 *
 * @author sajjan
 */
public interface ExcelFileDetailDAO {

    public int save(List<ExcelFileDetail> ex);

    public List<ExcelFileDetail> getAll();

    public List<ExcelFileDetail> manageSearch(String partDesc, String partNumber, String price);
    
    public List<ExcelFileDetail> popularProducts();

}
