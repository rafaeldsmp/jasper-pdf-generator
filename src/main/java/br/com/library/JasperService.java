package br.com.library;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

public class JasperService {
    private Map<String, Object> params = new LinkedHashMap<>();

    public void addParams(String key, Object value) {
        this.params.put(key, value);
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
