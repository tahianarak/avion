<%@page import="avion.model.*, java.util.List"%>
<%
    List<Avion> avions = (List<Avion>) request.getAttribute("avions");
    List<VilleDesservie> villes = (List<VilleDesservie>) request.getAttribute("villes");
    List<TypeSiege> sieges = (List<TypeSiege>) request.getAttribute("sieges");

    Vol vol = null;
    if (request.getAttribute("vol") != null) {
        vol = (Vol) request.getAttribute("vol");
    }
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Création d'un Vol - Réservation de Billets d'Avion</title>
</head>
<body>
<jsp:include page="sideBar.jsp" />
<div class="vol-container">
    <h2>Créer un Vol</h2>

    <!-- Formulaire de création de vol -->
    <form action="insertVol" method="POST">
        <div class="form-layout">
            <!-- Informations sur le vol (gauche) -->
            <div class="left-column">
                <div class="vol-details">
                    <!-- Champ pour la date du vol -->
                    <div class="input-group">
                        <label for="dateVol">Date du Vol</label>
                        <input type="datetime-local" id="dateVol" name="dateVol" required value="<% if (vol != null) { out.print(vol.getDateVol()); } %>">
                    </div>

                    <!-- Champ pour la description -->
                    <div class="input-group">
                        <label for="description">Description</label>
                        <input type="text" value="<% if (vol != null) { out.print(vol.getDescription()); } %>" id="description" name="description" placeholder="Description du vol" required>
                    </div>

                    <!-- Ville de départ -->
                    <div class="input-group">
                        <label for="idVilleDepart">Ville de départ</label>
                        <select name="idVilleDepart">
                            <% if (vol != null) { %>
                                <option value="<%= vol.getVilleDepart().getIdVille() %>"><%= vol.getVilleDepart().getDescription() %></option>
                            <% } %>
                            <% for (VilleDesservie ville : villes) { %>
                                <option value="<%= ville.getIdVille() %>"><%= ville.getDescription() %></option>
                            <% } %>
                        </select>
                    </div>

                    <!-- Ville d'arrivée -->
                    <div class="input-group">
                        <label for="idVilleArrivee">Ville d'arrivée</label>
                        <select name="idVilleArrivee">
                            <% if (vol != null) { %>
                                <option value="<%= vol.getVilleArrivee().getIdVille() %>"><%= vol.getVilleArrivee().getDescription() %></option>
                            <% } %>
                            <% for (VilleDesservie ville : villes) { %>
                                <option value="<%= ville.getIdVille() %>"><%= ville.getDescription() %></option>
                            <% } %>
                        </select>
                    </div>

                    <!-- ID de l'avion -->
                    <div class="input-group">
                        <label for="idAvion">ID de l'Avion</label>
                        <select name="idAvion">
                            <% if (vol != null) { %>
                                <option value="<%= vol.getIdAvion() %>"><%= vol.getIdAvion() %></option>
                            <% } %>
                            <% for (Avion avion : avions) { %>
                                <option value="<%= avion.getIdAvion() %>">Avion numéro <%= avion.getIdAvion() %></option>
                            <% } %>
                        </select>
                    </div>

                    <!-- Durée -->
                    <div class="input-group">
                        <label for="duree">Durée</label>
                        <input type="time" id="duree" name="duree" value="<% if (vol != null) { out.print(vol.getDuree()); } %>" required>
                    </div>
                </div>
            </div>

            <!-- Informations sur le prix et promotions (droite) -->
            <div class="right-column">
                <h3>Prix et Promotions des Places par Type de Siège</h3>
                <% for (TypeSiege siege : sieges) { %>
                    <div class="siege-box">
                        <h4><%= siege.getDescription() %></h4>
                        <!-- Prix pour chaque type de siège -->
                        <div class="input-group">
                            <label for="prix_<%= siege.getIdTypeSiege() %>">Prix :</label>
                            <input type="number" id="prix_<%= siege.getIdTypeSiege() %>" name="prix_<%= siege.getIdTypeSiege() %>" min="0" step="0.01" placeholder="Prix pour <%= siege.getDescription() %>" required>
                        </div>

                        <!-- Nombre de places en promotion -->
                        <div class="input-group">
                            <label for="nbPlacesPromo_<%= siege.getIdTypeSiege() %>">Places en promo :</label>
                            <input type="number" id="nbPlacesPromo_<%= siege.getIdTypeSiege() %>" name="nbPlacesPromo_<%= siege.getIdTypeSiege() %>" min="0" placeholder="Nombre de places en promo" required>
                        </div>

                        <!-- Pourcentage de réduction -->
                        <div class="input-group">
                            <label for="pourcentagePromo_<%= siege.getIdTypeSiege() %>">Pourcentage promo :</label>
                            <input type="number" id="pourcentagePromo_<%= siege.getIdTypeSiege() %>" name="pourcentagePromo_<%= siege.getIdTypeSiege() %>" min="0" max="100" step="1" placeholder="Pourcentage de promo" required>
                        </div>
                    </div>
                <% } %>
            </div>
        </div>

        <!-- Champ caché pour l'ID du vol si nécessaire -->
        <% if (vol != null) { %>
            <input type="hidden" name="idVol" value="<%= vol.getIdVol() %>">
        <% } %>

        <!-- Bouton de soumission -->
        <button type="submit" class="btn-submit">Créer le Vol</button>
    </form>
