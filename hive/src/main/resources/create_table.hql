CREATE TABLE person (
	id INT,
	name STRING,
	age INT,
	fav ARRAY<STRING>,
	addr MAP<STRING, STRING>
)
COMMENT 'This is the person table'
ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'
COLLECTION ITEMS TERMINATED BY '-'
MAP KEYS TERMINATED BY ':'
STORED AS TEXTFILE;

DESC person;

LOAD DATA INPATH '/user/hive/data/person.txt' OVERWRITE INTO TABLE person;

SELECT * FROM person;

SELECT COUNT(*) FROM person;

DROP TABLE person;



SELECT name, addr['std_addr'] AS std_addr, fav[0] AS fav0, fav[1] AS fav1, fav[2] AS fav2 FROM person WHERE id = 2;

EXPLAIN CREATE TABLE person2 AS SELECT * FROM person;
CREATE TABLE person2 AS SELECT * FROM person;

EXPLAIN SELECT name, addr['std_addr'] AS std_addr, fav[0] AS fav0, fav[1] AS fav1, fav[2] AS fav2 FROM person WHERE id = 2;


SELECT age, COUNT(1) num from person group by age;

set hive.execution.engine=mr; //spark, tez
EXPLAIN SELECT age, COUNT(1) num from person group by age;

SELECT * FROM person;

EXPLAIN SELECT * FROM person;

SELECT UPPER(name), UPPER(fav[0]) FROM person;

EXPLAIN SELECT UPPER(name), UPPER(fav[0]) FROM person;

SELECT COUNT(*) FROM person;

SELECT COUNT(*), MAX(age), MIN(age), AVG(age), SUM(age) FROM person;

SELECT DISTINCT addr['std_addr'] FROM person;

SELECT * FROM person LIMIT 2;


SELECT name, CASE WHEN age < 20 THEN '少年' WHEN age >= 30 THEN '中年' ELSE '青年' END AS age_range FROM person;

SELECT * FROM person WHERE name LIKE '%o%';

SELECT AVG(age) FROM person GROUP BY addr['work_addr'];

SELECT AVG(age) FROM person GROUP BY name HAVING AVG(age) > 20;