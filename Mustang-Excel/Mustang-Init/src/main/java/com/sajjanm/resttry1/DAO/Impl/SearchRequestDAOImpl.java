/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sajjanm.resttry1.DAO.Impl;

import com.sajjanm.ingarm.request.SearchRequestNew;
import com.sajjanm.resttry1.DAO.SearchRequestDAO;
import com.sajjanm.resttry1.entity.ExcelFileDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sajjan
 */
@Stateless
public class SearchRequestDAOImpl implements SearchRequestDAO {

    @PersistenceContext(unitName = "IngramPU")
    private EntityManager em;

    @Override
    public ExcelFileDetail getByPartNumber(String partNumber) {
        return (ExcelFileDetail) em.createQuery("SELECT EFD FROM ExcelFileDetail AS EFD WHERE EFD.partNumber = :partNumber")
                .setParameter("partNumber", partNumber)
                .getSingleResult();
    }

    @Override
    public List<ExcelFileDetail> getBySearchQuery(SearchRequestNew searchRequest) {

        Query query = em.createQuery("SELECT EFD FROM ExcelFileDetail EFD WHERE EFD.partDesc LIKE :partDesc OR EFD.price =:price OR EFD.partNumber =:partNumber OR EFD.vendor =:vendor");

        query = setPagination(query, searchRequest);

        query.setParameter("partDesc", "%" + searchRequest.getSearchValue() + "%")
                .setParameter("partNumber", searchRequest.getSearchValue())
                .setParameter("price", searchRequest.getSearchValue())
                .setParameter("vendor", searchRequest.getSearchValue());

        System.out.println("search query :::: " + query);

        System.out.println("");
        System.out.println((List<ExcelFileDetail>) query.getResultList());
        System.out.println("");
        
        return (List<ExcelFileDetail>) query.getResultList();

    }

    @Override
    public List<ExcelFileDetail> getByVendor(SearchRequestNew searchRequest) {
        Query query = em.createQuery("SELECT EFD FROM ExcelFileDetail EFD WHERE EFD.vendor =:vendor");

        query = setPagination(query, searchRequest);

        query.setParameter("vendor", searchRequest.getVendor());

        return (List<ExcelFileDetail>) query.getResultList();
    }

    @Override
    public List<ExcelFileDetail> getPopularProducts(SearchRequestNew searchRequest) {
        Query query = em.createQuery("SELECT EFD FROM ExcelFileDetail EFD WHERE EFD.customerPriceChangeFlag =:customerPriceChangeFlag");

        query = setPagination(query, searchRequest);

        query.setParameter("customerPriceChangeFlag", "X");
        return (List<ExcelFileDetail>) query.getResultList();

    }

    private Query setPagination(Query query, SearchRequestNew searchRequest) {

        if (searchRequest.getLimit() == null && searchRequest.getRequestNum() == null) {
            query.setFirstResult(0)
                    .setMaxResults(20);
        } else {
            query.setFirstResult((searchRequest.getRequestNum() - 1) * searchRequest.getLimit())
                    .setMaxResults(searchRequest.getLimit());
        }

        return query;
    }

}
