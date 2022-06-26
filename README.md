<h1 align="center">disk-sharing</h1>

## Инструкция по запуску
Запускать команды следует из корня проекта:
```
mvn install
docker-compose up
```
Изменить порт запуска можно [тут](docker-compose.yml) в пункте *ports* (по умолчанию 8080)

## Инструкция по использованию
- Можете воспользоваться **swagger-api**, он доступен по адресу `http://<host>:<port>/swagger-ui.html` после запуска
- Так же есть файл [спецификации](src/main/resources/openapi/disk-sharing.yaml)