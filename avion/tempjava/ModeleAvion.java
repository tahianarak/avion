package avion.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeleAvion {

    private int idModeleAvion;
    private String description;
    private String dateFabrication;


    public ModeleAvion(int idModeleAvion, String description, String dateFabrication) {
        this.idModeleAvion = idModeleAvion;
        this.description = description;
        this.dateFabrication = dateFabrication;
    }


    public int getIdModeleAvion() {
        return idModeleAvion;
    }

    public void setIdModeleAvion(int idModeleAvion) {
        this.idModeleAvion = idModeleAvion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateFabrication() {
        return dateFabrication;
    }

    public void setDateFabrication(String dateFabrication) {
        this.dateFabrication = dateFabrication;
    }

    // Méthode pour obtenir un modèle d'avion par son ID
    public static ModeleAvion getById(Connection connection, int idModeleAvion) {
        ModeleAvion modeleAvion = null;
        String query = "SELECT * FROM modele_avion WHERE id_modele_avion = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idModeleAvion);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String description = resultSet.getString("description");
                    String dateFabrication = resultSet.getString("date_fabrication");

                    modeleAvion = new ModeleAvion(idModeleAvion, description, dateFabrication);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modeleAvion;
    }

    // Méthode pour obtenir tous les modèles d'avion
    public static List<ModeleAvion> getAll(Connection connection) {
        List<ModeleAvion> modelesAvion = new ArrayList<>();
        String query = "SELECT * FROM modele_avion";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idModeleAvion = resultSet.getInt("id_modele_avion");
                String description = resultSet.getString("description");
                String dateFabrication = resultSet.getString("date_fabrication");

                ModeleAvion modeleAvion = new ModeleAvion(idModeleAvion, description, dateFabrication);
                modelesAvion.add(modeleAvion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modelesAvion;
    }

    // Méthode pour insérer un modèle d'avion
    public static void insert(Connection connection, ModeleAvion modeleAvion) {
        String query = "INSERT INTO modele_avion (description, date_fabrication) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, modeleAvion.getDescription());
            preparedStatement.setString(2, modeleAvion.getDateFabrication());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour un modèle d'avion
    public static void update(Connection connection, ModeleAvion modeleAvion) {
        String query = "UPDATE modele_avion SET description = ?, date_fabrication = ? WHERE id_modele_avion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, modeleAvion.getDescription());
            preparedStatement.setString(2, modeleAvion.getDateFabrication());
            preparedStatement.setInt(3, modeleAvion.getIdModeleAvion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un modèle d'avion
    public static void delete(Connection connection, int idModeleAvion) {
        String query = "DELETE FROM modele_avion WHERE id_modele_avion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idModeleAvion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
