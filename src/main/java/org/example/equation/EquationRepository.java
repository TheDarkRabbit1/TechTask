package org.example.equation;

import org.example.Main;
import org.example.utils.SQLConstants;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EquationRepository implements EquationDAO {
    public EquationRepository() {
    }

    @Override
    public void saveEquation(Equation equation) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.addEquation);
            preparedStatement.setString(1, equation.getBody());
            preparedStatement.executeUpdate();
            preparedStatement = getConnection().prepareStatement(SQLConstants.getEquationByBody);
            preparedStatement.setString(1, equation.getBody());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                equation.setId(rs.getLong("id"));
            }

        } catch (SQLException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void saveRoot(long eq_id, Float root) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.addRootForEquation);
            preparedStatement.setLong(1, eq_id);
            preparedStatement.setFloat(2, root);
            preparedStatement.executeUpdate();
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Equation getEquationById(long id) {
        Equation equation = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.getEquationById);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                equation = new Equation(rs.getString("body"));
                equation.setId(id);
                equation.setRoots(getRootsForEquation(equation.getId()));
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return equation;
    }

    @Override
    public Equation getEquationByBody(String body) {
        Equation equation = null;
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.getEquationByBody);
            preparedStatement.setString(1, body);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                equation = new Equation(body);
                equation.setId(rs.getLong("id"));
                equation.setRoots(getRootsForEquation(equation.getId()));
            }
        } catch (SQLException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return equation;
    }

    @Override
    public List<Equation> getEquations() {
        List<Equation> equations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.getAllEquations);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Equation equation = new Equation(rs.getString("body"));
                equation.setId(rs.getLong("id"));
                equation.setRoots(getRootsForEquation(equation.getId()));
                equations.add(equation);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return equations;
    }

    public List<Equation> getEquationsByRoot(Float root) {
        List<Equation> equations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.getEquationsByRoot);
            preparedStatement.setFloat(1, root);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Equation equation = new Equation(rs.getString("body"));
                equation.setId(rs.getLong("id"));
                equation.setRoots(getRootsForEquation(equation.getId()));
                equations.add(equation);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return equations;
    }

    public List<Equation> getOnlyOneRootEquations() {
        List<Equation> equations = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.getOneRootEquations);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Equation equation = new Equation(rs.getString("body"));
                equation.setId(rs.getLong("id"));
                equations.add(equation);
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return equations;
    }

    @Override
    public List<Float> getRootsForEquation(long id) {
        List<Float> roots = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.getRootsOfEquation);
            preparedStatement.setFloat(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                roots.add(rs.getFloat("root"));
            }
        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return roots;
    }

    public void clearTablesForTests() {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(SQLConstants.clearTableEquation_to_Roots);
            preparedStatement.execute();
            preparedStatement = getConnection().prepareStatement(SQLConstants.clearTableEquations);
            preparedStatement.execute();

        } catch (SQLException | IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        InputStream in = Main.class.getResourceAsStream("/databaseConfiguration.xml");
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
