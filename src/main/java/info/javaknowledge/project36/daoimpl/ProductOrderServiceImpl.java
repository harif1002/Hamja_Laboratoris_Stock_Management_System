/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.daoimpl;

import info.javaknowledge.project36.dao.ProductOrderService;
import info.javaknowledge.project36.model.ProductOrder;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductOrderServiceImpl implements ProductOrderService{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String insertProductOrder(ProductOrder order) {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        s.save(order);
        t.commit();
        s.close();
        return null;
    }

    @Override
    public String updateProductOrder(int orderid, ProductOrder order) {
        return null;
    }

    @Override
    public String deleteProductOrder(int orderid) {
        return null;
    }

    @Override
    public String viewProductOrder() {
        return null;
    }

    @Override
    public ProductOrder viewOneProductOrder(int orderid) {
        return null;
    }
    
}
