### Приложение для расчета отпускных дней

Это простое приложение на Spring Boot, которое вычисляет отпускные дни на основе средней зарплаты, даты начала и даты окончания отпуска.

### Требования
- Java Development Kit (JDK) версии 11 или выше
- Apache Maven

### Установка
1. Клонируйте репозиторий на свой локальный компьютер с помощью команды `git clone https://github.com/kahooso/vacation_days_calculate`
2. Перейдите в директорию проекта `cd vacation_days_calculate`
   
### Использование
1. Соберите проект с помощью Maven:
2. Запустите приложение:
3. Откройте веб-браузер и перейдите по адресу [http://localhost:8080](http://localhost:8080), чтобы получить доступ к приложению.

### Как расчитать отпускные дни
1. Введите среднюю зарплату, дату начала и дату окончания отпуска.
2. Нажмите кнопку "Рассчитать".
3. Результат будет отображен на экране, указывая сумму выплаты за отпускные дни.

### Особенности
- Приложение автоматически проверяет введенные данные на корректность.
- Если данные введены неверно, приложение выдаст сообщение об ошибке.
- Поддерживается ввод дат в формате "год-месяц-день".
- В случае ошибки во время выполнения, приложение отображает страницу с сообщением об ошибке.

### Технологии
- Java
- Spring Boot
- Thymeleaf (для создания пользовательского интерфейса)
- JUnit и Mockito (для тестирования)
- Maven (для сборки проекта)

### Автор
@kahooso
