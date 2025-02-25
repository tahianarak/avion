<%@page import="avion.model.*, java.util.HashMap"%>
<%
    // Récupérer les prix des sièges pour un vol spécifique
    HashMap<Integer, VolPrixTypeSiege> prix = (HashMap<Integer, VolPrixTypeSiege>) request.getAttribute("prix");
    Vol vol = (Vol) request.getAttribute("vol");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sélection de Sièges</title>
</head>
<body>
<jsp:include page="sideBar.jsp" />

<div class="seats-container">
    <h2>Sélectionnez votre Siège</h2>

    <form action="insertReservation" method="post">
        <div class="flight-info">
            <p><strong>Vol:</strong> <%= vol.getVilleDepart().getDescription() %> -> <%= vol.getVilleArrivee().getDescription() %></p>
            <p><strong>Date de Vol:</strong> <%= vol.getDateVol() %></p>
        </div>
        <input type="hidden" name="res:idVol" value="<%=vol.getIdVol()%>">
        <div class="seats-group">
            <h3>Sélection des Sièges</h3>
            <table>
                <thead>
                    <tr>
                        <th>Type de Siège</th>
                        <th>Prix</th>
                        <th>Sélectionner</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Parcours de la HashMap des prix de sièges
                        for (VolPrixTypeSiege volPrix : prix.values()) {
                    %>
                        <tr>
                            <td><%= volPrix.getTypeSiege().getDescription() %></td> <!-- Description du type de siège -->
                            <td><%= volPrix.getPrixUnitaire() %></td> <!-- Prix unitaire -->
                            <td>
                                <input type="radio" name="res:idTypeSiege" value="<%= volPrix.getIdTypeSiege() %>"> <!-- Sélection du siège -->
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>

        <!-- Nouveau champ pour le nombre de places -->
        <div class="seats-quantity">
            <label for="quantity">Nombre de places:</label>
            <input type="number" id="quantity" name="res:nbPlace" min="1" value="1" required>
        </div>

        <div class="submit-group">
            <button type="submit">Réserver ce Siège</button>
        </div>
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
        align-items: center;
        height: 100vh;
    }

    /* Conteneur principal de la sélection de siège */
    .seats-container {
        background-color: rgba(255, 255, 255, 0.9); /* Fond semi-transparent */
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 900px;
    }

    h2 {
        color: #00796b;
        font-size: 24px;
        margin-bottom: 20px;
        font-weight: 600;
        text-align: center;
    }

    .flight-info {
        margin-bottom: 20px;
        font-size: 16px;
        color: #555;
    }

    /* Style du tableau des sièges */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        padding: 12px;
        text-align: left;
        border: 1px solid #ddd;
    }

    th {
        background-color: #00796b;
        color: white;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    tr:hover {
        background-color: #ddd;
    }

    /* Style du formulaire de sélection de siège */
    .seats-group h3 {
        font-size: 20px;
        color: #00796b;
        margin-bottom: 15px;
    }

    /* Nouveau style pour le champ "Nombre de places" */
    .seats-quantity {
        margin-top: 20px;
        font-size: 16px;
        color: #555;
    }

    .seats-quantity label {
        font-weight: bold;
        margin-right: 10px;
    }

    .submit-group {
        margin-top: 20px;
        text-align: center;
    }

    .submit-group button {
        padding: 10px 20px;
        background-color: #00796b;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }

    .submit-group button:hover {
        background-color: #004d40;
    }
</style>

</body>
</html>
