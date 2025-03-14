package avion.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeSiege {

    private int idTypeSiege;
    private String description;


    public TypeSiege(int idTypeSiege, String description) {
        this.idTypeSiege = idTypeSiege;
        this.description = description;
    }


    public int getIdTypeSiege() {
        return idTypeSiege;
    }

    public void setIdTypeSiege(int idTypeSiege) {
        this.idTypeSiege = idTypeSiege;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static List<TypeSiege> getAll(Connection conn) throws SQLException {
        List<TypeSiege> typeSieges = new ArrayList<>();
        String query = "SELECT id_type_siege, description FROM type_siege";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id_type_siege");
                String description = rs.getString("description");
                typeSieges.add(new TypeSiege(id, description));
            }
        }
        return typeSieges;
    }


    public static boolean create(Connection conn, TypeSiege typeSiege) throws SQLException {
        String query = "INSERT INTO type_siege (description) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, typeSiege.getDescription());
            return stmt.executeUpdate() > 0;
        }
    }


    public static TypeSiege getById(Connection conn, int id) throws SQLException {
        String query = "SELECT id_type_siege, description FROM type_siege WHERE id_type_siege = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new TypeSiege(rs.getInt("id_type_siege"), rs.getString("description"));
                }
            }
        }
        return null;
    }

    public static boolean update(Connection conn, TypeSiege typeSiege) throws SQLException {
        String query = "UPDATE type_siege SET description = ? WHERE id_type_siege = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, typeSiege.getDescription());
            stmt.setInt(2, typeSiege.getIdTypeSiege());
            return stmt.executeUpdate() > 0;
        }
    }

    public static boolean delete(Connection conn, int id) throws SQLException {
        String query = "DELETE FROM type_siege WHERE id_type_siege = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }
}
