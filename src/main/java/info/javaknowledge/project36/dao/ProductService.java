/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.dao;
import info.javaknowledge.project36.model.Product;
import org.springframework.stereotype.Service;
/**
 *
 * @author User
 */
@Service
public interface ProductService {
    public String insertProduct(Product pm);

    public String updateProduct(int product_id, Product pm);

    public String deleteProduct(int product_id);

    public String viewProduct();

    public Product viewOneProduct(int product_id);
}
