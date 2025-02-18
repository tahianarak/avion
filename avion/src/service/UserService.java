package avion.service;

import  avion.model.*;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService
{

    public User verifyLogin(String email,String mdp) throws SQLException
    {
        try(Connection connection=DatabaseConnection.getConnection())
        {
            return  User.authenticate(connection,email,mdp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
