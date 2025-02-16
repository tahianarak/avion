package avion.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Vol {

    private int idVol;
    private Timestamp dateVol;
    private String description;
    private int idVilleDepart;
    private int idVilleArrivee;
    private int idAvion;
    private Time duree;

    // Constructeur
    public Vol(int idVol, Timestamp dateVol, String description, int idVilleDepart, int idVilleArrivee, int idAvion, Time duree) {
        this.idVol = idVol;
        this.dateVol = dateVol;
        this.description = description;
        this.idVilleDepart = idVilleDepart;
        this.idVilleArrivee = idVilleArrivee;
        this.idAvion = idAvion;
        this.duree = duree;
    }

    // Getters et Setters
    public int getIdVol() {
        return idVol;
    }

    public void setIdVol(int idVol) {
        this.idVol = idVol;
    }

    public Timestamp getDateVol() {
        return dateVol;
    }

    public void setDateVol(Timestamp dateVol) {
        this.dateVol = dateVol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdVilleDepart() {
        return idVilleDepart;
    }

    public void setIdVilleDepart(int idVilleDepart) {
        this.idVilleDepart = idVilleDepart;
    }

    public int getIdVilleArrivee() {
        return idVilleArrivee;
    }

    public void setIdVilleArrivee(int idVilleArrivee) {
        this.idVilleArrivee = idVilleArrivee;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public Time getDuree() {
        return duree;
    }

    public void setDuree(Time duree) {
        this.duree = duree;
    }

    // Méthode pour obtenir tous les vols
    public static List<Vol> getAll(Connection connection) {
        List<Vol> vols = new ArrayList<>();
        String query = "SELECT * FROM vol";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idVol = resultSet.getInt("id_vol");
                Timestamp dateVol = resultSet.getTimestamp("date_vol");
                String description = resultSet.getString("description");
                int idVilleDepart = resultSet.getInt("id_ville_depart");
                int idVilleArrivee = resultSet.getInt("id_ville_arrivee");
                int idAvion = resultSet.getInt("id_avion");
                Time duree = resultSet.getTime("duree");

                Vol vol = new Vol(idVol, dateVol, description, idVilleDepart, idVilleArrivee, idAvion, duree);
                vols.add(vol);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vols;
    }

    // Méthode pour insérer un vol
    public static void insert(Connection connection, Vol vol) {
        String query = "INSERT INTO vol (date_vol, description, id_ville_depart, id_ville_arrivee, id_avion, duree) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, vol.getDateVol());
            preparedStatement.setString(2, vol.getDescription());
            preparedStatement.setInt(3, vol.getIdVilleDepart());
            preparedStatement.setInt(4, vol.getIdVilleArrivee());
            preparedStatement.setInt(5, vol.getIdAvion());
            preparedStatement.setTime(6, vol.getDuree());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour un vol
    public static void update(Connection connection, Vol vol) {
        String query = "UPDATE vol SET date_vol = ?, description = ?, id_ville_depart = ?, id_ville_arrivee = ?, id_avion = ?, duree = ? WHERE id_vol = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, vol.getDateVol());
            preparedStatement.setString(2, vol.getDescription());
            preparedStatement.setInt(3, vol.getIdVilleDepart());
            preparedStatement.setInt(4, vol.getIdVilleArrivee());
            preparedStatement.setInt(5, vol.getIdAvion());
            preparedStatement.setTime(6, vol.getDuree());
            preparedStatement.setInt(7, vol.getIdVol());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un vol
    public static void delete(Connection connection, int idVol) {
        String query = "DELETE FROM vol WHERE id_vol = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idVol);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

