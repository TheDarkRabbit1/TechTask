package org.example.utils;

public class SQLConstants {
    public static final String addEquation="INSERT INTO equations (body) VALUES (?);";
    public static final String getEquationById="select * from equations where id=?;";
    public static final String getOneRootEquation="SELECT e.body" +
            "FROM equations e " +
            "INNER JOIN equation_to_roots r ON e.id = r.eq_id " +
            "where r.root=?" +
            "GROUP BY e.id, e.body HAVING COUNT(*) = 1;";
    public static final String getEquationByRoot="SELECT e.body"+
            "FROM equations e INNER JOIN equation_to_roots r ON e.id = r.eq_id WHERE r.root = ?;";
    public static final String getAllEquations="select * from equations;";
    public static final String addRootForEquation="INSERT INTO equation_to_roots (eq_id, root) VALUES (?, ?);";
}
