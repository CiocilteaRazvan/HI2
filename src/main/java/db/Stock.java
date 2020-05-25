package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Stock extends Database{
    private static ResultSet stock = null;

    public Stock()
    {
        super();
    }

    public void openTable() throws SQLException {
        stock = statement.executeQuery("SELECT * FROM stock");
    }

    public void closeTable() throws SQLException {
        stock.close();
    }

}