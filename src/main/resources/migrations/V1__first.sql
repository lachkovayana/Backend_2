create table projects
(
    id            varchar(255) not null
        primary key,
    creation_date date,
    description   varchar(255),
    edit_date     date,
    name          varchar(255)
);

alter table projects
    owner to hits;

create table users
(
    id            varchar(255) not null
        primary key,
    creation_date date,
    edit_date     date,
    email         varchar(255),
    full_name     varchar(255),
    password      varchar(255),
    role          varchar(255)
);

alter table users
    owner to hits;

create table comments
(
    id            varchar(255) not null
        primary key,
    comment_text  varchar(255),
    creation_date date,
    edit_date     date,
    author_id     varchar(255)
        constraint fkn2na60ukhs76ibtpt9burkm27
            references users
);

alter table comments
    owner to hits;

create table tasks
(
    id            varchar(255) not null
        primary key,
    creation_date date,
    description   varchar(255),
    edit_date     date,
    priority      varchar(255),
    time_estimate integer,
    title         varchar(255),
    creator_id    varchar(255)
        constraint fkt1ph5sat39g9lpa4g5kl46tbv
            references users,
    editor_id     varchar(255)
        constraint fkc9cec1p4qxfeu1shyhfk4uqsj
            references users,
    project_id    varchar(255)
        constraint fksfhn82y57i3k9uxww1s007acc
            references projects
);

alter table tasks
    owner to hits;

create table comment_task
(
    comment_id varchar(255) not null
        constraint fk8eej508bophi57eaq6csdn9lg
            references comments,
    task_id    varchar(255) not null
        constraint fkkbhl8uvyblemp5wl1nf39mhr6
            references tasks
);

alter table comment_task
    owner to hits;

