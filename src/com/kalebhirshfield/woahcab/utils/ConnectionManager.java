package com.kalebhirshfield.woahcab.utils;

import java.sql.*;

public class ConnectionManager {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void connect(String password) {
        String connStr = "jdbc:postgresql://db.glrdugvzjprdipllqdch.supabase.co:5432/postgres?user=postgres&password=" + password;
        try {
            Connection conn = DriverManager.getConnection(connStr);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
