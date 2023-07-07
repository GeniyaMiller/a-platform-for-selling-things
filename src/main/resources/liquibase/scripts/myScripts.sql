-- liquibase formatted sql

-- changeset jenmiller:1

CREATE TABLE if not exists comments
(
    pk         serial not null primary key,
    created_at timestamp,
    text       varchar(255),
    ads_pk     integer
        references public.ads,
    author_id  integer
        references public.users
);

-- changeset jk:2
CREATE TABLE if not exists ads
(
    pk          serial primary key,
    author_id   integer references users (id),
    title       varchar(100) not null,
    description varchar(100) not null,
    price       integer      not null,
    image       bytea
);
