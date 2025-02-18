package avion.service;

import avion.model.*;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class VolService
{
    public static  void insertPromoVol(Promotion promotion)throws Exception{
        try(Connection connection=DatabaseConnection.getConnection())
        {
            Promotion.insert(connection,promotion);
        }
    }

    public static  void insertVolPrix(VolPrixTypeSiege volPrixTypeSiege)throws Exception{
        try(Connection connection=DatabaseConnection.getConnection())
        {
            VolPrixTypeSiege.insert(connection,volPrixTypeSiege);
        }
    }
    public static void updateVol(Vol vol)throws Exception
    {
        try(Connection connection=DatabaseConnection.getConnection())
        {
            Vol.update(connection,vol);
        }
    }
    public  int insertVol(Vol vol)throws  Exception
    {
        try(Connection connection= DatabaseConnection.getConnection())
        {
            return  Vol.insert(connection,vol);
        }
    }

    public static List<Vol> getAllVolfiltered(Date dateMin,Date dateMax,int idModeleAvion,int idVilleDepart)throws Exception
    {
        try(Connection connection=DatabaseConnection.getConnection())
        {
            return Vol.filterVols(connection,dateMin,dateMax,idVilleDepart,idModeleAvion);
        }
    }
    public static List<Vol> getAllVol()throws Exception
    {
        try(Connection connection=DatabaseConnection.getConnection())
        {
            return Vol.getAll(connection);
        }

    }



    public static  Vol getVolByID(int idVol)throws Exception
    {
        try (Connection connection= DatabaseConnection.getConnection()){
            return  Vol.getById(connection,idVol);
        }
    }
}
