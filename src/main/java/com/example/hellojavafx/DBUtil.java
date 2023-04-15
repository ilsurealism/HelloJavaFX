package com.example.hellojavafx;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DBUtil {
    static String url = "jdbc:sqlite:hellojavafx.db";
    static Connection conn = null;

    public static void connect() throws ClassNotFoundException, SQLException {

        checkDrivers();
        try {

            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw e;
        } /*finally {
            try {
                if (conn == null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
    }

    public static void dbDisconnect() throws  SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

//    public static void createDB(/*String dbName*/) {
//        String url = "jdbc:sqlite:hellojavafx.db";
//
//        try (Connection conn = DriverManager.getConnection(url)){
//            if (conn != null) {
//                checkDrivers();
//                DatabaseMetaData meta = conn.getMetaData();
//                System.out.println("Driver is " + meta.getDriverName());
//                System.out.println("Created DB");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        } catch (ClassNotFoundException er) {
//            System.out.println(er.getMessage());
//        }
//    }

    private static boolean checkDrivers() {
        try {
            Class.forName("org.sqlite.JDBC");
            DriverManager.registerDriver(new org.sqlite.JDBC());
            return true;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
            Logger.getAnonymousLogger().log(Level.SEVERE, LocalDateTime.now() + ": Could not start SQLite Drivers");
            return false;
        }
    }

    public static void initDBObjects() {

        String[] queries = new String[6];

        queries[0] = "create table if not exists assets_types (id integer primary key, name text not null unique);";
        queries[1] = "create table if not exists currencies (id integer primary key, code text not null unique, name text);";
        queries[2] = "create table if not exists assets (id integer primary key, name text not null unique,\n" +
                        "asset_type_id integer not null,\n" +
                        "creation_dt datetime default current_timestamp,\n" +
                        "foreign key(asset_type_id) references assets_types(id));";
        queries[3] = "create table if not exists budget (id integer primary key,\n" +
                        "asset_id integer not null,\n" +
                        "currency_id integer not null,\n" +
                        "sum integer,\n" +
                        "creation_dt datetime default current_timestamp,\n" +
                        "update_dt datetime default current_timestamp,\n" +
                        "foreign key(asset_id) references assets(id)," +
                        "foreign key(currency_id) references currencies(id));";
        queries[4] = "insert into assets_types (name) values (\"cash\"),\n" +
                        "(\"deposit\")\n," +
                        "(\"bank account\")\n," +
                        "(\"brokerage account\");";
        queries[5] = "insert into currencies (code, name) values (\"RUB\", \"Rubles\"),\n" +
                "(\"USD\", \"US Dollars\"),\n" +
                "(\"EUR\", \"Euro\"),\n" +
                "(\"KZT\", \"Tenge\");";
        for (String q:queries) {
            if (q != null) {
                createTable(q);
            }
        }
    }

    private static void createTable(String sql) {
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;
        try {
            connect();
            stmt = conn.createStatement();
            System.out.println(queryStmt);
            resultSet = stmt.executeQuery(queryStmt);
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet); // error
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }

    // insert/update/delete
    public static void dbExecuteUpdate(String sqlQuery) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            connect();
            stmt = conn.createStatement();
            stmt.execute(sqlQuery);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }

}
