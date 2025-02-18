<%@page import="avion.model.*, java.util.List"%>
<%
    // Récupérer la liste des vols filtrés depuis l'attribut request
    List<Vol> vols = (List<Vol>) request.getAttribute("vols");
    List<ModeleAvion> avions=(List<ModeleAvion>)request.getAttribute("avions");
     List<VilleDesservie> villes=(List<VilleDesservie>)request.getAttribute("villes");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Vols</title>
</head>
<body>
<jsp:include page="sideBar.jsp" />

<div class="flights-container">
    <h2>Liste des Vols Disponibles</h2>

    <!-- Formulaire de filtrage -->
    <form action="volsFiltres" method="get">
        <div class="filter-group">
            <label for="villeDepart">Ville de départ:</label>
            <select name="villeDepart">
                <% for(VilleDesservie ville:villes){%>
                <option value="<%=ville.getIdVille()%>"><%=ville.getDescription()%></option>
                <%}%>
            </select>

            <label for="nbPlace">modele de l'avion </label>
             <select name="avion">
                 <% for(ModeleAvion avion:avions){%>
                      <option value="<%=avion.getIdModeleAvion()%>"><%=avion.getDescription()%></option>
                 <%}%>
             </select>


            <label for="dateMin">Date Min:</label>
            <input type="date" id="dateMin" name="dateMin">

            <label for="dateMax">Date Max:</label>
            <input type="date" id="dateMax" name="dateMax">

            <button type="submit">Filtrer</button>
        </div>
    </form>

    <!-- Table des vols -->
    <table>
        <thead>
        <tr>
            <th>ID Vol</th>
            <th>Ville de Départ</th>
            <th>Ville d'Arrivée</th>
            <th>Date et Heure</th>
            <th>Durée</th>
            <th>Avion</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% for (Vol vol : vols) { %>
            <tr>
                <td><%= vol.getIdVol() %></td>
                <td><%= vol.getVilleDepart().getDescription() %></td>
                <td><%= vol.getVilleArrivee().getDescription() %></td>
                <td><%= vol.getDateVol() %></td>
                <td><%= vol.getDuree() %></td>
                <td><%= vol.getIdAvion() %></td>
                <td><%= vol.getDescription() %></td>
                <td><a href="volFormUpdate?idVol=<%= vol.getIdVol() %>">Modifier</a></td>
            </tr>
        <% } %>
        </tbody>
    </table>
</div>

<!-- CSS en bas -->
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

    /* Conteneur principal de la liste des vols */
    .flights-container {
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

    a {
        color: #00796b;
        text-decoration: none;
    }

    a:hover {
        text-decoration: underline;
    }

    /* Style du formulaire de filtrage */
    .filter-group {
        margin-bottom: 20px;
        display: flex;
        flex-direction: column;
    }

    .filter-group label,
    .filter-group input {
        margin: 5px 0;
    }

    .filter-group input {
        padding: 8px;
        font-size: 16px;
        border-radius: 5px;
        border: 1px solid #ccc;
    }

    .filter-group button {
        padding: 10px;
        background-color: #00796b;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }

    .filter-group button:hover {
        background-color: #004d40;
    }
</style>

</body>
</html>
