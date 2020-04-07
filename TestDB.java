package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestDB {
    static String classConnection   = "com.mysql.jdbc.Driver";
    static String dbUrl             = "jdbc:mysql://127.0.0.1:3306/pbo";
    static String username          = "admin";
    static String password          = "1234";
    static Statement statement      = null;
    static ResultSet resultSet      = null;
    static Koneksi koneksi = new Koneksi(classConnection, dbUrl, username, password);
    static Connection connection      = koneksi.getConnection();

    public static void main(String[] args) throws SQLException {

//        TestDB testDB = new TestDB();
        System.out.println("1. Tampilkan data");
        System.out.println("2. Masukan data");
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Pilih menu yang ingin ditampilkan ");

        if(keyboard.nextInt() == 1){
            tampilkanData();
        }
        else{
            masukanData();
        }
    }

    public static void masukanData() throws SQLException{
        System.out.println("Masukan nama");
        Scanner keyboard = new Scanner(System.in);
        String nama = keyboard.next();
        System.out.println("Masukan nim");
        String nim = keyboard.next();
        statement = connection.createStatement();

        String sql = "INSERT INTO mahasiswa(nama, nim) " +
                "VALUES ('"+nama+"', '"+nim+"')";
        statement.executeUpdate(sql);
    }

    public static void tampilkanData() throws SQLException{
        String sql = "SELECT * FROM mahasiswa";
        statement = connection.createStatement();
        // Result set get the result of the SQL query
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            long id             = resultSet.getLong("id");
            String namakaryawan = resultSet.getString("nama");
            String nim          = resultSet.getString("nim");
            System.out.println(id + " - " + namakaryawan + " - " + nim);
        }
        System.out.println("Anda mau edit data atau hapus data?");
        System.out.println("1. Edit data");
        System.out.println("2. Hapus data");
        Scanner keyboard = new Scanner(System.in);



        if(keyboard.nextInt() == 1){
            System.out.println("Masukan data yang ingin diedit");
            int id_edit = keyboard.nextInt();
            System.out.println("Masukan nama");
            String nama = keyboard.next().toString();
            System.out.println("Masukan nim");
            String nim = keyboard.next().toString();
            System.out.println("Updating");
            String sql_update = "UPDATE mahasiswa SET nama = '"+nama+"', nim =  '"+nim+"' WHERE id="+id_edit+" ";

            statement.executeUpdate(sql_update);
        }
        else{
            System.out.println("Masukan data yang ingin dihapus");
            int id_edit = keyboard.nextInt();
            String sql_delete = "DELETE FROM mahasiswa WHERE id="+id_edit+" ";

            statement.executeUpdate(sql_delete);
        }

    }
}
