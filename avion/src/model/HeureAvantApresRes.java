package avion.model;

import java.sql.*;

public class HeureAvantApresRes {

    private Time heureAvRes;
    private Time heureApRes;

    // Constructeur
    public HeureAvantApresRes(Time heureAvRes, Time heureApRes) {
        this.heureAvRes = heureAvRes;
        this.heureApRes = heureApRes;
    }

    // Getters et Setters
    public Time getHeureAvRes() {
        return heureAvRes;
    }

    public void setHeureAvRes(Time heureAvRes) {
        this.heureAvRes = heureAvRes;
    }

    public Time getHeureApRes() {
        return heureApRes;
    }

    public void setHeureApRes(Time heureApRes) {
        this.heureApRes = heureApRes;
    }

    // Méthode toString pour afficher les valeurs
    @Override
    public String toString() {
        return "HeureAvantApresRes{" +
                "heureAvRes=" + heureAvRes +
                ", heureApRes=" + heureApRes +
                '}';
    }

    // CREATE: Insérer une nouvelle entrée dans la table
    public static void create(Connection conn, HeureAvantApresRes record) throws SQLException {
        String query = "INSERT INTO heure_avant_apres_res (heure_av_res, heure_ap_res) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTime(1, record.getHeureAvRes());
            stmt.setTime(2, record.getHeureApRes());
            stmt.executeUpdate();
        }
    }

    // READ: Lire une entrée de la table par un identifiant (ici, on suppose qu'il y a une colonne `id` dans la table)
    public static HeureAvantApresRes read(Connection conn) throws SQLException {
        String query = "SELECT * FROM heure_avant_apres_res";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Time heureAvRes = rs.getTime("heure_av_res");
                Time heureApRes = rs.getTime("heure_ap_res");
                return new HeureAvantApresRes(heureAvRes, heureApRes);
            }
        }
        return null; // Si l'entrée n'est pas trouvée
    }

    // UPDATE: Mettre à jour une entrée dans la table
    public static void update(Connection conn, HeureAvantApresRes newRecord) throws SQLException {
        String query = "UPDATE heure_avant_apres_res SET heure_av_res = ?, heure_ap_res = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setTime(1, newRecord.getHeureAvRes());
            stmt.setTime(2, newRecord.getHeureApRes());
            stmt.executeUpdate();
        }
    }

    // DELETE: Supprimer une entrée de la table
    public static void delete(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM heure_avant_apres_res WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}


