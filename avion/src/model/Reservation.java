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

    // Constructeur
    public Reservation(int idReservation, Timestamp dateReservation, int idUser, int idVol, int idTypeSiege) {
        this.idReservation = idReservation;
        this.dateReservation = dateReservation;
        this.idUser = idUser;
        this.idVol = idVol;
        this.idTypeSiege = idTypeSiege;
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

    // Méthode pour obtenir toutes les réservations
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

                Reservation reservation = new Reservation(idReservation, dateReservation, idUser, idVol, idTypeSiege);
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Méthode pour insérer une réservation
    public static void insert(Connection connection, Reservation reservation) {
        String query = "INSERT INTO reservation (date_reservation, id_user, id_vol, id_type_siege) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, reservation.getDateReservation());
            preparedStatement.setInt(2, reservation.getIdUser());
            preparedStatement.setInt(3, reservation.getIdVol());
            preparedStatement.setInt(4, reservation.getIdTypeSiege());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour une réservation
    public static void update(Connection connection, Reservation reservation) {
        String query = "UPDATE reservation SET date_reservation = ?, id_user = ?, id_vol = ?, id_type_siege = ? WHERE id_reservation = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, reservation.getDateReservation());
            preparedStatement.setInt(2, reservation.getIdUser());
            preparedStatement.setInt(3, reservation.getIdVol());
            preparedStatement.setInt(4, reservation.getIdTypeSiege());
            preparedStatement.setInt(5, reservation.getIdReservation());
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
