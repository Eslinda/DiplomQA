## Дипломный проект профессии «Инженер по тестированию»

---

### Описание

Автоматизация тестирования мобильного приложения "Мобильный хоспис".

Приложение предоставляет функционал по работе с претензиями хосписа и включает в себя:

* Информацию о заявках и функционал для работы с ними.
* Новости хосписа.
* Тематические цитаты.

### Подготовка тестового окружения
1. Клонировать [репозиторий](https://github.com/Eslinda/DiplomQA)
2. В Android Studio открыть проект (папка fmh-android)

### Запуск тестов
В Android Studio в пункте меню Run выбрать Run 'All Tests' 

### Создание отчета Allure
После прогона всех тестов для формирования отчета Allure необходимо скопировать результаты тестирования с тестового устройства на рабочую станцию. Для этого в Android Studio запустить Device File Explorer (двойное нажатие Shift), в контекстном меню папки \data\data\ru.iteco.fmhandroid\files выбрать пункт Save As... и указать путь к папке проекта для сохранения.

Выполните локально консольную команду allure serve, находясь на уровень выше каталога allure-results, для визуализации отчета.

### Документация
* [Чек-лист](https://github.com/Eslinda/DiplomQA/blob/main/Check.xlsx)
* [Тест-кейсы](https://github.com/Eslinda/DiplomQA/blob/main/Cases.xlsx)
* [Результаты автоматизированного тестирования - allure-results](https://github.com/Eslinda/DiplomQA/blob/main/allure-results.zip)
* [Отчет о тестировании](https://github.com/Eslinda/DiplomQA/blob/main/Result.md)
