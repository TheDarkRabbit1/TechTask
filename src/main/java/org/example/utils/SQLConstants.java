package org.example.utils;

public class SQLConstants {
    public static final String addEquation="INSERT INTO equations (body) VALUES (?);";
    public static final String getEquationById="select * from equations where id=?;";
    public static final String getOneRootEquations="SELECT *" +
            "FROM equations e " +
            "INNER JOIN equation_to_roots r ON e.id = r.eq_id " +
            "where r.root=?" +
            "GROUP BY e.id, e.body HAVING COUNT(*) = 1;";
    public static final String getEquationsByRoot="SELECT *"+
            "FROM equations e INNER JOIN equation_to_roots r ON e.id = r.eq_id WHERE r.root = ?;";
    public static final String getAllEquations="select * from equations;";
    public static final String addRootForEquation="INSERT INTO equation_to_roots (eq_id, root) VALUES (?, ?);";
    public static final String getEquationByBody="SELECT * from equations where body=?;";
    public static final String getRootsOfEquation="SELECT root FROM roots WHERE equation_id = ?;";
}
