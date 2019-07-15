package info.javaknowledge.project36.controller;

import info.javaknowledge.project36.dao.Userservice;
import info.javaknowledge.project36.model.Cart;
import info.javaknowledge.project36.model.User;
import info.javaknowledge.project36.model.UserRole;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import info.javaknowledge.project36.dao.ViewProductOrderService;

@Controller
public class LoginController {

    @Autowired
    Userservice user;
    
    @Autowired
    ViewProductOrderService vdao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin() {
        return "login";
    }
    
    @RequestMapping("/perlogcheck")
    public String prologin(HttpSession session, @RequestParam("getProductId") int id) {
        //logger.info("Inside prologin method in login controller");
        session.setAttribute("pId", id);
        //logger.info("end of prologin method in login controller");
        return "login";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String showWelcome() {
        return "welcome";
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView showAdmin() {
        String orderlist = vdao.viewProductOrder();
        ModelAndView mv = new ModelAndView("admin");
        mv.addObject("orderlist", orderlist);
        //logger.info("End of Landingpage in home controller");
        return mv;
    }

    @RequestMapping(value = "/loginsuccess")
    public String loginsuccess(HttpSession session) {
        System.out.println("success..........");

        String userid = SecurityContextHolder.getContext().getAuthentication().getName();
        User us = user.viewUser(userid);
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String page = "";
        String role = "ROLE_USER";
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(role)) {
                session.setAttribute("UserLoggedIn", us.getUsername());
                session.setAttribute("UserId", userid);
                session.setAttribute("usercart", new ArrayList<Cart>());
                page= "redirect:/";
            }
            else if (authority.getAuthority().equals("ROLE_ADMIN")) {
                session.setAttribute("UserLoggedIn", us.getUsername());
                session.setAttribute("UserId", userid);
                session.setAttribute("usercart", new ArrayList<Cart>());
                page= "redirect:/admin";
            }
            else{
            page= "redirect:/";
            }
        }
        return page;
    }
}
