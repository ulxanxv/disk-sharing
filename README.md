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

### API
- Авторизация - /auth/login

Пример запроса:
```json
{
  "login": "login",
  "password": "pass"
}
```
Пример ответа:
```json
{
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJiMDhmODZhZi0zNWRhLTQ4ZjItOGZhYi1jZWYzOTA0NjYwYmQifQ.-xN_h82PHVTCMA9vdoHrcZxH-x5mb11y1537t3rGzcM"
}
```
*Токен необходимо отправлять далее при каждом запросе в заголовке Authorization:*
```
Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiJiMDhmODZhZi0zNWRhLTQ4ZjItOGZhYi1jZWYzOTA0NjYwYmQifQ.-xN_h82PHVTCMA9vdoHrcZxH-x5mb11y1537t3rGzcM"
```
- Посмотреть свои диска - /sharing/disks
- Посмотреть взятые диски /sharing/disks/taken
- Посмотреть свободные диски - /sharing/disks/free
- Посмотреть список должников - /sharing/disks/debtors
- Взять диск - /sharing/disks/take

Пример запроса:
```json
{
  "diskId": 1
}
```
Пример ответа:
```json
{} // 200 status
```
- Отдать диск - /sharing/disks/return

Пример запроса:
```json
{
  "diskId": 1
}
```
Пример ответа:
```json
{} // 200 status
```