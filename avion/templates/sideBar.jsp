<%@ page import="avion.model.*"%>

<%
    String role =(String)session.getAttribute("role");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sidebar - Réservation de Billets d'Avion</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <div class="sidebar">
        <div class="sidebar-header">
            <h2>Réservation Vols</h2>
        </div>
        <ul class="sidebar-menu">
            <li><a href="#">Accueil</a></li>
            <li><a href="/avion/vols">vols</a></li>
            <%if(role.equals("admin")){%>
                <li><a href="/avion/volForm">insertion vol</a></li>
                <li><a href="/avion/insertHeureRes">insertion heure </a></li>
            <%}%>
            <li><a href="/avion/reservations">liste reservations </a></li>
        </ul>
    </div>

</body>
</html>

<style>
/* Style général de la page */
body {
    font-family: 'Arial', sans-serif;
    margin: 0;
    padding: 0;
    display: flex;
}

/* Style du Sidebar */
.sidebar {
    height: 100vh;
    width: 250px;
    background: linear-gradient(135deg, #00bcd4, #009688); /* Dégradé bleu-vert */
    color: white;
    padding-top: 30px;
    position: fixed;
    top: 0;
    left: 0;
    box-shadow: 2px 0 10px rgba(0, 0, 0, 0.1);
}

/* En-tête du Sidebar */
.sidebar-header {
    text-align: center;
    margin-bottom: 30px;
}

.sidebar-header h2 {
    font-size: 24px;
    font-weight: 600;
    color: white;
    margin: 0;
}

/* Liste du menu */
.sidebar-menu {
    list-style: none;
    padding: 0;
    margin: 0;
}

.sidebar-menu li {
    padding: 15px 20px;
    text-align: left;
    border-bottom: 1px solid #00796b;
}

.sidebar-menu li a {
    color: white;
    text-decoration: none;
    font-size: 18px;
    display: block;
}

.sidebar-menu li a:hover {
    background-color: #00796b;
    color: #ffffff;
}

/* Style pour les liens */
.sidebar-menu li a:active,
.sidebar-menu li a:focus {
    color: #ffffff;
    background-color: #004d40;
}
</style>
