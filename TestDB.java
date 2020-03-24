package com.koneksidatabase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {
    static String classConnection = "com.mysql.jdbc.Driver";
    static String dbUrl = "jdbc:mysql://127.0.0.1:3306/Mahasiswa";
    static String username = "admin";
    static String password = "1234";
    static Statement statement = null;
    static ResultSet resultSet = null;

    public static void main(String[] args) throws SQLException {
        Koneksi koneksi = new Koneksi(classConnection, dbUrl, username, password);
        Connection connection      = koneksi.getConnection();

        String sql = "SELECT * FROM karyawan";
        statement = connection.createStatement();
        // Result set get the result of the SQL query
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            long id             = resultSet.getLong("id");
            String namakaryawan = resultSet.getString("namakaryawan");
            int nip             = resultSet.getInt("nip");
            System.out.println(id + " - " + namakaryawan + " - " + nip);
        }


    }
}
