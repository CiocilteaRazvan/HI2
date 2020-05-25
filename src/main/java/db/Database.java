package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class Database {
    protected Connection db = null;
    protected Statement statement = null;

    public Database()
    {
        try{
            db = DriverManager.getConnection("jdbc:sqlite:hivdb.sqlite");
            statement = db.createStatement();
        } catch (Exception e){
            printException(e);
        }
    }

    public void close()
    {
        try {
            db.close();
        } catch (Exception e) {
            printException(e);
        }
    }

    public static void printException(Exception e) {
        System.out.println("Exception: " + e.getMessage());
    }
}
