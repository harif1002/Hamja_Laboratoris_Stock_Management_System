/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.dao;

import org.springframework.stereotype.Service;
import info.javaknowledge.project36.model.ProductOrder;
/**
 *
 * @author User
 */
@Service
public interface ViewProductOrderService {
    public String updateProductOrder(int orderid, ProductOrder order);

    public String deleteProductOrder(int orderid);

    public String viewProductOrder();

    public ProductOrder viewOneProductOrder(int orderid);
}
