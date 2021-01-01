INSERT INTO categories(id, name, parent_id)
VALUES (0, 'Любая категория', null),

       (1, 'Транспорт', 0),
       (2, 'Автомобили', 1),
       (3, 'Мотоциклы и мототехника', 1),
       (4, 'Грузовики и спецтехника', 1),
       (5, 'Водный транспорт', 1),
       (6, 'Запчасти и аксессуары', 1),


       (7, 'Недвижимость', 0),
       (8, 'Квартиры', 7),
       (9, 'Комнаты', 7),
       (10, 'Дома, дачи, коттеджи', 7),
       (11, 'Земельные участки', 7),
       (12, 'Гаражи и машиноместа', 7),
       (13, 'Коммерческая недвижимость', 7),
       (14, 'Недвижимость за рубежом', 7),


       (15, 'Работа', 0),
       (16, 'Вакансии', 15),
       (17, 'Резюме', 15),


       (18, 'Услуги', 0),


       (19, 'Личные вещи', 0),
       (20, 'Одежда, обувь, аксессуары', 19),
       (21, 'Детская одежда и обувь', 19),
       (22, 'Товары для детей и игрушки', 19),
       (23, 'Часы и украшения', 19),
       (24, 'Красота и здоровье', 19),


       (25, 'Для дома и дачи', 0),
       (26, 'Бытовая техника', 25),
       (27, 'Мебель и интерьер', 25),
       (28, 'Посуда и товары для кухни', 25),
       (29, 'Продукты питания', 25),
       (30, 'Ремонт и строительство', 25),
       (31, 'Растения', 25),


       (32, 'Бытовая электроника', 0),
       (33, 'Аудио и видео', 32),
       (34, 'Игры, приставки и программы', 32),
       (35, 'Настольные компьютеры', 32),
       (36, 'Ноутбуки', 32),
       (37, 'Оргтехника и расходники', 32),
       (38, 'Планшеты и электронные книги', 32),
       (39, 'Телефоны', 32),
       (40, 'Товары для компьютера', 32),
       (41, 'Фототехника', 32),


       (42, 'Хобби и отдых', 0),
       (43, 'Билеты и путешествия', 42),
       (44, 'Велосипеды', 42),
       (45, 'Книги и журналы', 42),
       (46, 'Коллекционирование', 42),
       (47, 'Музыкальные инструменты', 42),
       (48, 'Охота и рыбалка', 42),
       (49, 'Спорт и отдых', 42),


       (50, 'Животные', 0),
       (51, 'Собаки', 50),
       (52, 'Кошки', 50),
       (53, 'Птицы', 50),
       (54, 'Аквариум', 50),
       (55, 'Другие животные', 50),
       (56, 'Товары для животных', 50),


       (57, 'Готовый бизнес и оборудование', 0),
       (58, 'Готовый бизнес', 57),
       (59, 'Оборудование для бизнеса', 57);