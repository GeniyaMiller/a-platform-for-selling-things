-- liquibase formatted sql

-- changeset jenmiller:1

CREATE TABLE if not exists comments
(
   id           SERIAL NOT NULL PRIMARY KEY,
   user_id      SERIAL,
   ads_id       SERIAL,
   text         TEXT,
   createdAt    TIMESTAMP WITH TIME ZONE
);

-- changeset jk:2
CREATE TABLE if not exists ads
(
    id           SERIAL NOT NULL PRIMARY KEY,
    title        VARCHAR(255),
    price        BIGINT NOT NULL,
    desription   VARCHAR(255),
    image        TEXT,
    user_id      SERIAL
);

CREATE TABLE if not exists image
(
    id         VARCHAR(255) PRIMARY KEY,
    image      bytea
);
