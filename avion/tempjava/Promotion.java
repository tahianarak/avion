package avion.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Promotion {

    private int idPromotion;
    private int idVol;
    private int idTypeSiege;
    private double remise;
    private int nbPlace;

    // Constructeur
    public Promotion(int idPromotion, int idVol, int idTypeSiege, double remise, int nbPlace) {
        this.idPromotion = idPromotion;
        this.idVol = idVol;
        this.idTypeSiege = idTypeSiege;
        this.remise = remise;
        this.nbPlace = nbPlace;
    }

    // Getters et Setters
    public int getIdPromotion() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion = idPromotion;
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

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    // Méthode pour obtenir toutes les promotions
    public static List<Promotion> getAll(Connection connection) {
        List<Promotion> promotions = new ArrayList<>();
        String query = "SELECT * FROM promotion";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idPromotion = resultSet.getInt("id_promotion");
                int idVol = resultSet.getInt("id_vol");
                int idTypeSiege = resultSet.getInt("id_type_siege");
                double remise = resultSet.getDouble("remise");
                int nbPlace = resultSet.getInt("nb_place");

                Promotion promotion = new Promotion(idPromotion, idVol, idTypeSiege, remise, nbPlace);
                promotions.add(promotion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return promotions;
    }

    // Méthode pour insérer une promotion
    public static void insert(Connection connection, Promotion promotion) {
        String query = "INSERT INTO promotion (id_vol, id_type_siege, remise, nb_place) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, promotion.getIdVol());
            preparedStatement.setInt(2, promotion.getIdTypeSiege());
            preparedStatement.setDouble(3, promotion.getRemise());
            preparedStatement.setInt(4, promotion.getNbPlace());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour une promotion
    public static void update(Connection connection, Promotion promotion) {
        String query = "UPDATE promotion SET id_vol = ?, id_type_siege = ?, remise = ?, nb_place = ? WHERE id_promotion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, promotion.getIdVol());
            preparedStatement.setInt(2, promotion.getIdTypeSiege());
            preparedStatement.setDouble(3, promotion.getRemise());
            preparedStatement.setInt(4, promotion.getNbPlace());
            preparedStatement.setInt(5, promotion.getIdPromotion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer une promotion
    public static void delete(Connection connection, int idPromotion) {
        String query = "DELETE FROM promotion WHERE id_promotion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idPromotion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
