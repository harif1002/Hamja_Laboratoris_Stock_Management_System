/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.daoimpl;
import com.google.gson.Gson;
import info.javaknowledge.project36.dao.ViewProductOrderService;
import info.javaknowledge.project36.model.Category;
import info.javaknowledge.project36.model.OrderDetails;
import info.javaknowledge.project36.model.ProductOrder;
import java.util.ArrayList;
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
public class ViewProductOrderServiceImpl implements ViewProductOrderService{
    
    @Autowired
    SessionFactory sessionFactory;

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
        Session s = sessionFactory.openSession();
        Transaction t = s.getTransaction();
        t.begin();
        List<ProductOrder> orderList = s.createQuery("from ProductOrder order by orderid").list();
        List<OrderDetails> orderDetailList = s.createQuery("from OrderDetails order by orderid").list();
        //adding blank orderdetails
        for (int i = 0; i < orderList.size(); i++) {
            orderList.get(i).setOrderDetails(new ArrayList<OrderDetails>());
        }
        List<Integer> parentId = new ArrayList<>();
        List<Integer> childId = new ArrayList<>();
        //list all orderid from order
        for (int i = 0; i < orderList.size(); i++) {
            parentId.add(orderList.get(i).getOrderid());            
        }
        //list all orderid from orderdetails
        for (int i = 0; i < orderDetailList.size(); i++) {
            childId.add(orderDetailList.get(i).getOrderid());            
        }
        //List<OrderDetails> subchild;
        try {
            int index=0;
            for (Integer i : childId) {
            if (parentId.contains(i)) {
                //subchild = new ArrayList<>();
                System.out.println(parentId.indexOf(i)+" Match Found " + i);
                //subchild.add(orderDetailList.get(index));                
                orderList.get(parentId.indexOf(i)).getOrderDetails().add(orderDetailList.get(index));
                index++;
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //orderList.addAll(orderDetailList);
        //for (int i = 0; i < orderDetailList.size(); i++) {
            //if(orderList.get(i).getOrderid()!=0)
            //if(orderDetailList.get(i).getOrderid()==orderList.get(i).getOrderid()){
            //orderList.get(0).setOrderDetails(orderDetailList);
            //}            
        //}
        Gson g = new Gson();
        String orderListgson = g.toJson(orderList);
        t.commit();
        s.close();
        System.out.println("-----"+orderListgson);
        return orderListgson;
    }

    @Override
    public ProductOrder viewOneProductOrder(int orderid) {
        return null;
    }
    
}
