package org.example.equation;

import org.example.Main;

import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class EquationRepository implements EquationDAO{
    public EquationRepository() {}

    @Override
    public void saveEquation(Equation equation) {

    }

    @Override
    public void saveRoot(long for_eq_id, Float root) {

    }

    @Override
    public Equation getEquationById(long id) {
        return null;
    }

    @Override
    public Equation getEquationByBody(String body) {
        return null;
    }

    @Override
    public List<Equation> getEquations() {
        return null;
    }

    public List<Equation> getEquationsByRoot(Float root) {
        return null;
    }

    public List<Equation> getOnlyOneRootEquations() {
        return null;
    }
    private Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        InputStream in = Main.class.getResourceAsStream("databaseConfiguration.xml");
        Properties props = new Properties();
        props.loadFromXML(in);
        in.close();

        Class.forName(props.getProperty("driver"));
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, user, password);
    }
}
