<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        form {
            max-width: 500px;
            margin: 0 auto;
            padding: 25px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.2);
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #4e6e4e;
        }
        input, select {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #d1ded1;
            border-radius: 4px;
            box-sizing: border-box;
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
    </style>
</head>
<body>
<h1>Калькулятор отпускных</h1>
<form action="/calculate" method="get">
    <label for="averageSalary">Cр. зарплата за 12 месяцев:</label>
    <input type="number" id="averageSalary" name="averageSalary">
    <div id="vacationDays">
        <label for="vacationDays">Кол-во дней отпуска:</label>
        <input type="number" id="vacationDays" name="vacationDays">
    </div>
    <label for="specificPeriodCheckbox">Указать конкретный период
        <input type="checkbox" id="specificPeriodCheckbox" name="specificPeriod" style = "width: 10%">
    </label><br>
    <div id="specificPeriodFields" style="display:none;">
        <label for="startDate">Начало отпуска:</label>
        <input type="date" id="startDate" name="startDate">
        <label for="endDate">Конец отпуска:</label>
        <input type="date" id="endDate" name="endDate">
    </div>
    <button type="submit">Вычислить</button>
</form>
<h1>Как использовать поиск для расчетов</h1>
<form>
    <p>Вы также можете воспользоваться поиском для быстрого расчета. Введите параметры напрямую в строку запроса, например:</p>
    <ul>
        <li>http://localhost:8080/calculate?averageSalary=100&vacationDays=20<br>Расчет оплаты отпуска при зарплате 100 и 20 днях отпуска.</li>
        <li>http://localhost:8080/calculate?averageSalary=100&startDate=2024-01-01&endDate=2024-01-10<br>Расчет оплаты отпуска при зарплате 100 и отпуске с 1 по 10 января 2024 года.</li>
    </ul>
</form>
<script>
    document.getElementById("specificPeriodCheckbox").addEventListener("change", function() {
        const specificPeriodFields = document.getElementById("specificPeriodFields");
        const vacationDays = document.getElementById("vacationDays");
        if (this.checked) {
            vacationDays.style.display = "none";
            specificPeriodFields.style.display = "block";
        } else {
            vacationDays.style.display = "block";
            specificPeriodFields.style.display = "none";
        }
    });
</script>
</body>
</html>
