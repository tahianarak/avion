package avion.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VilleDesservie {

    private int idVille;
    private String description;

    // Constructeur
    public VilleDesservie(int idVille, String description) {
        this.idVille = idVille;
        this.description = description;
    }

    // Getters et Setters
    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Méthode pour obtenir toutes les villes desservies
    public static List<VilleDesservie> getAll(Connection connection) {
        List<VilleDesservie> villesDesservies = new ArrayList<>();
        String query = "SELECT * FROM ville_desservie";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idVille = resultSet.getInt("id_ville");
                String description = resultSet.getString("description");

                VilleDesservie villeDesservie = new VilleDesservie(idVille, description);
                villesDesservies.add(villeDesservie);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return villesDesservies;
    }

    // Méthode pour insérer une ville desservie
    public static void insert(Connection connection, VilleDesservie villeDesservie) {
        String query = "INSERT INTO ville_desservie (description) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, villeDesservie.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour une ville desservie
    public static void update(Connection connection, VilleDesservie villeDesservie) {
        String query = "UPDATE ville_desservie SET description = ? WHERE id_ville = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, villeDesservie.getDescription());
            preparedStatement.setInt(2, villeDesservie.getIdVille());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer une ville desservie
    public static void delete(Connection connection, int idVille) {
        String query = "DELETE FROM ville_desservie WHERE id_ville = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idVille);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

