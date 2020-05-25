package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Basket extends Database{

    private static ResultSet basket = null;

    public Basket()
    {
        super();
    }

    private void openTable() throws SQLException
    {
            basket = statement.executeQuery("SELECT * FROM basket");
    }

    private void closeTable() throws SQLException
    {
        basket.close();
    }

    private boolean nextRow()
    {
        try {
            return basket.next();
        } catch (Exception e) {
            printException(e);
            return false;
        }
    }

    public String getName() {
        try {
            return basket.getString("name");
        } catch (Exception e) {
            printException(e);
            return "";
        }
    }

    public String getDescription() {
        try {
            return basket.getString("description");
        } catch (Exception e) {
            printException(e);
            return "";
        }
    }

    public int getPrice() {
        try {
            return basket.getInt("price");
        } catch (Exception e) {
            printException(e);
            return -1;
        }
    }

    public int getQuantity() {
        try {
            return basket.getInt("quantity");
        } catch (Exception e) {
            printException(e);
            return -1;
        }
    }

    public void updatePrice(String nameNewPrice) {
        try {
            String[] string = nameNewPrice.split(" ", 2);
            String name = string[0];
            int newPrice = Integer.parseInt(string[1]);

            PreparedStatement prepStatement = db.prepareStatement("UPDATE basket SET price = ? WHERE name = ?");
            prepStatement.setInt(1, newPrice);
            prepStatement.setString(2, name);
            prepStatement.executeUpdate();

        } catch (Exception e) {
            printException(e);
        }
    }

    public void updateQuantity(String nameNewQuantity) {
        try {
            String[] string = nameNewQuantity.split(" ", 2);
            String name = string[0];
            int newQuantity = Integer.parseInt(string[1]);

            PreparedStatement prepStatement = db.prepareStatement("UPDATE basket SET quantity = ? WHERE name = ?");
            prepStatement.setInt(1, newQuantity);
            prepStatement.setString(2, name);
            prepStatement.executeUpdate();

        } catch (Exception e) {
            printException(e);
        }
    }

    public void updateDescription(String nameNewDescription) {
        try {
            String[] string = nameNewDescription.split(" ", 2);
            String name = string[0];
            String newDescription = string[1];

            PreparedStatement prepStatement = db.prepareStatement("UPDATE basket SET description = '?' WHERE name = ?");
            prepStatement.setString(1, newDescription);
            prepStatement.setString(2, name);
            prepStatement.executeUpdate();

        } catch (Exception e) {
            printException(e);
        }
    }

    private ResultSet searchItem(String name) {
        try {
            PreparedStatement preparedStatement = db.prepareStatement("SELECT * FROM stock WHERE name = ?");
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();
            return rs;
        } catch (Exception e) {
            printException(e);
            return null;
        }
    }

    public void addItem(String name, String description, int price, int quantity)
    {
        try {
            if(searchItem(name).next())
            {
                System.out.println("GOT HERE");
                updateQuantity(name + " " + quantity);
                return;
            }
            PreparedStatement prepStatement = db.prepareStatement("INSERT INTO stock (name, description, price, quantity) VALUES (?, ?, ?, ?)");
            prepStatement.setString(1, name);
            prepStatement.setString(2, description);
            prepStatement.setInt(3, price);
            prepStatement.setInt(4, quantity);

            prepStatement.executeUpdate();

        } catch (Exception e) {
            printException(e);
        }
    }

    public void removeItem(String nameRemovedQuantity, Stock stock) throws Exception
    {
        try {
            String[] string = nameRemovedQuantity.split(" ", 2);
            String name = string[0];
            int removedQuantity = Integer.parseInt(string[1]);

            ResultSet rs = searchItem(name);
            int quantity = rs.getInt("quantity");
            if(quantity >= removedQuantity) {
                quantity -= removedQuantity;

                PreparedStatement prepStatement = db.prepareStatement("UPDATE quantity = ? WHERE name = ?");
                prepStatement.setInt(1, quantity);
                prepStatement.setString(2, name);
                prepStatement.executeUpdate();

                ResultSet stockItem = stock.searchItem(name);
                int existingQuantity = stockItem.getInt("quantity");
                existingQuantity += removedQuantity;
                stock.updateQuantity(name + " " + existingQuantity);
            } else {
                throw new Exception("Not enough items in basket");
            }
        } catch (Exception e) {
            printException(e);
        }
    }

    public void printAllItems()
    {
        try {
            openTable();
            while(nextRow())
            {
                System.out.println("Name: " + getName());
                System.out.println("Description: " + getDescription());
                System.out.println("Price: " + getPrice());
                System.out.println("Quantity: " + getQuantity() + "\n");
            }
            System.out.println("------------------------------");
            closeTable();
        } catch (Exception e) {
            printException(e);
        }

    }
}
