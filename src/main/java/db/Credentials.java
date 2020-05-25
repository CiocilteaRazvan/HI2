package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import security.*;

public class Credentials extends Database{

    private static ResultSet credentials = null;

    public Credentials()
    {
        super();
    }

    public String getUsername() {
        try {
            return credentials.getString("username");
        } catch (Exception e) {
            printException(e);
            return "";
        }
    }

    public String getPassword() {
        try {
            return credentials.getString("password");
        } catch (Exception e) {
            printException(e);
            return "";
        }
    }

    public int getType()
    {
        try {
            return credentials.getInt("type");
        } catch (Exception e) {
            printException(e);
            return 0;
        }
    }

    public boolean nextRow()
    {
        try {
            return credentials.next();
        } catch (Exception e) {
            printException(e);
            return false;
        }
    }

    public void openTable() throws SQLException {
        credentials = statement.executeQuery("SELECT * FROM credentials");
    }

    public void closeTable() throws SQLException {
        credentials.close();
    }

    public void addAccount(String usrPwdType)
    {
        try{
            String[] string = usrPwdType.split(" ", 3);
            String username = string[0];
            String password = string[1];
            int type = Integer.parseInt(string[2]);

            password = Hashing.sha256(password);

            PreparedStatement prepStatement = db.prepareStatement("INSERT INTO credentials (username, password, type) VALUES (?, ?, ?)");
            prepStatement.setString(1, username);
            prepStatement.setString(2, password);
            prepStatement.setInt(3, type);

            prepStatement.executeUpdate();

        } catch(Exception e) {
            printException(e);
        }
    }

    public void removeAccount(String removedUsername) {
        try {
            PreparedStatement prepStatement = db.prepareStatement("DELETE FROM credentials WHERE username = ?");
            prepStatement.setString(1, removedUsername);
            prepStatement.executeUpdate();
        } catch (Exception e) {
            printException(e);
        }
    }

    public void changePassword(String usrNewPwd){
        try {
            String[] string = usrNewPwd.split(" ", 2);
            String username = string[0];
            String newPassword = string[1];

            PreparedStatement prepStatement = db.prepareStatement("SELECT * FROM credentials WHERE username = ?");
            prepStatement.setString(1, username);

            ResultSet modifiedUser = prepStatement.executeQuery();
            modifiedUser.next();
            int type = modifiedUser.getInt("type");

            removeAccount(username);
            addAccount(username + " " + newPassword + " " + type);

        } catch (Exception e) {
            printException(e);
        }
    }

    public int checkCredentials(String username, String password) throws Exception {
        if(username.length() == 0 && password.length() == 0)
            return 0;

        try {
            password = Hashing.sha256(password);
            openTable();
            while(nextRow()) {
                if (getUsername().equals(username) && getPassword().equals(password))
                {
                    return getType();
                }
            }
            throw new Exception("Wrong Credentials");
        } catch (Exception e) {
            printException(e);
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public void printAllCredentials()
    {
        try{
            openTable();
            while(nextRow()) {
                System.out.println("Username: " + getUsername());
                System.out.println("Password: " + getPassword());
                System.out.println("Type: " + getType() + "\n");
            }
            System.out.println("------------------------------");
            closeTable();
        } catch(Exception e) {
            printException(e);
        }
    }
}