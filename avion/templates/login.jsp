<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Réservation de Billets d'Avion</title>
</head>
<body>

<div class="login-container">
    <h2>Connexion à votre compte</h2>

    <!-- Formulaire de login -->
    <form action="verifyLogin" method="get">
        <div class="input-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Entrez votre email" required>
        </div>

        <div class="input-group">
            <label for="password">Mot de passe</label>
            <input type="password" id="password" name="mdp" placeholder="Entrez votre mot de passe" required>
        </div>

        <a href="#" class="forgot-password">Mot de passe oublié ?</a>

        <button type="submit" class="btn-submit">Se connecter</button>
    </form>
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

    /* Conteneur principal du formulaire */
    .login-container {
        background-color: rgba(255, 255, 255, 0.9); /* Fond semi-transparent */
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 400px;
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

    input[type="email"],
    input[type="password"] {
        width: 100%;
        padding: 14px;
        margin-top: 8px;
        border-radius: 8px;
        border: 1px solid #ccc;
        font-size: 16px;
        box-sizing: border-box;
    }

    input[type="email"]:focus,
    input[type="password"]:focus {
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

    .forgot-password {
        text-align: right;
        font-size: 14px;
        color: #00796b;
        text-decoration: none;
    }

    .forgot-password:hover {
        text-decoration: underline;
    }
</style>

</body>
</html>
