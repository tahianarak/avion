package avion.service;

import avion.model.DatabaseConnection;
import avion.model.HeureAvantApresRes;

import java.sql.Connection;

public class ReservationService
{
    public static void insertHeureRes(HeureAvantApresRes res)throws Exception
    {
        try(Connection connection= DatabaseConnection.getConnection())
        {
            HeureAvantApresRes.update(connection,res);
        }
    }
}
