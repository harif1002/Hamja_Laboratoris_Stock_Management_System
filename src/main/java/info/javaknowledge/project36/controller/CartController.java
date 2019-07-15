/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.controller;

import com.google.gson.Gson;
import info.javaknowledge.project36.dao.ProductService;
import info.javaknowledge.project36.model.Cart;
import info.javaknowledge.project36.model.Product;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
public class CartController {

    @Autowired
    ProductService productdao;

    @RequestMapping("/showcartpage")
    public ModelAndView showCartPage(HttpSession httpSession) {
        ModelAndView mv = new ModelAndView("cart");
        Gson g = new Gson();
        ArrayList<Cart> c = (ArrayList<Cart>) httpSession.getAttribute("usercart");
        if (c.isEmpty()) {
            httpSession.setAttribute("cartempty", "true");
            //mv.addObject("itemsincart","null");
            return mv;
        } else {
            String cartgson = g.toJson(c);
            mv.addObject("itemsincart", cartgson);
            httpSession.setAttribute("grandtotal", getGrandTotal(c));
            httpSession.setAttribute("grandquantity", getgrandquantity(c));
            httpSession.setAttribute("cartempty", "false");
            return mv;
        }
    }

    @RequestMapping("/addtocart")
    public String addingToCart(@RequestParam("getProductId") int productId, HttpSession session) {

        Product pm = productdao.viewOneProduct(productId);
        session.setAttribute("productid", productId);
        String flag = "not inserted";
        ArrayList<Cart> cartarray = (ArrayList<Cart>) session.getAttribute("usercart");

        if (cartarray.isEmpty()) {
            cartarray.add(new Cart(pm.getProductid(), pm.getProductname(), 1, pm.getSaleprice(), pm.getSaleprice()));
            flag = "inserted";
        } else {
            Iterator<Cart> lit = cartarray.iterator();
            while (lit.hasNext()) {
                Cart d = lit.next();
                if (d.getPid() == productId) {
                    int index = cartarray.indexOf(d);
                    String name = d.getPname();
                    int qty = d.getQty() + 1;
                    double price = d.getPrice();
                    cartarray.remove(index);
                    cartarray.add(index, new Cart(productId, name, qty, price, ((qty) * price)));
                    session.setAttribute("usercart", cartarray);
                    flag = "inserted";
                    break;
                }
            }
            if (flag.equals("not inserted")) {
                cartarray.add(new Cart(pm.getProductid(), pm.getProductname(), 1, pm.getSaleprice(), pm.getSaleprice()));
                session.setAttribute("usercart", cartarray);
            }
        }
        session.setAttribute("grandquantity", getgrandquantity(cartarray));
//        UserCartDetails usercart = new UserCartDetails();
//        Integer intcart = Integer.parseInt(session.getAttribute("Cartid").toString());
//        usercart.setCartid(Integer.parseInt(session.getAttribute("Cartid").toString()));
//        usercart.setProductsincart(cartarray.toString());
//        cdao.insertCartwithProducts(usercart);
        return "redirect:/buyproductpage";
    }

    public int getgrandquantity(ArrayList<Cart> items) {
        int grandquant = 0;
        ListIterator<Cart> itr = items.listIterator();
        while (itr.hasNext()) {
            Cart obj = (Cart) itr.next();
            grandquant = grandquant + obj.getQty();
        }
        return grandquant;
    }

    public double getGrandTotal(ArrayList<Cart> item) {
        double gtotal = 0;
        ListIterator<Cart> itr = item.listIterator();
        while (itr.hasNext()) {
            Cart ob = (Cart) itr.next();
            gtotal = gtotal + (ob.getQty() * ob.getPrice());
        }
        return gtotal;
    }

    @RequestMapping(value = "/editquantity")
    public String editCart(@RequestParam("getproductid") int id, @RequestParam("value") String sign, HttpSession session) {
        ArrayList<Cart> c = (ArrayList<Cart>) session.getAttribute("usercart");
        Iterator<Cart> lit = c.iterator();
        while (lit.hasNext()) {
            Cart d = lit.next();
            int index = c.indexOf(d);
            if (d.getPid() == id) {
                String name = d.getPname();
                int qty = d.getQty();
                double price = d.getPrice();
                c.remove(index);
                if (sign.equals("decrease") && qty > 1) {
                    c.add(index, new Cart(id, name, qty - 1, price, ((qty + 1) * price)));
                } else if (sign.equals("decrease") && qty == 1) {
                    c.add(index, new Cart(id, name, 1, price, price));
                } else if (sign.equals("increase") && qty < 100) {
                    c.add(index, new Cart(id, name, qty + 1, price, ((qty + 1) * price)));
                } else {
                    c.add(index, new Cart(id, name, qty, price, ((qty) * price)));
                }
                break;
            }
        }
        session.setAttribute("grandquantity", getgrandquantity(c));
        session.setAttribute("usercart", c);

        return "redirect:/showcartpage";
    }

    @RequestMapping("/removeproductitem")
    public String removeproductitem(@RequestParam("pid") int productid, HttpSession session) {
        @SuppressWarnings("unchecked")
        ArrayList<Cart> cartitems = (ArrayList<Cart>) session.getAttribute("usercart");
        Iterator<Cart> list = (Iterator<Cart>) cartitems.iterator();
        while (list.hasNext()) {
            Cart c = list.next();
            if (c.getPid() == productid) {
                cartitems.remove(cartitems.indexOf(c));
                break;
            }
        }
        session.setAttribute("grandquantity", getgrandquantity(cartitems));
        session.setAttribute("usercart", cartitems);
        return "redirect:/showcartpage";
    }
}
