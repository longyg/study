create table business (
	name string,
	orderdate string,
	cost int
)
row format delimited fields terminated by ',';

load data local inpath '/home/bigdata/data/business.txt' into table business;

select * from business;

select name, count(*) over () from business where substring(orderdate,1,7) = '2017-04' group by name;

select name, orderdate, cost, sum(cost) over(PARTITION by month(orderdate)) from business;

