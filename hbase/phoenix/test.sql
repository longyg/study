CREATE TABLE IF NOT EXISTS t_user (
	id BIGINT NOT NULL,
	name VARCHAR NOT NULL,
	CONSTRAINT my_pk PRIMARY KEY (id, name)
);

UPSERT INTO t_user VALUES (1, 'yglong');
UPSERT INTO t_user VALUES (2, 'colin');
UPSERT INTO t_user VALUES (3, 'molly');

SELECT * FROM t_user;

create table if not exists us_population (
    state CHAR(2)  NOT NULL,
    city VARCHAR NOT NULL,
    population BIGINT
    CONSTRAINT my_pk PRIMARY KEY (state, city));

show tables;

upsert into us_population values ('NY', 'New York', 8143197);
upsert into us_population values ('CA', 'Los Angeles', 3844829);
upsert into us_population values ('IL', 'Chicago', 2842518);
upsert into us_population values ('TX', 'Houston', 2016582);
upsert into us_population values ('PA', 'Philadelphia', 1463281);

SELECT * FROM us_population;