create table person2 (
	id INT,
	name STRING,
	age INT,
	fav ARRAY<STRING>,
	addr MAP<STRING, STRING>
)
COMMENT 'patitioned table'
PARTITIONED BY (dt STRING)
ROW FORMAT DELIMITED FIELDS TERMINATED BY '|'
COLLECTION ITEMS TERMINATED BY '-'
MAP KEYS TERMINATED BY ':'
STORED AS TEXTFILE;

LOAD DATA INPATH '/user/hive/data/person.txt' INTO TABLE person2 PARTITION (dt='2020-8-29');

LOAD DATA INPATH '/user/hive/data/person.txt' INTO TABLE person2 PARTITION (dt='2020-8-28');

show partitions person2;

select * from person2 where dt='2020-8-28';

DROP TABLE person2;
