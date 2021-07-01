/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author DiegoHR
 */
public class Conexion {

    protected static Connection conexion = null;

    public static Connection conectar() throws Exception {

//        final String DB_URL = "jdbc:oracle:thin:@apafa_tp?TNS_ADMIN=./wallet_syap";
//        // Use TNS alias when using tnsnames.ora.  Use it while connecting to the database service on cloud. 
//        // final static String DB_URL=   "jdbc:oracle:thin:@orcldbaccess";
//        final String DB_USER = "ADMIN";
//        final String DB_PASSWORD = "Vallegrande2019";
//        final String CONN_FACTORY_CLASS_NAME = "oracle.jdbc.pool.OracleDataSource";
        try {
//            Class.forName(CONN_FACTORY_CLASS_NAME).newInstance();
//            conexion = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(
                    "jdbc:sqlserver:// localhost:1433;databaseName=pokemon",
                    "sa",
                    "123");
            //ORACLE
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##admin", "admin");
//            properties.load(inputStream);
//            String user = properties.getProperty("user");
//            String pwd = properties.getProperty("pwd");
//            String driver = properties.getProperty("driver");
//            String url = properties.getProperty("url");                
//            Class.forName(driver).newInstance();
//            conexion = DriverManager.getConnection(url, user, pwd);
            //Mysql
//           String driver = "com.mysql.jdbc.Driver";
//           String url = "jdbc:mysql://localhost:3306/syap";
//           String user = "root";
//           String pwd = "root";
//           Class.forName(driver).newInstance();
//           conexion = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
    }

    public static void desconectar() throws SQLException {
        try {
            if (conexion.isClosed() == false) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean estado() throws SQLException {
        try {
            return !conexion.isClosed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        conectar();
        System.out.println("[STATUS CONNECTION]: " + estado());
        desconectar();

    }

}
