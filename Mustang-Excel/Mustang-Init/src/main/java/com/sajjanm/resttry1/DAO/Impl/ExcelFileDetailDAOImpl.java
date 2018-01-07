/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.DAO.Impl;

import com.sajjanm.resttry1.DAO.ExcelFileDetailDAO;
import com.sajjanm.resttry1.entity.ExcelFileDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sajjan
 */
@Stateless
public class ExcelFileDetailDAOImpl implements ExcelFileDetailDAO {

    @PersistenceContext(unitName = "IngramPU")
    private EntityManager em;

    @Override
    public int save(List<ExcelFileDetail> ex) {
        try {
            for(ExcelFileDetail e: ex){
//                em.persist(e);
                ExcelFileDetail find = new ExcelFileDetail();
                find = findByPartNumber(e.getPartNumber());
                if(find!=null){
                    find.setPrice(e.getPrice());
                    em.merge(find);
                } else {
                    em.merge(e);
                }
            }
            System.out.println("Updating Excel File Completed! ");
            return 0;
        } catch (Exception e) {
            System.out.println("Error Saving Excel File Details To Database");
            e.printStackTrace();
            return 1;
        }
    }

    private ExcelFileDetail findByPartNumber(String partNumber){
        try{
            return (ExcelFileDetail) em.createQuery("SELECT EFD from ExcelFileDetail EFD WHERE EFD.vendorPartNumber = :partNumber")
                .setParameter("partNumber", partNumber)
                .getSingleResult();
        } catch(NoResultException e){
//            System.out.println("No result found when searching via part number :::  ::: " +partNumber);
            return null;
        }
        
    }
    
    @Override
    public List<ExcelFileDetail> getAll() {
        return (List<ExcelFileDetail>) em.createQuery("SELECT efd FROM ExcelFileDetail efd").getResultList();
    }

    @Override
    public List<ExcelFileDetail> manageSearch(String partDesc, String partNumber, String price) {
        return (List<ExcelFileDetail>) em.createQuery("SELECT EFD FROM ExcelFileDetail EFD WHERE EFD.partDesc LIKE :partDesc OR EFD.price =:price OR EFD.partNumber =:partNumber")
                .setParameter("partDesc", "%"+partDesc+"%")
                .setParameter("partNumber", partNumber)
                .setParameter("price", price)
                .getResultList();
    }

    @Override
    public List<ExcelFileDetail> popularProducts() {
        return (List<ExcelFileDetail>) em.createQuery("SELECT EFD FROM ExcelFileDetail EFD WHERE EFD.customerPriceChangeFlag =:customerPriceChangeFlag")
                .setParameter("customerPriceChangeFlag", "X")
                .getResultList();
    }
}
