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

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double prixUnitaire;

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    double remise;
    public int getPlaceEnPromotion() {
        return placeEnPromotion;
    }

    public void setPlaceEnPromotion(int placeEnPromotion) {
        this.placeEnPromotion = placeEnPromotion;
    }

    int placeEnPromotion;
    public TypeSiege getTypeSiege() {
        return typeSiege;
    }

    public void setTypeSiege(TypeSiege typeSiege) {
        this.typeSiege = typeSiege;
    }

    TypeSiege typeSiege;

    public Vol getVol() {
        return vol;
    }

    public void setVol(Vol vol) {
        this.vol = vol;
    }

    Vol vol;

    public Reservation(){}
    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    int nbPlace;

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

    public static List<Reservation> getByIdUser(Connection connection, int idUser) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM reservation WHERE id_user = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUser);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idReservation = resultSet.getInt("id_reservation");
                    Timestamp dateReservation = resultSet.getTimestamp("date_reservation");
                    int idVol = resultSet.getInt("id_vol");
                    int idTypeSiege = resultSet.getInt("id_type_siege");
                    double prixBillet = resultSet.getDouble("prix_billet");
                    int nbPlace = resultSet.getInt("nb_place");

                    Vol vol=Vol.getById(connection,idVol);
                    TypeSiege typeSiege=TypeSiege.getById(connection,idTypeSiege);

                    Reservation reservation = new Reservation(idReservation, dateReservation, idUser, idVol, idTypeSiege, prixBillet);
                    reservation.setNbPlace(nbPlace);
                    reservation.setVol(vol);
                    reservation.setTypeSiege(typeSiege);
                    reservations.add(reservation);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    public static Reservation getByIdReservation(Connection connection, int idReservation) {
        Reservation reservation = null;
        String query = "SELECT * FROM reservation WHERE id_reservation = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idReservation);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int idRes = resultSet.getInt("id_reservation");
                    Timestamp dateRes = resultSet.getTimestamp("date_reservation");
                    int idUser = resultSet.getInt("id_user");
                    int idVol = resultSet.getInt("id_vol");
                    int idTypeSiege = resultSet.getInt("id_type_siege");
                    double prixBillet = resultSet.getDouble("prix_billet");
                    int nbPlace = resultSet.getInt("nb_place");
                    int nbPlacePromotion=resultSet.getInt("place_en_promotion");

                    reservation = new Reservation(idRes, dateRes, idUser, idVol, idTypeSiege, prixBillet);
                    reservation.setNbPlace(nbPlace);
                    reservation.setPlaceEnPromotion(nbPlacePromotion);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservation;
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
        String query = "INSERT INTO reservation (date_reservation, id_user, id_vol, id_type_siege, prix_billet,nb_place,place_en_promotion,remise,prix_unitaire) VALUES (?, ?, ?, ?, ?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, reservation.getDateReservation());
            preparedStatement.setInt(2, reservation.getIdUser());
            preparedStatement.setInt(3, reservation.getIdVol());
            preparedStatement.setInt(4, reservation.getIdTypeSiege());
            preparedStatement.setDouble(5, reservation.getPrixBillet()); // Insérer le prix du billet
            preparedStatement.setInt(6,reservation.getNbPlace());
            preparedStatement.setInt(7,reservation.getPlaceEnPromotion());
            preparedStatement.setDouble(8,reservation.getRemise());
            preparedStatement.setDouble(9,reservation.getPrixUnitaire());
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
