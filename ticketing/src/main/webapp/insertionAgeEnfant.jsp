<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Création d'un Age Enfant Max Remise</title>
</head>
<body>

<div class="form-container">
    <h2>Créer un Age Enfant Max Remise</h2>

    <!-- Formulaire de création d'AgeEnfantMaxRemise -->
    <form action="insert" method="POST">
        <div class="form-layout">
            <!-- Informations sur l'AgeEnfantMaxRemise (gauche) -->
            <div class="left-column">
                <div class="remise-details">
                    <!-- Champ pour l'âge maximum -->
                    <div class="input-group">
                        <label for="ageMax">Âge Max</label>
                        <input type="number" id="ageMax" name="ageMax" required placeholder="Entrez l'âge maximum">
                    </div>

                    <!-- Champ pour la remise -->
                    <div class="input-group">
                        <label for="remise">Remise (%)</label>
                        <input type="number" id="remise" name="remise" required placeholder="Entrez la remise" min="0" max="100" step="0.01">
                    </div>

                    <!-- Champ pour la date -->
                    <div class="input-group">
                        <label for="dateEns">Date d'Enseignement</label>
                        <input type="date" id="dateEns" name="dateEns" required>
                    </div>
                </div>
            </div>

            <!-- Bouton de soumission -->
            <div class="right-column">
                <button type="submit" class="btn-submit">Créer l'Age Enfant Max Remise</button>
            </div>
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
        align-items: center; /* Centrage vertical */
        height: 100vh;
    }

    /* Conteneur principal du formulaire */
    .form-container {
        background-color: rgba(255, 255, 255, 0.9); /* Fond semi-transparent */
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.1);
        width: 80%; /* Rendre plus large */
        max-width: 1200px; /* Limiter la largeur maximale */
        display: flex;
        flex-direction: column;
        align-items: center;
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
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .remise-details {
        background-color: #f9f9f9;
        padding: 20px;
        border-radius: 10px;
        border: 1px solid #ddd;
        margin-bottom: 30px;
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

    input[type="number"],
    input[type="date"] {
        width: 100%;
        padding: 14px;
        margin-top: 8px;
        border-radius: 8px;
        border: 1px solid #ccc;
        font-size: 16px;
        box-sizing: border-box;
    }

    input[type="number"]:focus,
    input[type="date"]:focus {
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
