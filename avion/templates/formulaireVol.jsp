
<%@page import="avion.model.*,java.util.List"%>
<%
List<Avion> avions=(List<Avion>)request.getAttribute("avions");
List<VilleDesservie> villes=(List<VilleDesservie>)request.getAttribute("villes");

Vol vol=null;
if(request.getAttribute("vol")!=null)
{
    vol=(Vol)request.getAttribute("vol");
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
        <div class="input-group">
            <label for="dateVol">Date du Vol</label>
            <input type="datetime-local" id="dateVol" name="dateVol" required value="<%if(vol!=null){out.print(vol.getDateVol());}%>">
        </div>

        <div class="input-group">
            <label for="description">Description</label>
            <input type="text"  value="<%if(vol!=null){out.print(vol.getDescription());}%>" id="description" name="description" placeholder="Description du vol" required>
        </div>

        <div class="input-group">
            <label for="idVilleDepart">Ville de depart</label>
            <select name="idVilleDepart">
                <%if(vol!=null){%>
                    <option value="<%=vol.getVilleDepart().getIdVille()%>"><%=vol.getVilleDepart().getDescription()%></option>
                <%}%>
                <%for(VilleDesservie ville:villes){%>
                <option value="<%=ville.getIdVille()%>"><%=ville.getDescription()%></option>
                <%}%>
            </select>
        </div>

        <div class="input-group">
            <label for="idVilleArrivee">Ville d'arrivee</label>
            <select name="idVilleArrivee">
            <%if(vol!=null){%>
                  <option value="<%=vol.getVilleArrivee().getIdVille()%>"><%=vol.getVilleArrivee().getDescription()%></option>
            <%}%>
                <%for(VilleDesservie ville:villes){%>
                <option value="<%=ville.getIdVille()%>"><%=ville.getDescription()%></option>
                <%}%>
            </select>
        </div>
        <div class="input-group">
            <label for="idAvion">ID de l'Avion</label>
            <select name="idAvion">
             <%if(vol!=null){%>
                 <option value="<%=vol.getIdAvion()%>"><%=vol.getIdAvion()%></option>
             <%}%>
                <%for(Avion avion:avions){%>
                <option value="<%=avion.getIdAvion()%>">avion numero <%=avion.getIdAvion()%> </option>
                <%}%>
            </select>
        </div>

        <div class="input-group">
            <label for="duree">Durée</label>
            <input type="time" id="duree" name="duree"  value="<%if(vol!=null){out.print(vol.getDuree());}%>" required>
        </div>

         <%if(vol!=null){%>
              <input type="hidden"  name="idVol"  value="<%=vol.getIdVol()%>" >
         <%}%>

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
        align-items: center;
        height: 100vh;
    }

    /* Conteneur principal du formulaire */
    .vol-container {
        background-color: rgba(255, 255, 255, 0.9); /* Fond semi-transparent */
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 500px;
        text-align: center;
    }

    h2 {
        color: #00796b;
        font-size: 24px;
        margin-bottom: 30px;
        font-weight: 600;
    }

    .input-group {
        margin-bottom: 20px;
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
