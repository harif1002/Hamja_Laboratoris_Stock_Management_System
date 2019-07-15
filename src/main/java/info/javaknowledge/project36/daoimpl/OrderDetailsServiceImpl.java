/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.daoimpl;

import info.javaknowledge.project36.dao.OrderDetailsService;
import info.javaknowledge.project36.model.OrderDetails;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public String insertOrderDetails(ArrayList<OrderDetails> orderdetails) {
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        for (OrderDetails details : orderdetails) {
            s.save(details);
        }
        t.commit();
        s.close();
        return null;
    }

    @Override
    public String updateOrderDetails(int orderid, OrderDetails orderdetails) {
        return null;
    }

    @Override
    public String deleteOrderDetails(int orderid) {
        return null;
    }

    @Override
    public String viewProductOrder() {
        return null;
    }

    @Override
    public OrderDetails viewOneProductOrder(int orderid) {
        return null;
    }

}
