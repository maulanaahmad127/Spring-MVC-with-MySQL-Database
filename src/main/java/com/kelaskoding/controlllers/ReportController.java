package com.kelaskoding.controlllers;


import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kelaskoding.Service.ReportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/reports")
public class ReportController {
    
    @Autowired
    private ReportService reportService;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private HttpSession session;

    @GetMapping("/products")
    public void printJasperReport() throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Dispotition", "attachment; filename=\"product_list.pdf\"");
        String key = session.getAttribute("searchKey").toString();
        JasperPrint jasperPrint = reportService.getJasperPrint(key);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
