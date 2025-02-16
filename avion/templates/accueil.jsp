<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil - Réservation de Billets d'Avion</title>
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

        .btn-book-now,
        .btn-login {
            width: 100%;
            padding: 14px;
            background-color: #00796b;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 18px;
            cursor: pointer;
            transition: background-color 0.3s;
            margin-bottom: 20px;
        }

        .btn-book-now:hover,
        .btn-login:hover {
            background-color: #004d40;
        }

        .icon-welcome {
            font-size: 60px;
            color: #00796b;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

<div class="main-container">
    <div class="icon-welcome">
        ✈️ <!-- Icône d'avion pour rappeler l'univers de la réservation -->
    </div>
    <h1>Bienvenue sur notre Service de Réservation de Billets d'Avion</h1>
    <p>Explorez les destinations, réservez vos billets et partez à l'aventure !</p>

    <!-- Boutons d'action -->
    <a href="/booking" class="btn-book-now">Réserver un Vol</a>
    <a href="/login" class="btn-login">Se Connecter</a>
</div>

</body>
</html>
