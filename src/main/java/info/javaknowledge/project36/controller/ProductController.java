/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import info.javaknowledge.project36.dao.*;
import info.javaknowledge.project36.model.Product;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
public class ProductController {

    @Autowired
    ProductService pdao;
    @Autowired
    CategoryService cdao;
    @Autowired
    SupplierService sdao;
    @Autowired
    private ServletContext servletContext;

    @InitBinder
    public void myInitBinder(WebDataBinder binder) {
        //binder.setDisallowedFields(new String[]{"empMobile"});
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        binder.registerCustomEditor(Date.class, "purchasedate", new CustomDateEditor(format, false));
//        binder.registerCustomEditor(String.class, "ename", new EmployeeNameEditor());
    }

    @RequestMapping("/showproductpage")
    public ModelAndView showproductpage() {
        String productlist = pdao.viewProduct();
        String categorygsonlist = cdao.viewCategory();
        String supplierslist = sdao.viewSupplier();

        ModelAndView mv = new ModelAndView("addproduct", "newProductObject", new Product());
        mv.addObject("productlist", productlist);
        mv.addObject("supplierslist", supplierslist);
        mv.addObject("categorymodelobject", categorygsonlist);
        mv.addObject("check", "true");
        return mv;
    }

    @RequestMapping(value = "/productadd", params = "Add")
    public String addproductpage(@ModelAttribute("newProductObject") Product product, HttpServletRequest request) {
        pdao.insertProduct(product);
        String path = servletContext.getRealPath("/");
        System.out.println(path);
        String projectcut = path.substring(0, path.lastIndexOf("\\"));
        String targetcut = projectcut.substring(0, projectcut.lastIndexOf("\\"));
         String targetcut2 = targetcut.substring(0, targetcut.lastIndexOf("\\"));
        //System.out.println(targetcut+".......");
        //System.out.println(request.getSession().getServletContext());
        //String p = servletContext.getContextPath();
        String p = targetcut2 + "\\src\\main\\webapp\\resources\\pimage\\" + String.valueOf(product.getProductid()) + "" + ".jpg";
//        path = path + String.valueOf(product.getProductid()) + "" + ".jpg";
        System.out.println(p);
        MultipartFile filedet = product.getPimage();
        if (!filedet.isEmpty()) {
            try {
                byte[] bytes = filedet.getBytes();
                System.out.println(bytes.length);
                FileOutputStream fos = new FileOutputStream(new File(p));
                BufferedOutputStream bs = new BufferedOutputStream(fos);
                bs.write(bytes);
                bs.close();
                fos.close();
                Thread.sleep(10000);
                System.out.println("File Uploaded Successfully");
            } catch (Exception e) {
                System.out.println("Exception Arised" + e);
            }
        } else {
            System.out.println("File is Empty not Uploaded");
        }
        return "redirect:/showproductpage";
    }

    @RequestMapping(value = "/productadd", params = "Edit")
    public String editProduct(@ModelAttribute("newProductObject") Product product, HttpServletRequest request) {
        pdao.updateProduct(product.getProductid(), product);
        String path = servletContext.getRealPath("/");
        String projectcut = path.substring(0, path.lastIndexOf("\\"));
        String targetcut = projectcut.substring(0, projectcut.lastIndexOf("\\"));
         String targetcut2 = targetcut.substring(0, targetcut.lastIndexOf("\\"));
//        path = path + String.valueOf(product.getProductid()) + "" + ".jpg";
        String p = targetcut2 + "\\src\\main\\webapp\\resources\\pimage\\" + String.valueOf(product.getProductid()) + "" + ".jpg";
        MultipartFile filedet = product.getPimage();
        if (!filedet.isEmpty()) {
            try {
                byte[] bytes = filedet.getBytes();
                System.out.println(bytes.length);
                File f = new File(p);
                if (f.exists()) {
                    f.delete();
                    FileOutputStream fos = new FileOutputStream(f);
                    BufferedOutputStream bs = new BufferedOutputStream(fos);
                    bs.write(bytes);
                    bs.close();
                    fos.close();
                    Thread.sleep(10000);
                    System.out.println("File Uploaded Successfully");
                }
            } catch (Exception e) {
                System.out.println("Exception Arised" + e);
            }
        } else {
            System.out.println("File is Empty not Uploaded");
        }
        return "redirect:/showproductpage";
    }

    @RequestMapping("removingproduct/{productId}")
    public String removeproduct(@PathVariable("productId") int productid) {
        pdao.deleteProduct(productid);
        return "redirect:/showproductpage";
    }

    @RequestMapping("/viewproduct")
    public ModelAndView viewproductdata(@RequestParam("getId") int productid, HttpSession session) {
        Gson g = new Gson();
        String productdata = g.toJson(pdao.viewOneProduct(productid));
        session.setAttribute("productid", productid);
        ModelAndView mv = new ModelAndView("prodescription");
        mv.addObject("pro", productdata);
        return mv;
    }

    @RequestMapping("/buyproductpage")
    public ModelAndView viewproductpage() {
        String productlist = pdao.viewProduct();
        ModelAndView mv = new ModelAndView("viewproduct");
        mv.addObject("productlist", productlist);
        return mv;
    }

    @RequestMapping("/editingproduct")
    public ModelAndView editproductpage(@RequestParam("getpid") int pid) {
        
        String categorygsonlist = cdao.viewCategory();
        String supplierslist = sdao.viewSupplier();
        String productlist = pdao.viewProduct();
        
        ModelAndView mv = new ModelAndView("addproduct", "newProductObject", pdao.viewOneProduct(pid));
        mv.addObject("productlist", productlist);
        mv.addObject("supplierslist", supplierslist);
        mv.addObject("categorymodelobject", categorygsonlist);
        mv.addObject("check", "false");
        return mv;
    }
}
