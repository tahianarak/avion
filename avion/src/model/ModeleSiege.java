package avion.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeleSiege {

    private int idTypeSiege;
    private int idModeleAvion;
    private int nombreSiege;

    // Constructeur
    public ModeleSiege(int idTypeSiege, int idModeleAvion, int nombreSiege) {
        this.idTypeSiege = idTypeSiege;
        this.idModeleAvion = idModeleAvion;
        this.nombreSiege = nombreSiege;
    }

    // Getters et Setters
    public int getIdTypeSiege() {
        return idTypeSiege;
    }

    public void setIdTypeSiege(int idTypeSiege) {
        this.idTypeSiege = idTypeSiege;
    }

    public int getIdModeleAvion() {
        return idModeleAvion;
    }

    public void setIdModeleAvion(int idModeleAvion) {
        this.idModeleAvion = idModeleAvion;
    }

    public int getNombreSiege() {
        return nombreSiege;
    }

    public void setNombreSiege(int nombreSiege) {
        this.nombreSiege = nombreSiege;
    }

    // Méthode pour obtenir tous les modèles de sièges

    public static List<ModeleSiege> getByModele(Connection connection,int idModeleAvion) {
        List<ModeleSiege> modeleSieges = new ArrayList<>();
        String query = "SELECT * FROM modele_siege where idModeleAvion="+idModeleAvion;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idTypeSiege = resultSet.getInt("id_type_siege");
                int nombreSiege = resultSet.getInt("nombre_siege");

                ModeleSiege modeleSiege = new ModeleSiege(idTypeSiege, idModeleAvion, nombreSiege);
                modeleSieges.add(modeleSiege);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modeleSieges;
    }

    public static List<ModeleSiege> getAll(Connection connection) {
        List<ModeleSiege> modeleSieges = new ArrayList<>();
        String query = "SELECT * FROM modele_siege";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idTypeSiege = resultSet.getInt("id_type_siege");
                int idModeleAvion = resultSet.getInt("id_modele_avion");
                int nombreSiege = resultSet.getInt("nombre_siege");

                ModeleSiege modeleSiege = new ModeleSiege(idTypeSiege, idModeleAvion, nombreSiege);
                modeleSieges.add(modeleSiege);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modeleSieges;
    }

    // Méthode pour insérer un modèle de siège
    public static void insert(Connection connection, ModeleSiege modeleSiege) {
        String query = "INSERT INTO modele_siege (id_type_siege, id_modele_avion, nombre_siege) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, modeleSiege.getIdTypeSiege());
            preparedStatement.setInt(2, modeleSiege.getIdModeleAvion());
            preparedStatement.setInt(3, modeleSiege.getNombreSiege());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour un modèle de siège
    public static void update(Connection connection, ModeleSiege modeleSiege) {
        String query = "UPDATE modele_siege SET nombre_siege = ? WHERE id_type_siege = ? AND id_modele_avion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, modeleSiege.getNombreSiege());
            preparedStatement.setInt(2, modeleSiege.getIdTypeSiege());
            preparedStatement.setInt(3, modeleSiege.getIdModeleAvion());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un modèle de siège
    public static void delete(Connection connection, int idTypeSiege, int idModeleAvion) {
        String query = "DELETE FROM modele_siege WHERE id_type_siege = ? AND id_modele_avion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idTypeSiege);
            preparedStatement.setInt(2, idModeleAvion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
