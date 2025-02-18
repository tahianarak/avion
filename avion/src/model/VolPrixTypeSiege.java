package avion.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VolPrixTypeSiege {

    private int idTypeSiege;
    private int idVol;
    private double prixUnitaire;


    public static HashMap<Integer, VolPrixTypeSiege> getPrixByVol(Connection connection, int idVol) {
        HashMap<Integer, VolPrixTypeSiege> volPrixTypeSiegesMap = new HashMap<>();
        String query = "SELECT * FROM vol_prix_type_siege WHERE id_vol = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idVol);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idTypeSiege = resultSet.getInt("id_type_siege");
                    double prixUnitaire = resultSet.getDouble("prix_unitaire");

                    VolPrixTypeSiege volPrixTypeSiege = new VolPrixTypeSiege(idTypeSiege, idVol, prixUnitaire);
                    volPrixTypeSiegesMap.put(idTypeSiege, volPrixTypeSiege); // Ajout dans le HashMap avec idTypeSiege comme clé
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return volPrixTypeSiegesMap;
    }



    // Constructeur
    public VolPrixTypeSiege(int idTypeSiege, int idVol, double prixUnitaire) {
        this.idTypeSiege = idTypeSiege;
        this.idVol = idVol;
        this.prixUnitaire = prixUnitaire;
    }

    // Getters et Setters
    public int getIdTypeSiege() {
        return idTypeSiege;
    }

    public void setIdTypeSiege(int idTypeSiege) {
        this.idTypeSiege = idTypeSiege;
    }

    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    // Méthode pour obtenir tous les prix pour les types de sièges sur un vol
    public static List<VolPrixTypeSiege> getAll(Connection connection) {
        List<VolPrixTypeSiege> volPrixTypeSieges = new ArrayList<>();
        String query = "SELECT * FROM vol_prix_type_siege";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idTypeSiege = resultSet.getInt("id_type_siege");
                int idVol = resultSet.getInt("id_vol");
                double prixUnitaire = resultSet.getDouble("prix_unitaire");

                VolPrixTypeSiege volPrixTypeSiege = new VolPrixTypeSiege(idTypeSiege, idVol, prixUnitaire);
                volPrixTypeSieges.add(volPrixTypeSiege);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return volPrixTypeSieges;
    }

    // Méthode pour insérer un prix pour un type de siège sur un vol
    public static void insert(Connection connection, VolPrixTypeSiege volPrixTypeSiege) {
        String query = "INSERT INTO vol_prix_type_siege (id_type_siege, id_vol, prix_unitaire) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, volPrixTypeSiege.getIdTypeSiege());
            preparedStatement.setInt(2, volPrixTypeSiege.getIdVol());
            preparedStatement.setDouble(3, volPrixTypeSiege.getPrixUnitaire());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour le prix d'un type de siège sur un vol
    public static void update(Connection connection, VolPrixTypeSiege volPrixTypeSiege) {
        String query = "UPDATE vol_prix_type_siege SET prix_unitaire = ? WHERE id_type_siege = ? AND id_vol = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, volPrixTypeSiege.getPrixUnitaire());
            preparedStatement.setInt(2, volPrixTypeSiege.getIdTypeSiege());
            preparedStatement.setInt(3, volPrixTypeSiege.getIdVol());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un prix pour un type de siège sur un vol
    public static void delete(Connection connection, int idTypeSiege, int idVol) {
        String query = "DELETE FROM vol_prix_type_siege WHERE id_type_siege = ? AND id_vol = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idTypeSiege);
            preparedStatement.setInt(2, idVol);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
