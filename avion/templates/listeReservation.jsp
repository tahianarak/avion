<%@page import="avion.model.*, java.util.List"%>
<%

    List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Réservations</title>
</head>
<body>
<jsp:include page="sideBar.jsp" />

<div class="reservations-container">
    <h2>Liste des Réservations</h2>

    <!-- Table des réservations -->
    <table>
        <thead>
        <tr>
            <th>ID Réservation</th>

            <th>Vol</th>
            <th>Date de Réservation</th>
            <th>Nb de Places</th>
            <th>Type de Siège</th>
            <th>Prix Billet</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <% for (Reservation reservation : reservations) { %>
            <tr>
                <td><%= reservation.getIdReservation() %></td>
                <td><%= reservation.getVol().getDescription() %></td>
                <td><%= reservation.getDateReservation() %></td>
                <td><%= reservation.getNbPlace() %></td>
                <td><%= reservation.getTypeSiege().getDescription() %></td>
                <td><%= reservation.getPrixBillet() %> €</td>
                <td>
                    <a href="annulerReservation?idReservation=<%= reservation.getIdReservation() %>"><button>Annuler</button></a>
                </td>
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

    /* Conteneur principal de la liste des réservations */
    .reservations-container {
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

    button {
        padding: 8px 15px;
        background-color: #00796b;
        color: white;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
    }

    button:hover {
        background-color: #004d40;
    }
</style>

</body>
</html>
