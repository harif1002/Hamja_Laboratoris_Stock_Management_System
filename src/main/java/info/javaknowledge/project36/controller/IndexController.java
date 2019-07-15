/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.controller;

import info.javaknowledge.project36.dao.ProductService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import info.javaknowledge.project36.dao.ViewProductOrderService;
/**
 *
 * @author User
 */
@Controller
public class IndexController {
    @Autowired
    ProductService pdao;
    @Autowired
    ViewProductOrderService vdao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView landingPage(HttpSession httpSession) {
        //logger.info("Inside Landingpage in home controller");
        //vdao.viewProductOrder();
        String productlist = pdao.viewProduct();
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("productlist", productlist);
        //logger.info("End of Landingpage in home controller");
        return mv;
    }
}
