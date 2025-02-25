package avion.service;

import avion.model.*;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReservationService
{

    public static List<Reservation> getAllByIDUser(int idUser)throws Exception
    {
        try(Connection connection=DatabaseConnection.getConnection()) {
            return Reservation.getByIdUser(connection,idUser);
        }
    }
    public static  void annulerReservation(int idReservation)throws Exception
    {
        try(Connection connection=DatabaseConnection.getConnection()){
            Reservation reservation=Reservation.getByIdReservation(connection,idReservation);
            HeureAvantApresRes heureAvantApresRes = HeureAvantApresRes.read(connection);
            Vol vol = Vol.getById(connection, reservation.getIdVol());
            long timeMax = vol.getDateVol().getTime() - heureAvantApresRes.getHeureApRes().getTime();
            long annulationReservationTime = reservation.getDateReservation().getTime();

            if (annulationReservationTime <= timeMax)
            {
                Promotion promotion=Promotion.getPromotionByVolAndTypeSiege(connection,vol.getIdVol(),reservation.getIdTypeSiege());

                promotion.setNbPlaceRestant(promotion.getNbPlaceRestant()+reservation.getPlaceEnPromotion());
                Promotion.update(connection,promotion);
                Reservation.delete(connection,idReservation);
            }
            else
            {
                throw  new Exception("vous ne pouvez plus annuler cette reservation");
            }
        }
    }
    public  static void insertReservation(Reservation reservation)throws Exception {
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            HeureAvantApresRes heureAvantApresRes = HeureAvantApresRes.read(connection);
            Vol vol = Vol.getById(connection, reservation.getIdVol());
            long timeMax = vol.getDateVol().getTime() - heureAvantApresRes.getHeureAvRes().getTime();
            long reservationTime = reservation.getDateReservation().getTime();

            if (reservationTime <= timeMax) {
                VolPrixTypeSiege volPrixTypeSiege = VolPrixTypeSiege.getPrixByVol(connection, vol.getIdVol()).get(reservation.getIdTypeSiege());
                int placeRestant=Vol.getPlaceRestante(connection,vol.getIdVol(),volPrixTypeSiege.getIdTypeSiege());
                if(placeRestant-reservation.getNbPlace()<=0)
                {
                    connection.setAutoCommit(true);
                    throw  new Exception("il n'y plus assez de place sur ce vol ,il reste :"+placeRestant);
                }
                reservation.setPlaceEnPromotion(0);

                Promotion promotion = Promotion.getPromotionByVolAndTypeSiege(connection, vol.getIdVol(), volPrixTypeSiege.getIdTypeSiege());
                double prixReservation = volPrixTypeSiege.getPrixUnitaire() * reservation.getNbPlace();
                if (promotion.getNbPlaceRestant() > 0) {

                    int ala = reservation.getNbPlace();
                    if (ala > promotion.getNbPlaceRestant()) {
                        ala = promotion.getNbPlaceRestant();
                        prixReservation = prixReservation - volPrixTypeSiege.getPrixUnitaire() * 0.01* promotion.getRemise() * ala;
                        Promotion.decrementerNbPlaceRestant(connection, promotion.getIdPromotion(), ala);
                    }
                    reservation.setPlaceEnPromotion(ala);
                    reservation.setRemise( promotion.getRemise());

                }
                reservation.setPrixUnitaire(volPrixTypeSiege.getPrixUnitaire());
                reservation.setPrixBillet(prixReservation);
                Reservation.insert(connection, reservation);

            }
            else
            {
                connection.setAutoCommit(true);
                throw new Exception("vous ne pouvez plus reserver sur ce vol,il est trop tard");
            }
            connection.setAutoCommit(true);
        }
    }
    public static void insertHeureRes(HeureAvantApresRes res)throws Exception
    {
        try(Connection connection= DatabaseConnection.getConnection())
        {
            HeureAvantApresRes.update(connection,res);
        }
    }
}

