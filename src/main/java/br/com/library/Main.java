package br.com.library;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws SQLException {
        //abrirJrxml("18");
//        exportarParaPdf("18", "${LOCAL_ARQUIVO}" + "jasper-" + UUID.randomUUID() + ".pdf");
        //abrirPontoJasperComSubRelatorios("10");
        abrirJrxmlComSubRelatorios("10");
    }

    private static void abrirPontoJasperComSubRelatorios(String numero) throws SQLException {
        Connection connection = JdbcConnection.getConnection();
        JasperService service = new JasperService();
        service.abrirPontoJasper("relatorios/jasper/funcionarios-" + numero + ".jasper", connection);
        connection.close();
    }

    private static void abrirJrxmlComSubRelatorios(String numero) throws SQLException {
        Connection connection = JdbcConnection.getConnection();
        JasperService service = new JasperService();
//        service.addParams("NIVEL_DESC", "JUNIOR");
//        service.addParams("UF", "SP");
        service.abrirJrxmlComSubReport(
                "relatorios/jrxml/funcionarios-" + numero + ".jrxml",
                "relatorios/jrxml/funcionario-" + numero + "-subfone.jrxml",
                connection);
        connection.close();
    }

    private static void abrirPontoJasper(String numero) throws SQLException {
        Connection connection = JdbcConnection.getConnection();
        JasperService service = new JasperService();
//        service.addParams("NIVEL_DESC", "JUNIOR");
        service.addParams("UF", "SP");
        service.abrirPontoJasper("relatorios/jasper/funcionarios-" + numero + ".jasper", connection);
        connection.close();
    }

    private static void exportarParaPdf(String numero, String saida) throws SQLException {
        Connection connection = JdbcConnection.getConnection();
        JasperService service = new JasperService();
        service.exportarParaPDF("relatorios/jrxml/funcionarios-" + numero + ".jrxml", connection, saida);
        connection.close();
    }

    private static void abrirJrxml(String numero) throws SQLException {
        Connection connection = JdbcConnection.getConnection();
        JasperService service = new JasperService();
//        service.addParams("NIVEL_DESC", "JUNIOR");
//        service.addParams("UF", "SP");
        service.abrirJasperView("relatorios/jrxml/funcionarios-" + numero + ".jrxml", connection);
        connection.close();
    }
}