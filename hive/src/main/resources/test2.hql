add jar /home/bigdata/test/hive-0.0.1-SNAPSHOT.jar;

create temporary function stringext as 'com.yglong.hive.function.StringExt';

show functions;

select stringext(name) from person;