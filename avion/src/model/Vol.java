package avion.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Vol {

    private int idVol;
    private Timestamp dateVol;
    private String description;



    public Vol(){}


    public VilleDesservie getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(VilleDesservie villeDepart) {
        this.villeDepart = villeDepart;
    }

    public VilleDesservie getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(VilleDesservie villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    VilleDesservie villeDepart;
    VilleDesservie villeArrivee;

    Avion avion;
    private int idVilleDepart;
    private int idVilleArrivee;
    private int idAvion;
    private Time duree;


    List<Promotion> promotions;
    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }


    public static  int getPlaceRestante(Connection connection,int idVol,int idTypeSiege)throws Exception
    {
        String sql="select place_restant from siege_restant_vol where id_vol=? and id_type_siege=?";
        int ans=-1;
        try(PreparedStatement statement=connection.prepareStatement(sql))
        {
            statement.setInt(1,idVol);
            statement.setInt(2,idTypeSiege);

            try(ResultSet rs=statement.executeQuery())
            {
                if(rs.next())
                {
                    ans=rs.getInt("place_restant");
                }
            }
        }
        return  ans;
    }

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


    public static List<Vol> filterVols(Connection connection, java.sql.Date dateMin, java.sql.Date dateMax, int idVilleDepart, int idModeleAvion) {
        List<Vol> filteredVols = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM vue_vol_avion WHERE 1=1");

        // Ajouter des conditions de filtrage
        if (dateMin != null) {
            query.append(" AND date_vol >= ?");
        }
        if (dateMax != null) {
            query.append(" AND date_vol <= ?");
        }
        if (idVilleDepart > 0) {
            query.append(" AND id_ville_depart = ?");
        }
        if (idModeleAvion > 0) {
            query.append(" AND id_modele_avion = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int index = 1;

            // Ajouter les paramètres à la requête préparée
            if (dateMin != null) {
                preparedStatement.setDate(index++, dateMin);
            }
            if (dateMax != null) {
                preparedStatement.setDate(index++, dateMax);
            }
            if (idVilleDepart > 0) {
                preparedStatement.setInt(index++, idVilleDepart);
            }
            if (idModeleAvion > 0) {
                preparedStatement.setInt(index++, idModeleAvion);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int idVol = resultSet.getInt("id_vol");
                    Timestamp dateVol = resultSet.getTimestamp("date_vol");  // Utilisation de java.sql.Date
                    String description = resultSet.getString("vol_description");
                    int villeDepartId = resultSet.getInt("id_ville_depart");
                    int villeArriveeId = resultSet.getInt("id_ville_arrivee");
                    int avionId = resultSet.getInt("id_avion");
                    Time duree = resultSet.getTime("duree");

                    // Créer l'objet Vol avec les données récupérées
                    Vol vol = new Vol(idVol, dateVol, description, villeDepartId, villeArriveeId, avionId, duree);
                    vol.setVilleDepart(VilleDesservie.getById(connection, villeDepartId));
                    vol.setVilleArrivee(VilleDesservie.getById(connection, villeArriveeId));
                    vol.setAvion(Avion.getByIdAvion(connection, avionId));

                    filteredVols.add(vol); // Ajouter le vol filtré à la liste
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredVols; // Retourner la liste des vols filtrés
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
                vol.setVilleArrivee(VilleDesservie.getById(connection,idVilleArrivee));
                vol.setVilleDepart(VilleDesservie.getById(connection,idVilleDepart));
                vol.setAvion(Avion.getByIdAvion(connection,idAvion));
                vols.add(vol);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vols;
    }

    public static Vol getById(Connection connection, int idVol) throws SQLException {
        Vol vol = null;
        String query = "SELECT * FROM vol WHERE id_vol = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Définir l'ID du vol dans la requête
            preparedStatement.setInt(1, idVol);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Récupérer les informations du vol depuis le ResultSet
                    Timestamp dateVol = resultSet.getTimestamp("date_vol");
                    String description = resultSet.getString("description");
                    int idVilleDepart = resultSet.getInt("id_ville_depart");
                    int idVilleArrivee = resultSet.getInt("id_ville_arrivee");
                    int idAvion = resultSet.getInt("id_avion");
                    Time duree = resultSet.getTime("duree");

                    // Créer un objet Vol avec les données récupérées
                    vol = new Vol(idVol, dateVol, description, idVilleDepart, idVilleArrivee, idAvion, duree);

                    // Récupérer les objets associés à ce vol
                    vol.setVilleDepart(VilleDesservie.getById(connection, idVilleDepart));
                    vol.setVilleArrivee(VilleDesservie.getById(connection, idVilleArrivee));
                    vol.setAvion(Avion.getByIdAvion(connection, idAvion));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de la récupération du vol par ID", e);
        }

        return vol; // Retourne le vol trouvé ou null si aucun vol n'a été trouvé
    }

    // Méthode pour insérer un vol
    public static int insert(Connection connection, Vol vol) throws SQLException {

        String query = "INSERT INTO vol (date_vol, description, id_ville_depart, id_ville_arrivee, id_avion, duree) VALUES (?, ?, ?, ?, ?, ?)";


        int generatedId = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, vol.getDateVol());
            preparedStatement.setString(2, vol.getDescription());
            preparedStatement.setInt(3, vol.getIdVilleDepart());
            preparedStatement.setInt(4, vol.getIdVilleArrivee());
            preparedStatement.setInt(5, vol.getIdAvion());
            preparedStatement.setTime(6, vol.getDuree());

            // Exécute la requête d'insertion
            preparedStatement.executeUpdate();

            // Récupère les clés générées automatiquement
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1); // Récupère le premier (et unique) ID généré
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erreur lors de l'insertion du vol", e);
        }

        return generatedId; // Retourne l'ID généré
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

