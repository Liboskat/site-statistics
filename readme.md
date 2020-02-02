# SiteStatistics
Тестовое задание - REST-сервис по сбору статистики посещаемости WEB-сайта.

## Инструкция по сборке
1. Загрузить и установить [OpenJDK 8](https://openjdk.java.net/install/).
2. [Загрузить](https://maven.apache.org/download.cgi) и [установить](https://maven.apache.org/install.html) Maven.
3. Загрузить исходный код из репозитория в виде [архива](https://github.com/Liboskat/site-statistics/archive/master.zip) и распаковать; либо склонировать репозиторий командой `git clone https://github.com/Liboskat/site-statistics.git`, для этого необходимо загрузить и установить [Git](https://git-scm.com/downloads).
4. Указать в файле конфигурации (`src/main/resources/application.properties`) параметры подключения к базе данных PostgreSQL:
   * `spring.datasource.url` - URL подключения к базе данных,
   * `spring.datasource.username` - логин пользователя базы данных,
   * `spring.datasource.password` - пароль пользователя базы данных.
5. Перейти в корневую директорию загруженного проекта и выполнить команду `mvn clean package`, таким образом удаляются существующие артефакты сборки (.class, .jar и других файлы), компилируется исходный код, собирается jar файл с приложением.
Собранный проект хранится в директории target.

## Инструкция по запуску и проверке
1. Выполните команду `java -jar target/SiteStatistics-0.1.jar` в терминале.
2. Откройте веб-страницу `http://localhost:8080/swagger-ui.html`.
3. Нажмите на вкладку `website-statistics-controller` и выберите метод для проверки:
   * `POST /api/register` - создание события посещения сайта пользователем,
   * `GET /api/statistics` - получение статистики посещения за произвольный период.
4. Нажмите на кнопку `Try it out`, затем введите входные данные и нажмите на кнопку `Execute`.
   Метод `POST /api/register` принимает входные данные в виде JSON.
   Метод `GET /api/statistics` принимает входные данные в виде GET параметров запроса, даты принимаются в формате ISO 8601 (`YYYY-MM-DD`)
5. Результаты запроса находятся ниже параметров запроса, под пунктом `Server response`.