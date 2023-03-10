Инструкцию по запуску
Открываем проект, подключаем базу данных(зависимости находятся в application.properties), создаем таблицы при помощи sql скрипта
по адресу  src/main/resources/project.sql,
запускаем ProjectManagerApplication.run.
Для проверки функционала при помощи postman нужно сделать импорт коллекций, коллекции находятся в папке src/main/resources/postman, сначала прогоняем коллекцию админа, потом юзера. При ошибке авторизации в разделе Authorization нужно проставить Type Basic Auth. 
username, password для admin (admin, password) для user (user, password) соответственно.


Не смог запустить с базой данных H2(при включенном Spring Security), в проекте использую PostgreSQL.
