-- sample 10 users
insert into users
values (true, 1, 'ali', 'val', 'password', 'ali.val'),
       (false, 2, 'reza', 'rename', 'password', 'reza.rename'),
       (true, 3, 'mohammad', 'mohammadi', 'password', 'mohammad.mohammadi'),
       (false, 4, 'ahmad', 'ahmadi', 'password', 'ahmad.ahmadi'),
       (true, 5, 'saeed', 'saeedi', 'password', 'saeed.saeedi'),
       (false, 6, 'hossein', 'hosseini', 'password', 'hossein.hosseini'),
       (true, 7, 'mehdi', 'mehdizadeh', 'password', 'mehdi.mehdizadeh'),
       (false, 8, 'ali', 'alizadeh', 'password', 'ali.alizadeh'),
       (true, 9, 'reza', 'rezaei', 'password', 'reza.rezaei'),
       (false, 10, 'varnish', 'sage', 'password', 'vartishka.sage');

-- sample 4 training types
insert into training_type
values (1, 'java'),
       (2, 'python'),
       (3, 'scala'),
       (4, 'c++');

-- sample 5 trainee
insert into trainee
values ('1990-01-01', 1, 1, 'Yunusobod'),
       ('1990-01-02', 2, 2, 'Yakkasaroy'),
       ('1990-01-03', 3, 3, 'Mirobod'),
       ('1990-01-04', 4, 4, 'Koraqamish'),
       ('1990-01-05', 5, 5, 'Kadishva');

-- sample 5 trainer
insert into trainer
values (1, 1, 6),
       (2, 2, 7),
       (3, 3, 8),
       (4, 4, 9),
       (5, 1, 10);

-- sample 5 training
insert into training
values (2, '2024-01-26', 1, 1, 1, 1, 'Avengers'),
       (2, '2024-01-27', 2, 1, 2, 2, 'Unstoppable'),
       (2, '2024-01-28', 3, 1, 3, 3, 'Sage'),
       (2, '2023-01-29', 4, 2, 3, 3, 'XStack'),
       (2, '2024-01-30', 5, 3, 3, 3, 'MJCSchool');