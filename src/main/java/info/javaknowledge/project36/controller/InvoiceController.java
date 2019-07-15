package info.javaknowledge.project36.controller;

import com.google.gson.Gson;
import info.javaknowledge.project36.dao.BillingAddressService;
import info.javaknowledge.project36.dao.OrderDetailsService;
import info.javaknowledge.project36.dao.ProductOrderService;
import info.javaknowledge.project36.dao.ProductService;
import info.javaknowledge.project36.model.BillingAddress;
import info.javaknowledge.project36.model.Cart;
import info.javaknowledge.project36.model.OrderDetails;
import info.javaknowledge.project36.model.Product;
import info.javaknowledge.project36.model.ProductOrder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InvoiceController {
@Autowired
    BillingAddressService bdao;
    @Autowired
    ProductOrderService odao;
    @Autowired
    OrderDetailsService odetdao;
    @Autowired
    ProductService pdao;

    @RequestMapping("/generatinginvoice")
    public ModelAndView showingReceipt(@RequestParam("getaddressid") int addressid, HttpSession httpSession) {
        BillingAddress bm = (BillingAddress) bdao.viewOneBillingAddress(addressid);
        ArrayList<Cart> c = (ArrayList<Cart>) httpSession.getAttribute("usercart");
        Gson g = new Gson();
        String cartgson = g.toJson(c);
        String addressgson = g.toJson(bm);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        System.out.println("this is date  " + currentDateTimeString);

        ModelAndView mv = new ModelAndView("invoice");
        mv.addObject("addressobject", addressgson);
        mv.addObject("itemsincart", cartgson);
        mv.addObject("bill", "true");
        httpSession.setAttribute("addressid", addressid);
        httpSession.setAttribute("Date", currentDateTimeString);

        httpSession.setAttribute("usercart", c);
        httpSession.setAttribute("grandquantity", 0);
        return mv;
    }

    @RequestMapping("/confirmorder")
    public ModelAndView confirmOrder(@RequestParam("getaddressid") int addressid, HttpSession httpSession) {
        BillingAddress bm = (BillingAddress) bdao.viewOneBillingAddress(addressid);
        ArrayList<Cart> c = (ArrayList<Cart>) httpSession.getAttribute("usercart");
        String email = (String) httpSession.getAttribute("UserId");
        ProductOrder porder = new ProductOrder();
        porder.setUseremail(email);
        porder.setAddressid(addressid);
        odao.insertProductOrder(porder);
        ArrayList<OrderDetails> odetails = new ArrayList<OrderDetails>();
        for (Cart cart : c) {
            OrderDetails sorder = new OrderDetails();
            sorder.setOrderid(porder.getOrderid());
            sorder.setProductid(cart.getPid());
            sorder.setQuantity(cart.getQty());
            sorder.setPrice(cart.getPrice());
            odetails.add(sorder);
            // get product and update quantity
            Product p = pdao.viewOneProduct(cart.getPid());
            p.setProductquantity(p.getProductquantity()-cart.getQty());
            pdao.updateProduct(cart.getPid(), p);
            
        }
        odetdao.insertOrderDetails(odetails);
        odetails.removeAll(odetails);
        ModelAndView mv = new ModelAndView("invoice");
        mv.addObject("bill", "false");
        mv.addObject("success", "true");
        mv.addObject("orderid", porder.getOrderid());
        httpSession.setAttribute("usercart", new ArrayList<Cart>());
        httpSession.setAttribute("grandquantity", 0);
        return mv;
    }
//    @Autowired
//    BillingAddressService bdao;
//    @Autowired
//    ProductOrderService odao;
//    @Autowired
//    OrderDetailsService odetdao;
//    @Autowired
//    ProductService pdao;
//
//    @RequestMapping("/generatinginvoice")
//    public ModelAndView showingReceipt(@RequestParam("getaddressid") int addressid, HttpSession httpSession) {
//        BillingAddress bm = (BillingAddress) bdao.viewOneBillingAddress(addressid);
//        ArrayList<Cart> c = (ArrayList<Cart>) httpSession.getAttribute("usercart");
//        Gson g = new Gson();
//        String cartgson = g.toJson(c);
//        String addressgson = g.toJson(bm);
//        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
//        System.out.println("this is date  " + currentDateTimeString);
//
//        ModelAndView mv = new ModelAndView("invoice");
//        mv.addObject("addressobject", addressgson);
//        mv.addObject("itemsincart", cartgson);
//        mv.addObject("bill", "true");
//        httpSession.setAttribute("addressid", addressid);
//        httpSession.setAttribute("Date", currentDateTimeString);
//
//        httpSession.setAttribute("usercart", c);
//        httpSession.setAttribute("grandquantity", 0);
//        return mv;
//    }
//
//    @RequestMapping("/confirmorder")
//    public ModelAndView confirmOrder(@RequestParam("getaddressid") int addressid, HttpSession httpSession) {
//        BillingAddress bm = (BillingAddress) bdao.viewOneBillingAddress(addressid);
//        ArrayList<Cart> c = (ArrayList<Cart>) httpSession.getAttribute("usercart");
//        String email = (String) httpSession.getAttribute("UserId");
//        ProductOrder porder = new ProductOrder();
//        porder.setUseremail(email);
//        porder.setAddressid(addressid);
//        odao.insertProductOrder(porder);
//        ArrayList<OrderDetails> odetails = new ArrayList<OrderDetails>();
//        for (Cart cart : c) {
//            OrderDetails sorder = new OrderDetails();
//            sorder.setOrderid(porder.getOrderid());
//            sorder.setProductid(cart.getPid());
//            sorder.setQuantity(cart.getQty());
//            sorder.setPrice(cart.getPrice());
//            odetails.add(sorder);
//            // get product and update quantity
//            Product p = pdao.viewOneProduct(cart.getPid());
//            p.setProductquantity(p.getProductquantity()-cart.getQty());
//            pdao.updateProduct(cart.getPid(), p);
//            
//        }
//        odetdao.insertOrderDetails(odetails);
//        odetails.removeAll(odetails);
//        ModelAndView mv = new ModelAndView("invoice");
//        mv.addObject("bill", "false");
//        mv.addObject("success", "true");
//        mv.addObject("orderid", porder.getOrderid());
//        httpSession.setAttribute("usercart", new ArrayList<Cart>());
//        httpSession.setAttribute("grandquantity", 0);
//        return mv;
//    }
}
