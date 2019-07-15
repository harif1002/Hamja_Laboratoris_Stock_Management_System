/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.daoimpl;

import com.google.gson.Gson;
import info.javaknowledge.project36.dao.SupplierService;
import info.javaknowledge.project36.model.Supplier;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public class SupplierServiceImpl implements SupplierService{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String insertSupplier(Supplier sm) {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        s.save(sm);
        t.commit();
        s.close();
        return null;
    }

    @Override
    public String updateSupplier(Supplier sm) {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        s.update(sm);
        t.commit();
        s.close();
        return null;
    }

    @Override
    public String deleteSupplier(String id) {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        Supplier sm = (Supplier) s.get(Supplier.class, id);
        s.delete(sm);
        t.commit();
        s.close();
        return null;
    }

    @Override
    public String viewSupplier() {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        List<Supplier> supplierlist = s.createQuery("from Supplier").list();
        Gson g = new Gson();
        String supplierlistgson = g.toJson(supplierlist);
        t.commit();
        s.close();
        return supplierlistgson;
    }

    @Override
    public Supplier viewOneSupplier(String id) {
       Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        Supplier sm = (Supplier) s.get(Supplier.class, id);
		//Gson gson = new Gson();
        //String onesuppliergsonstring = gson.toJson(sm);
        t.commit();
        s.close();
        return sm;
    }
    
}
