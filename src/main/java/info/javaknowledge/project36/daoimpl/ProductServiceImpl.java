/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.daoimpl;

import com.google.gson.Gson;
import info.javaknowledge.project36.dao.ProductService;
import info.javaknowledge.project36.model.Product;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public class ProductServiceImpl implements ProductService{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String insertProduct(Product pm) {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        s.save(pm);
        t.commit();
        s.close();
        return null;
    }

    @Override
    public String updateProduct(int product_id, Product pm) {
       Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        Product oneproductobject = (Product) s.get(Product.class, product_id);
        oneproductobject.setCatid(pm.getCatid());
        oneproductobject.setSupplierid(pm.getSupplierid());
        oneproductobject.setProductname(pm.getProductname());
        oneproductobject.setProductprice(pm.getProductprice());
        oneproductobject.setProductquantity(pm.getProductquantity());
        oneproductobject.setPurchasedate(pm.getPurchasedate());
        oneproductobject.setSaleprice(pm.getSaleprice());
        s.update(oneproductobject);
        t.commit();
        s.close();
        return null;
    }

    @Override
    public String deleteProduct(int product_id) {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        Product pm = (Product) s.get(Product.class, product_id);
        s.delete(pm);
        t.commit();
        s.close();
        return null;
    }

    @Override
    public String viewProduct() {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        List<Product> productslist = s.createQuery("from Product").list();
        Gson g = new Gson();
        String productslistgson = g.toJson(productslist);
        t.commit();
        s.close();
        return productslistgson;
    }

    @Override
    public Product viewOneProduct(int product_id) {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        Product pm = (Product) s.get(Product.class, product_id);
//        Criteria criteria = s.createCriteria(Product.class).add(Restrictions.eq("productname", "A"));
//        Product p = null;
//        Object result = criteria.uniqueResult();
//            if (result != null) {
//                p = (Product) criteria.uniqueResult();
//                System.out.println("Genre = " + p.getProductname());
//            }
        t.commit();
        s.close();
        return pm;
    }
    
}
