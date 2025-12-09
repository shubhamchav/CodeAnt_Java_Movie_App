package com.movieapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class BuggyVulnerable {
    // Hardcoded credentials (vulnerability)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/movies";
    private static final String USER = "root";
    private static final String PASS = "password123";

    public static void main(String[] args) {
        BuggyVulnerable bv = new BuggyVulnerable();
        bv.runQuery("admin' OR '1'='1"); // SQL Injection vulnerability
        bv.nullPointerBug();
    }

    // SQL Injection vulnerability
    public void runQuery(String username) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Null pointer bug
    public void nullPointerBug() {
        String str = null;
        // This will throw NullPointerException
        System.out.println(str.length());
    }
}
