package br.com.library;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        abrirJrxml("02");
    }

    private static void abrirJrxml(String numero) throws SQLException {
        Connection connection = JdbcConnection.getConnection();
        JasperService service = new JasperService();
        service.abrirJasperView("relatorios/jrxml/funcionarios-" + numero + ".jrxml", connection);
        connection.close();
    }
}