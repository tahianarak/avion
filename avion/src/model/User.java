package avion.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int idUser;
    private String nomComplet;
    private String email;
    private boolean status;
    private String mdp;
    private String adresse;



    public static User authenticate(Connection connection, String email, String mdp) throws SQLException, Exception {
        String query = "SELECT * FROM users WHERE email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("mdp");

                    if (mdp.equals(storedPassword)) {
                        // Si le mot de passe correspond, on crée et renvoie l'objet User
                        int idUser = resultSet.getInt("id_user");
                        String nomComplet = resultSet.getString("nom_complet");
                        boolean status = resultSet.getBoolean("status");
                        String adresse = resultSet.getString("adresse");

                        return new User(idUser, nomComplet, email, status, storedPassword, adresse);
                    } else {
                        throw new Exception("Mot de passe incorrect");
                    }
                } else {
                    throw new Exception("Utilisateur non trouvé");
                }
            }
        }
    }

    // Constructeur
    public User(int idUser, String nomComplet, String email, boolean status, String mdp, String adresse) {
        this.idUser = idUser;
        this.nomComplet = nomComplet;
        this.email = email;
        this.status = status;
        this.mdp = mdp;
        this.adresse = adresse;
    }

    // Getters et Setters
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    // Méthode pour obtenir tous les utilisateurs
    public static List<User> getAll(Connection connection) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int idUser = resultSet.getInt("id_user");
                String nomComplet = resultSet.getString("nom_complet");
                String email = resultSet.getString("email");
                boolean status = resultSet.getBoolean("status");
                String mdp = resultSet.getString("mdp");
                String adresse = resultSet.getString("adresse");

                User user = new User(idUser, nomComplet, email, status, mdp, adresse);
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Méthode pour insérer un utilisateur
    public static void insert(Connection connection, User user) {
        String query = "INSERT INTO users (nom_complet, email, status, mdp, adresse) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getNomComplet());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setBoolean(3, user.isStatus());
            preparedStatement.setString(4, user.getMdp());
            preparedStatement.setString(5, user.getAdresse());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour mettre à jour un utilisateur
    public static void update(Connection connection, User user) {
        String query = "UPDATE users SET nom_complet = ?, email = ?, status = ?, mdp = ?, adresse = ? WHERE id_user = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getNomComplet());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setBoolean(3, user.isStatus());
            preparedStatement.setString(4, user.getMdp());
            preparedStatement.setString(5, user.getAdresse());
            preparedStatement.setInt(6, user.getIdUser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour supprimer un utilisateur
    public static void delete(Connection connection, int idUser) {
        String query = "DELETE FROM users WHERE id_user = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