</div>

<!-- CSS -->
<style>
    /* Style général de la page */
    body {
        font-family: 'Arial', sans-serif;
        background: linear-gradient(135deg, #00bcd4, #009688); /* Dégradé bleu-vert */
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: flex-start; /* Alignement vers le haut */
        height: 100vh;
    }

    /* Conteneur principal du formulaire */
    .vol-container {
        background-color: rgba(255, 255, 255, 0.9); /* Fond semi-transparent */
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
        width: 80%; /* Rendre plus large */
        max-width: 1200px; /* Limiter la largeur maximale */
        margin-left: 15%; /* Déplacer encore plus vers la droite */
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
    }

    h2 {
        color: #00796b;
        font-size: 24px;
        margin-bottom: 30px;
        font-weight: 600;
        width: 100%;
        text-align: center;
    }

    /* Disposition des colonnes */
    .form-layout {
        display: flex;
        justify-content: space-between;
        width: 100%;
        gap: 40px; /* Espacement entre les colonnes */
    }

    .left-column, .right-column {
        width: 48%; /* Augmenter la largeur des colonnes */
        padding: 20px;
        box-sizing: border-box;
    }

    .right-column {
        padding-left: 40px;
    }

    .vol-details {
        background-color: #f9f9f9;
        padding: 20px;
        border-radius: 10px;
        border: 1px solid #ddd;
        margin-bottom: 30px;
    }

    /* Boîtes pour chaque type de siège */
    .siege-box {
        background-color: #f2f2f2;
        padding: 20px;
        margin-bottom: 20px;
        border-radius: 10px;
        border: 1px solid #ddd;
    }

    .siege-box h4 {
        margin-bottom: 15px;
        color: #00796b;
    }

    .input-group {
        margin-bottom: 15px;
        text-align: left;
    }

    label {
        font-size: 14px;
        color: #00796b;
        font-weight: 600;
    }

    input[type="datetime-local"],
    input[type="text"],
    input[type="number"],
    input[type="time"] {
        width: 100%;
        padding: 14px;
        margin-top: 8px;
        border-radius: 8px;
        border: 1px solid #ccc;
        font-size: 16px;
        box-sizing: border-box;
    }

    input[type="datetime-local"]:focus,
    input[type="text"]:focus,
    input[type="number"]:focus,
    input[type="time"]:focus {
        border-color: #00796b;
        outline: none;
    }

    .btn-submit {
        width: 100%;
        padding: 14px;
        background-color: #00796b;
        color: white;
        border: none;
        border-radius: 8px;
        font-size: 18px;
        cursor: pointer;
        transition: background-color 0.3s;
    }

    .btn-submit:hover {
        background-color: #004d40;
    }
</style>

</body>
</html>
