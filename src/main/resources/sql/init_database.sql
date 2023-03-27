CREATE DATABASE testtask;

CREATE TABLE equations (
                           id SERIAL PRIMARY KEY,
                           body VARCHAR(255) NOT NULL
);

CREATE TABLE equation_to_roots (
                                   eq_id integer REFERENCES equations(id),
                                   root varchar(255) NOT NULL,
                                   PRIMARY KEY (eq_id)
);
