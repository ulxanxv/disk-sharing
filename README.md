<h1 align="center">disk-sharing</h1>

## Требования
- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Инструкция по запуску
Запускать команды следует из корня проекта:

- Docker
```
mvn install
docker-compose up
```
- Обычный запуск
```
mvn spring-boot:run
```
Изменить порт запуска можно [тут](docker-compose.yml) в пункте *ports* (по умолчанию 8080)

## Инструкция по использованию
- Можете воспользоваться **swagger-api**, он доступен по адресу `http://<host>:<port>/swagger-ui.html` после запуска
- Так же есть файл [спецификации](src/main/resources/openapi/disk-sharing.yaml)