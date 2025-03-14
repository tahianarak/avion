package avion.service;

import avion.model.*;
import jakarta.servlet.http.HttpServletRequest;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class VolService
{
    public void updateVolWithPrixAndPromos( HttpServletRequest request, List<TypeSiege> typeSieges,
                                           Vol vol)throws Exception {

        Connection connection=DatabaseConnection.getConnection();

        try {

            connection.setAutoCommit(false);

            Vol.update(connection, vol);


            int idVol=vol.getIdVol();
            for (TypeSiege siege : typeSieges) {

                double prix = Double.valueOf(request.getParameter("prix_" + siege.getIdTypeSiege()));
                double pourcentagePromo = Double.valueOf(request.getParameter("pourcentagePromo_" + siege.getIdTypeSiege()));
                int nbPlacesPromo = Integer.valueOf(request.getParameter("nbPlacesPromo_" + siege.getIdTypeSiege()));
                int idPromo = Integer.valueOf(request.getParameter("id_promo_" + siege.getIdTypeSiege()));

               VolPrixTypeSiege.update(connection, new VolPrixTypeSiege(siege.getIdTypeSiege(), idVol, prix));


                Promotion.update(connection, new Promotion(idPromo, idVol, siege.getIdTypeSiege(), pourcentagePromo, nbPlacesPromo));
            }

            // Validation de la transaction si tout se passe bien
            connection.commit();
        } catch (SQLException e) {
            // En cas d'exception, rollback à partir du savepoint
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                // Réinitialiser l'auto-commit à true après la transaction
                connection.setAutoCommit(true);

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

     public void createVolWithPrixAndPromos( Vol vol, List<TypeSiege> typeSieges, HttpServletRequest request)throws Exception {

        Connection connection=DatabaseConnection.getConnection();
        try
        {
            connection.setAutoCommit(false);
            int idVol = insertVol( vol,connection);
            for (TypeSiege siege : typeSieges)
            {
                double prix = Double.valueOf(request.getParameter("prix_" + siege.getIdTypeSiege()));
                VolPrixTypeSiege.insert(connection, new  VolPrixTypeSiege(siege.getIdTypeSiege(), idVol, prix));
                double pourcentagePromo = Double.valueOf(request.getParameter("pourcentagePromo_" + siege.getIdTypeSiege()));
                int nbPlacesPromo = Integer.valueOf(request.getParameter("nbPlacesPromo_" + siege.getIdTypeSiege()));
                Promotion.insert(connection,new Promotion(-1, idVol, siege.getIdTypeSiege(), pourcentagePromo, nbPlacesPromo));
            }
            connection.commit();
        }
        catch (SQLException e)
        {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
        finally
        {
            try {
                connection.setAutoCommit(true);

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static HashMap<Integer, VolPrixTypeSiege> getPrixForVol(int idVol)throws Exception
    {
        try(Connection connection=DatabaseConnection.getConnection())
        {
            return VolPrixTypeSiege.getPrixByVol(connection,idVol);
        }
    }
    public static HashMap<Integer, Promotion> getPromByVol(int idVol)throws  Exception
    {
        try(Connection connection=DatabaseConnection.getConnection())
        {
            return  Promotion.getPromotionsForVol(connection,idVol);
        }
    }
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
    public  int insertVol(Vol vol,Connection connection)throws  Exception
    {

            return  Vol.insert(connection,vol);
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
