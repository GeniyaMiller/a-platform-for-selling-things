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
