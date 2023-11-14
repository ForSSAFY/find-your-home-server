create table if not exists `user`
(
    `id`        int          not null auto_increment primary key,
    `username`  varchar(30)  not null unique,
    `password`  varchar(300) not null,
    `join_date` timestamp    not null default current_timestamp
);

create table if not exists `qna`
(
    `id`         int           not null auto_increment primary key,
    `user_id`    int           not null,
    `title`      varchar(100)  not null,
    `content`    varchar(2000) not null,
    `views`      int           not null default 0,
    `created_at` timestamp     not null default current_timestamp,
    constraint `qna_to_user_id_fk`
        foreign key (`user_id`) references `user` (`id`)
);
delete from `user` where username = 'ssafy';
