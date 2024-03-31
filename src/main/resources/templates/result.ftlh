<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="/static/icons/heart.ico" type="image/x-icon">
    <title>Vacation Pay Calculator</title>
    <style>
        body {
            font-size: 16px;
            font-family: 'Roboto', 'Helvetica Neue', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f0f8f0;
            color: #3a3a3a;
        }
        h1 {
            text-align: center;
            color: #6e8a67;
            font-size: 24px;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #aed2ae;
            color: #3a573a;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #96b796;
            transform: scale(1.05);
        }
        p {
            text-align: center;
            color: #333;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
            padding: 25px;
            background-color: #ffffff;
            border-radius: 16px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>
<h1>Оплата за отпуск: ${payment} руб.</h1>
<form action="/" method="get">
    <button type="submit">Вернуться на главную страницу</button>
</form>
</body>
</html>

