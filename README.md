# RestPetProject

REST API приложение для регистрации и сбора данных метеорологических датчиков

## Технологический стек
- Java 25
- Spring Boot 4.0.6
- Spring Data Jpa
- PostgreSQL
- Docker Compose
- MapStruct
- Springdoc OpenAPI Swagger


## Запуск проекта

Для того, чтобы убедиться в корректной подгрузке стартеров обновите зависимости: нажмите Reload All Maven Projects во вкладке Maven, либо выполните команду ./mvnw clean compile во встроенном терминале.

Запустить базу данных используя среду разработки или выполнить команду в терминале:
```bash
docker-compose up -d
```
База данных поднимется на порту 5492.

Запустить сам проект с помощью среды разработки или с помощью команды:
```bash
./mvnw spring-boot:run
```
Приложение запустится на порту 8080.


## Конфигурация и безопасность

Для удобства проверки и менторского ревью в файлах application.yml и docker-compose.yml оставлены дефолтные значения. Проект готов к запуску "из коробки" без ручной настройки .env файлов.


## Документация API

После запуска приложения доступен интерфейс для тестирования эндпоинтов:
- Swagger UI: http://localhost:8080/swagger-ui/index.html
Основные эндпоинты:
- `POST /sensors/registration`
- `POST /measurements/add`
- `GET /measurements`
- `GET /measurements/rainyDaysCount`