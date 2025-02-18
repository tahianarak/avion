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

    // Méthode pour obtenir une ville desservie par son id
    public static VilleDesservie getById(Connection connection, int idVille) {
        VilleDesservie villeDesservie = null;
        String query = "SELECT * FROM ville_desservie WHERE id_ville = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idVille);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String description = resultSet.getString("descritpion");
                    villeDesservie = new VilleDesservie(idVille, description);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return villeDesservie;
    }

    // Méthode pour obtenir toutes les villes desservies
    public static List<VilleDesservie> getAll(Connection connection) throws SQLException {


        List<VilleDesservie> villesDesservies = new ArrayList<>();
        String query = "SELECT * FROM ville_desservie";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idVille = resultSet.getInt("id_ville");
                String description = resultSet.getString("descritpion");

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
        String query = "INSERT INTO ville_desservie (descritpion) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, villeDesservie.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour une ville desservie
    public static void update(Connection connection, VilleDesservie villeDesservie) {
        String query = "UPDATE ville_desservie SET descritpion = ? WHERE id_ville = ?";
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

