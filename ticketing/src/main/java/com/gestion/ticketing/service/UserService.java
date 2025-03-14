package com.gestion.ticketing.service;

import com.gestion.ticketing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class UserService
{
    @Autowired
    DataSource dataSource;
    public User verifyLogin(String email, String mdp) throws SQLException
    {
        try(Connection connection=dataSource.getConnection())
        {
            return  User.authenticate(connection,email,mdp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
