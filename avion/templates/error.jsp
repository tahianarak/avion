<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erreur - Réservation de Billets d'Avion</title>
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

        /* Conteneur principal de l'erreur */
        .error-container {
            background-color: rgba(255, 255, 255, 0.9); /* Fond semi-transparent */
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
            text-align: center;
        }

        h2 {
            color: #e74c3c; /* Rouge pour l'erreur */
            font-size: 24px;
            margin-bottom: 30px;
            font-weight: 600;
        }

        p {
            font-size: 16px;
            color: #555;
            margin-bottom: 20px;
        }

        .icon-error {
            font-size: 50px;
            color: #e74c3c;
            margin-bottom: 20px;
        }

        .btn-back {
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

        .btn-back:hover {
            background-color: #004d40;
        }
    </style>
</head>
<body>

<div class="error-container">
    <div class="icon-error">
        ✈️ <!-- Icon d'avion pour illustrer le thème -->
    </div>
    <h2>Oups ! Une erreur est survenue</h2>
    <p><%=request.getAttribute("error").toString()%></p>
    <a href="/" class="btn-back">Retour à l'accueil</a>
</div>

</body>
</html>
