package db;

import java.sql.PreparedStatement;
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

    private boolean nextRow()
    {
        try {
            return stock.next();
        } catch (Exception e) {
            printException(e);
            return false;
        }
    }

    public String getName() {
        try {
            return stock.getString("name");
        } catch (Exception e) {
            printException(e);
            return "";
        }
    }

    public String getDescription() {
        try {
            return stock.getString("description");
        } catch (Exception e) {
            printException(e);
            return "";
        }
    }

    public int getPrice() {
        try {
            return stock.getInt("price");
        } catch (Exception e) {
            printException(e);
            return -1;
        }
    }

    public int getQuantity() {
        try {
            return stock.getInt("quantity");
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

            PreparedStatement prepStatement = db.prepareStatement("UPDATE stock SET price = ? WHERE name = ?");
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

            PreparedStatement prepStatement = db.prepareStatement("UPDATE stock SET quantity = ? WHERE name = ?");
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

            PreparedStatement prepStatement = db.prepareStatement("UPDATE stock SET description = '?' WHERE name = ?");
            prepStatement.setString(1, newDescription);
            prepStatement.setString(2, name);
            prepStatement.executeUpdate();

        } catch (Exception e) {
            printException(e);
        }
    }

    public void removeItem(String name) {
        try {
            PreparedStatement prepStatement = db.prepareStatement("DELETE FROM stock WHERE name = ?");
            prepStatement.setString(1, name);
            prepStatement.executeUpdate();
        } catch (Exception e) {
            printException(e);
        }
    }

    public void addItem(String nameDescriptionPriceQuantity)
    {
        try {
            String[] string = nameDescriptionPriceQuantity.split(" ");
            String name = string[0];
            String description = "";
            for (int i = 1; i < string.length - 2; i++) {
                description += string[i] + " ";
            }
            int price = Integer.parseInt(string[string.length - 2]);
            int quantity = Integer.parseInt(string[string.length - 1]);

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

    protected ResultSet searchItem(String name) {
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

    public void addToBasket(String nameQuantity, Basket basket) throws Exception
    {
        try {
            String[] string = nameQuantity.split(" ", 2);
            String name = string[0];
            int addedQuantity = Integer.parseInt(string[1]);
            ResultSet rs = searchItem(name);
            rs.next();
            int quantity = rs.getInt("quantity");

            if (quantity >= addedQuantity) {
                quantity -= addedQuantity;
                PreparedStatement prepStatement = db.prepareStatement("UPDATE stock SET quantity = ? WHERE name = ?");
                prepStatement.setInt(1, quantity);
                prepStatement.setString(2, name);
                prepStatement.executeUpdate();

                String description = rs.getString("description");
                int price = rs.getInt("price");
                basket.addItem(name, description, price,addedQuantity);
            } else {
                throw new Exception("Not enough items in stock");
            }
        } catch (Exception e) {
            printException(e);
        }
    }

    public void printAllItems()
    {
        try{
            openTable();
            while(nextRow()) {
                System.out.println("Name: " + getName());
                System.out.println("Description: " + getDescription());
                System.out.println("Price: " + getPrice());
                System.out.println("Quantity: " + getQuantity() + "\n");
            }
            System.out.println("------------------------------");
            closeTable();
        } catch(Exception e) {
            printException(e);
        }
    }
}