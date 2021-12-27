package com.kelaskoding.Service;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportService {
    
    @Autowired
    public DataSource dataSource;
    
    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JasperPrint getJasperPrint(String nameParams) throws Exception{
        InputStream jasperInput = new ClassPathResource("reports/ProductReports.jasper").getInputStream();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperInput);
        Map<String, Object> keyParams = new HashedMap<String, Object>();
        keyParams.put("name", "%"+nameParams+"%");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, keyParams, getConnection());
        return jasperPrint;
    }

}
