package br.com.library;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class JasperService {
    private Map<String, Object> params = new LinkedHashMap<>();

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public void abrirJrxmlComSubReport(String arqMaster, String arqSub, Connection connection){
        try {
            JasperReport subReport = compilarJrxml(arqSub);
            this.params.put("SUB_REPORT_PARAM", subReport);
            JasperReport masterReport = compilarJrxml(arqMaster);
            JasperPrint print = JasperFillManager.fillReport(masterReport, this.params, connection);
            JasperViewer viewer = new JasperViewer(print);
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void abrirPontoJasper(String jasperFile, Connection connection){
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(jasperFile);
            JasperPrint print = JasperFillManager.fillReport(is, this.params, connection);
            JasperViewer viewer = new JasperViewer(print);
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportarParaPDF(String jrxml, Connection connection, String saida){
        JasperReport report = compilarJrxml(jrxml);
        try {
            OutputStream out = new FileOutputStream(saida);
            JasperPrint print = JasperFillManager.fillReport(report, this.params, connection);
            JasperExportManager.exportReportToPdfStream(print, out);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void abrirJasperView(String jrxml, Connection connection){
        JasperReport report = compilarJrxml(jrxml);
        try {
            JasperPrint print = JasperFillManager.fillReport(report, this.params, connection);
            JasperViewer viewer = new JasperViewer(print);
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private JasperReport compilarJrxml(String arquivo) {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(arquivo);
            if (inputStream == null) {
                throw new RuntimeException("Arquivo jrxml não encontrado no classpath: " + arquivo);
            }
            return JasperCompileManager.compileReport(inputStream);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }
}
