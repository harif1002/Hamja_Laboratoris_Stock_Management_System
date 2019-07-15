package info.javaknowledge.project36.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import info.javaknowledge.project36.reportmodel.ProductReport;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import info.javaknowledge.project36.daoimpl.JasperReportDAO;
/**
 *
 * @author User
 */
@Controller
public class ReportController {
    
    @RequestMapping(value = "/reportView", method = RequestMethod.GET)
    public String loadSurveyPg(
            @ModelAttribute("reportInputForm") ProductReport reportInputForm, Model model) {
        model.addAttribute("reportInputForm", reportInputForm);
        return "report";
    }
    
    @RequestMapping(value = "/reportView", method = RequestMethod.POST)
    public String generateReport(@ModelAttribute("reportInputForm") ProductReport reportInputForm,HttpServletRequest request,HttpServletResponse response) throws JRException, IOException, SQLException, NamingException {
        String reportFileName = "productlist";
        JasperReportDAO jrdao = new JasperReportDAO();
        Connection conn = null;
        try {
            conn = jrdao.getConnection();
            String title = reportInputForm.getTitle();
            HashMap<String, Object> hmParams = new HashMap<String, Object>();
            hmParams.put("title", title);
            JasperReport jasperReport = jrdao.getCompiledFile(reportFileName,request);

            jrdao.generateReportPDF(response, hmParams, jasperReport, conn); 

        } catch (SQLException sqlExp) {
            System.out.println("Exception::" + sqlExp.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }

        return null;
    }
}
