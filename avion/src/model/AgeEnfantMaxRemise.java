package avion.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgeEnfantMaxRemise {

    private int id;
    private int ageMax;
    private double remise;
    private Date dateEns;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public Date getDateEns() {
        return dateEns;
    }

    public void setDateEns(Date dateEns) {
        this.dateEns = dateEns;
    }

    // Méthode pour obtenir un AgeEnfantMaxRemise par son id
    public static AgeEnfantMaxRemise getById(int id, Connection connection) throws SQLException {
        String sql = "SELECT * FROM age_enfant_max_remise WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAgeEnfantMaxRemise(rs);
                }
            }
        }
        return null;
    }

    // Méthode pour obtenir toutes les entrées
    public static List<AgeEnfantMaxRemise> getAll(Connection connection) throws SQLException {
        String sql = "SELECT * FROM age_enfant_max_remise";
        List<AgeEnfantMaxRemise> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapResultSetToAgeEnfantMaxRemise(rs));
            }
        }
        return list;
    }

    // Méthode pour obtenir la dernière entrée par date
    public static AgeEnfantMaxRemise getLastByDate(Connection connection,java.sql.Date date) throws SQLException {
        String sql = "SELECT * FROM age_enfant_max_remise where date_ens<=? ORDER BY date_ens DESC LIMIT 1";
        ResultSet rs=null;
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setDate(1,date);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToAgeEnfantMaxRemise(rs);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            rs.close();
        }
        return null;
    }

    // Méthode pour insérer un nouvel enregistrement
    public static void insert(AgeEnfantMaxRemise ageEnfantMaxRemise, Connection connection) throws SQLException {
        String sql = "INSERT INTO age_enfant_max_remise (age_max, remise, date_ens) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ageEnfantMaxRemise.getAgeMax());
            stmt.setDouble(2, ageEnfantMaxRemise.getRemise()); // Utilisation de setDouble
            stmt.setDate(3, ageEnfantMaxRemise.getDateEns());
            stmt.executeUpdate();
        }
    }

    // Méthode pour mettre à jour un enregistrement
    public static void update(AgeEnfantMaxRemise ageEnfantMaxRemise, Connection connection) throws SQLException {
        String sql = "UPDATE age_enfant_max_remise SET age_max = ?, remise = ?, date_ens = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ageEnfantMaxRemise.getAgeMax());
            stmt.setDouble(2, ageEnfantMaxRemise.getRemise()); // Utilisation de setDouble
            stmt.setDate(3, ageEnfantMaxRemise.getDateEns());
            stmt.setInt(4, ageEnfantMaxRemise.getId());
            stmt.executeUpdate();
        }
    }

    // Méthode pour supprimer un enregistrement
    public static void delete(int id, Connection connection) throws SQLException {
        String sql = "DELETE FROM age_enfant_max_remise WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Méthode privée pour mapper le ResultSet à l'objet AgeEnfantMaxRemise
    private static AgeEnfantMaxRemise mapResultSetToAgeEnfantMaxRemise(ResultSet rs) throws SQLException {
        AgeEnfantMaxRemise ageEnfantMaxRemise = new AgeEnfantMaxRemise();
        ageEnfantMaxRemise.setId(rs.getInt("id"));
        ageEnfantMaxRemise.setAgeMax(rs.getInt("age_max"));
        ageEnfantMaxRemise.setRemise(rs.getDouble("remise")); // Utilisation de getDouble
        ageEnfantMaxRemise.setDateEns(rs.getDate("date_ens"));
        return ageEnfantMaxRemise;
    }
}
