<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulaire d'Insertion - Heure Avant Après Réservation</title>
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

        /* Conteneur principal */
        .main-container {
            background-color: rgba(255, 255, 255, 0.9); /* Fond semi-transparent */
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
            text-align: center;
        }

        h1 {
            color: #00796b;
            font-size: 30px;
            margin-bottom: 20px;
            font-weight: 600;
        }

        p {
            color: #555;
            font-size: 16px;
            margin-bottom: 30px;
        }

        .form-group {
            margin-bottom: 20px;
            text-align: left;
        }

        label {
            color: #00796b;
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
        }

        input[type="time"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 16px;
            box-sizing: border-box;
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

        .icon-form {
            font-size: 60px;
            color: #00796b;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<jsp:include page="sideBar.jsp" />
<div class="main-container">
    <div class="icon-form">
        ⏰ <!-- Icône de montre pour rappeler l'heure -->
    </div>
    <h1>Formulaire de regle de gestion</h1>
    <p>Veuillez remplir les informations ci-dessous pour insérer une nouvelle entrée.</p>

    <form action="insertHeureRes" method="POST">
        <div class="form-group">
            <label for="heure_av_res">Heure Avant Réservation</label>
            <input type="time" id="heure_av_res" name="heureAvRes" required />
        </div>

        <div class="form-group">
            <label for="heure_ap_res">Heure Après Réservation</label>
            <input type="time" id="heure_ap_res" name="heureApRes" required />
        </div>

        <button type="submit" class="btn-submit">Ajouter l'Entrée</button>
    </form>
</div>
</body>
</html>
