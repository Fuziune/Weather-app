package repository;

import org.sqlite.SQLiteDataSource;
import java.sql.*;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        conn=null;
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
