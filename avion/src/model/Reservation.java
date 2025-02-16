package avion.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private int idReservation;
    private Timestamp dateReservation;
    private int idUser;
    private int idVol;
    private int idTypeSiege;
    private double prixBillet; // Nouvel attribut

    // Constructeur
    public Reservation(int idReservation, Timestamp dateReservation, int idUser, int idVol, int idTypeSiege, double prixBillet) {
        this.idReservation = idReservation;
        this.dateReservation = dateReservation;
        this.idUser = idUser;
        this.idVol = idVol;
        this.idTypeSiege = idTypeSiege;
        this.prixBillet = prixBillet; // Initialisation du nouvel attribut
    }

    // Getters et Setters
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Timestamp getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Timestamp dateReservation) {
        this.dateReservation = dateReservation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public int getIdTypeSiege() {
        return idTypeSiege;
    }

    public void setIdTypeSiege(int idTypeSiege) {
        this.idTypeSiege = idTypeSiege;
    }

    public double getPrixBillet() {
        return prixBillet;
    }

    public void setPrixBillet(double prixBillet) {
        this.prixBillet = prixBillet;
    }

    // Méthode pour obtenir toutes les réservations avec le prix du billet
    public static List<Reservation> getAll(Connection connection) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idReservation = resultSet.getInt("id_reservation");
                Timestamp dateReservation = resultSet.getTimestamp("date_reservation");
                int idUser = resultSet.getInt("id_user");
                int idVol = resultSet.getInt("id_vol");
                int idTypeSiege = resultSet.getInt("id_type_siege");
                double prixBillet = resultSet.getDouble("prix_billet"); // Récupérer le prix du billet

                Reservation reservation = new Reservation(idReservation, dateReservation, idUser, idVol, idTypeSiege, prixBillet);
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Méthode pour insérer une réservation avec le prix du billet
    public static void insert(Connection connection, Reservation reservation) {
        String query = "INSERT INTO reservation (date_reservation, id_user, id_vol, id_type_siege, prix_billet) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, reservation.getDateReservation());
            preparedStatement.setInt(2, reservation.getIdUser());
            preparedStatement.setInt(3, reservation.getIdVol());
            preparedStatement.setInt(4, reservation.getIdTypeSiege());
            preparedStatement.setDouble(5, reservation.getPrixBillet()); // Insérer le prix du billet
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour une réservation avec le prix du billet
    public static void update(Connection connection, Reservation reservation) {
        String query = "UPDATE reservation SET date_reservation = ?, id_user = ?, id_vol = ?, id_type_siege = ?, prix_billet = ? WHERE id_reservation = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, reservation.getDateReservation());
            preparedStatement.setInt(2, reservation.getIdUser());
            preparedStatement.setInt(3, reservation.getIdVol());
            preparedStatement.setInt(4, reservation.getIdTypeSiege());
            preparedStatement.setDouble(5, reservation.getPrixBillet()); // Mettre à jour le prix du billet
            preparedStatement.setInt(6, reservation.getIdReservation());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer une réservation
    public static void delete(Connection connection, int idReservation) {
        String query = "DELETE FROM reservation WHERE id_reservation = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idReservation);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
