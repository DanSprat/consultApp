# Progwards Consult App
Данное веб-приложение было создано к качестве выпускного проекта по завершении продвинутого курса Java учебной платфомы Progwards.<br> Приложение построено на сервлетах и развернуто на удаленной вирутальной машинине с помощью Apache Tomcat по адресу:
`http://34.116.203.124:8888/`
## Описание 
Данное приложение позволяет: 
- Cтудентам учебной платформы записываться на консультации к наставникам. 
- Наставникам составлять собственное расписание, на основе которого формируются консультации.
- Наставникам (админам) менять параметры системы, например: ***продолжительность сессии*** или ***длительность консультации***
## Структура
Проект реализован на файловой базе данных с 4 таблицами:
1. Пользователи
2. Настройки
3. Расписание
4. Консультации

**Взаимодействие клиента и сервера происходит посредством использования сервлетов.**

> **/login** - страница входа или регистрации

При регистрации с помощью JavaScript проверяется сложность пароля и идентичность его повтора. После авторизации сервер перенаправляет пользователя на страницу с информацией

> **/user/view?login=name** - страница пользователя с логином `name` 

>**/mentors** - выбор ментора (по умолчанию для записи на консультацию)

На сервере настроен листенер, который каждые сутки в 00:00 генерирует список консультаций на неделю вперед по следующим правилам: <br>
>а) Если консультация прошла - удаляет ее <br>
>б) Если на консультацию уже записан студент - оставляет ее
