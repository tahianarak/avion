package avion.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Avion {

    private int idAvion;
    private int idModeleAvion;

    // Constructeur
    public Avion(int idAvion, int idModeleAvion) {
        this.idAvion = idAvion;
        this.idModeleAvion = idModeleAvion;
    }

    // Getters et Setters
    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public int getIdModeleAvion() {
        return idModeleAvion;
    }

    public void setIdModeleAvion(int idModeleAvion) {
        this.idModeleAvion = idModeleAvion;
    }

    // Méthode pour obtenir tous les avions
    public static List<Avion> getAll(Connection connection) {
        List<Avion> avions = new ArrayList<>();
        String query = "SELECT * FROM avion";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idAvion = resultSet.getInt("id_avion");
                int idModeleAvion = resultSet.getInt("id_modele_avion");

                Avion avion = new Avion(idAvion, idModeleAvion);
                avions.add(avion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return avions;
    }

    // Méthode pour insérer un avion
    public static void insert(Connection connection, Avion avion) {
        String query = "INSERT INTO avion (id_modele_avion) VALUES (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, avion.getIdModeleAvion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour un avion
    public static void update(Connection connection, Avion avion) {
        String query = "UPDATE avion SET id_modele_avion = ? WHERE id_avion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, avion.getIdModeleAvion());
            preparedStatement.setInt(2, avion.getIdAvion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un avion
    public static void delete(Connection connection, int idAvion) {
        String query = "DELETE FROM avion WHERE id_avion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idAvion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
