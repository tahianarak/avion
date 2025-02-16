package avion.service;

import avion.model.DatabaseConnection;
import avion.model.VilleDesservie;

import java.sql.Connection;
import java.util.List;

public class VilleDesservieService
{
    public static List<VilleDesservie> getAllVille()throws Exception
    {
        try(Connection connection= DatabaseConnection.getConnection())
        {
            return VilleDesservie.getAll(connection);
        }
    }
}
