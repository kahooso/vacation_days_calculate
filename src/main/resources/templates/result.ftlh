<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Калькулятор отпускных</title>
    <style>
        body {
            font-family: 'Roboto', 'Helvetica Neue', sans-serif;
            font-size: 14px;
            margin: 0;
            padding: 0;
            background-color: #f9f6f7;
        }
        h1, p {
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
        button {
            width: 100%;
            padding: 10px;
            background-color: #C6D8FF;
            color: #333;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #DEF7FE;
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

