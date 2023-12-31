# План по тестированию мобильного приложения «Мобильный хоспис»

## Описание приложения.

Приложение даёт функционал по работе с претензиями хосписа и включает в себя:

информацию о претензиях и функционал для работы с ними;
новостную сводку хосписа;
тематические цитаты.

## Функционал приложения на данный момент:

**Экран авторизации, поля:**

-   Логин;
-   Пароль;
-   кнопка "Войти".

**Верхняя панель навигации:**
- меню:
  - Главная;
  - Заявки;
  - Новости;
  - О приложении;
- раздел с цитатами;
- меню выхода из учетной записи.

**Главный экран:**

Новости (возможность свернуть все новости):
- Отображаются три последние новости и кнопка "Все новости", возможность развернуть и свернуть конкретную новость.

Заявки (возможность свернуть все заявки, возможность добавить новую):
- Отображаются последние шесть заявок и кнопка "Все заявки", возможность перейти в выбранную заявку.

**Экран заявок:**
-   Кнопка фильтрации (возможность фильтровать по статусу заявки);
-   Кнопка добавления новой заявки;
-   Список всех заявок.

**Экран новостей:**

-   Кнопка сортировки по дате создания;
-   Кнопка фильтрации (возможность фильтрации по категории, диапазону дат);
-   Кнопка панели управления новостями;
-   Список новостей.

**Экран "О приложении":**

-   Версия приложения;
-   Ссылка на политику конфиденциальности;
-   Ссылка на пользовательское соглашение;
-   Разработчик.

**В заявках реализовано:**

-   Добавление новых заявок;
-   Редактирование существующих заявок;
-   Добавление комментариев к существующим заявкам;
-   Редактирование комментариев к заявкам;
-   Изменение статуса существующих заявок.

**В новостях реализовано:**

-   Добавление новых новостей;
-   Удаление новостей;
-   Редактирование новостей.

**Цитаты:**

-   возможность развернуть/свернуть цитату.

## Виды тестирования:

-   Функциональное тестирование - ручное и автоматизированное тестирование;
-   Нефункциональное тестирование:
    -   Тестирование установки - установка, удаление приложения
    -   Тестирование пользовательского интерфейса (Usability testing) - ручное тестирование.
    -   Тестирование совместимости - установка и работа основных функций на устройствах с разными версиями Android и разрешениями экрана.

## Перечень используемых инструментов:

-   GitHub - для хранения проекта и тестов;
-   Android Studio - среда разработки для работы с платформой Android и эмулирования устройств, на которых будет тестироваться приложение (Pixel 5 API 29 (Android 10), Pixel 6 Pro API 33 (Android 13));
-   Gradle - система сборки проекта, с ее помощью будут подключаться нужные зависимости;
-   Java 11 - язык программирования для написания автотестов;
-   Junit - фреймворк для написания тестов;
-   Espresso - фреймворк для написания тестов;
-   Allure - фреймворк для получения информации о прохождении.

## Интервальная оценка времени с учетом рисков:

-   Исследование приложения - 2 часа;
-   Составление плана тестирования - 3 часа;
-   Составление чек-листа - 5 часов;
-   Составление тест-кейсов - 9 часов;
-   Ручное тестирование (составление баг-репортов в случае обнаружения дефектов) - 5 часа;
-   Подготовка проекта, настройка окружения - 4 часа;
-   Написание автотестов - 45 часов;
-   Проведение автоматизированного тестирования - 3 часа;
-   Составление отчета о тестировании - 5 часа.

Итого 81 часов.

## Перечень и описание возможных рисков при автоматизации тестирования:

-   Отсутствие конкретных требований к приложению;
-   Возможные сложности в поиске id элементов;
-   Возможны выявления дефектов в приложении;
-   Возможны переименования элементов, текста в объектах (сообщения об ошибках).

## План сдачи работ:

* Составление плана тестирования, чек-листа, тест-кейсов: до 26.09.2023.
* Ручное тестирование: 27.09.2023.
* Составление автотестов и проведение автоматизированного тестирования: до 09.10.2023
* Составление отчетности: до 12.10.2023.
