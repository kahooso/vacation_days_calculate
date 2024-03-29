<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vacation Pay Calculator</title>
    <style>
        body {
            font-size: 18px;
            font-family: "Comic Sans MS", sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f6f7;
        }
        h1 {
            text-align: center;
            color: #7b6c73;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
            padding: 25px;
            background-color: #ffffff;
            border-radius: 16px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #e8d8e3;
            border-radius: 4px;
            box-sizing: border-box;
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
