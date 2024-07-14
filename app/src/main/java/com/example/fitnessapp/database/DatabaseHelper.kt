//package com.example.fitnessapp.database
//
//import java.sql.Connection
//import java.sql.DriverManager
//import java.sql.SQLException
//
//object DatabaseHelper {
//
//    private var connection: Connection? = null
//
//    fun getConnection(): Connection? {
//        if (connection == null) {
//            try {
//                // Load JDBC driver (e.g., for MySQL)
//                Class.forName("com.mysql.jdbc.Driver")
//
//                // Replace with your database URL, username, and password
//                val url = "jdbc:mysql://your-database-url:3306/your-database-name"
//                val username = "your-username"
//                val password = "your-password"
//
//                // Establish connection
//                connection = DriverManager.getConnection(url, username, password)
//            } catch (e: ClassNotFoundException) {
//                e.printStackTrace()
//            } catch (e: SQLException) {
//                e.printStackTrace()
//            }
//        }
//        return connection
//    }
//
//    fun closeConnection() {
//        try {
//            connection?.close()
//            connection = null
//        } catch (e: SQLException) {
//            e.printStackTrace()
//        }
//    }
//}
