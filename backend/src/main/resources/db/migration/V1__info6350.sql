create table info6350.t_expense
(
    id             varchar(32) not null
        primary key,
    name           varchar(255) null,
    description    varchar(255) null,
    category       varchar(255) null,
    cost_in_dollar double null,
    user_id        varchar(32) not null,
    expense_date   datetime(6) null
);

create table info6350.t_group
(
    id      varchar(32) not null,
    name    varchar(255) null,
    user_id varchar(32) null
);

create table info6350.t_todo
(
    id           varchar(32) null,
    name         text null,
    description  text null,
    status       tinyint(1) null,
    user_id      varchar(32) null,
    created_time datetime null,
    updated_time datetime null
);

create table info6350.t_user
(
    id           varchar(32) not null
        primary key,
    first_name   varchar(255) null,
    last_name    varchar(255) null,
    email        varchar(255) null,
    password     varchar(255) null,
    created_time datetime(6) null,
    updated_time datetime(6) null,
    verified     tinyint(1) default 0 null
);

