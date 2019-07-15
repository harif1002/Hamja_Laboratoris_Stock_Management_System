/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.javaknowledge.project36.controller;

import info.javaknowledge.project36.dao.FeedbackService;
import info.javaknowledge.project36.model.Feedback;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin
 */
@Controller
public class FeedbackController {
     @Autowired
    FeedbackService fs;
    
     @InitBinder
    public void myInitBinder(WebDataBinder binder) {
        //binder.setDisallowedFields(new String[]{"empMobile"});
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        binder.registerCustomEditor(Date.class, "feedbackdate", new CustomDateEditor(format, false));
//        binder.registerCustomEditor(String.class, "ename", new EmployeeNameEditor());
    }
      
    /**
     *
     * @return
     */
    @RequestMapping("/showingfeedbackpage")
    public ModelAndView showfeedbackpage() {
        String feedbacklist = fs.viewFeedback();
        
        
        ModelAndView mv = new ModelAndView("addfeedback", "FeedbackObject", new Feedback());
        mv.addObject("feedbacklist", feedbacklist);
        mv.addObject("check", "true");
        return mv;
    }
        
    @RequestMapping(value = "/feedbackadd", params = "Add")
    public String addFeedback(@ModelAttribute("FeedbackObject") Feedback f) { 
        fs.insertFeedback(f);  
        return "redirect:/showingfeedbackpage";
    }

    @RequestMapping(value = "/feedbackadd", params = "Edit")
    public String editFeedback(@ModelAttribute("FeedbackObject") Feedback f) {
        fs.updateFeedback(f);
        return "redirect:/showingfeedbackpage";
    }

    @RequestMapping("removingfeedback/{feedbackid}")
    public String removeFeedback(@PathVariable("feedbackid") String feedbackid) {
        fs.deleteFeedback(feedbackid);
        return "redirect:/showingfeedbackpage";
    }

    @RequestMapping("/editingfeedback")
    public ModelAndView editfeedbackpage(@RequestParam("getfeedbackid") String feedbackid) {
        String feedbacklist = fs.viewFeedback();
        
        ModelAndView mv = new ModelAndView("addfeedback", "FeedbackObject", fs.viewOneFeedback(feedbackid));
        mv.addObject("feedbacklist", feedbacklist);
        mv.addObject("check", "false");
        return mv;
    }   
}
