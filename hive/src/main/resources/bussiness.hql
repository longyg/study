create table bussiness (
    name string,
    orderdate string,
    cost int
)
row format delimited fields terminated by ',';

load data local inpath '/home/bigdata/data/bussiness.txt' into table bussiness;

select name, count(*) over () from bussiness where substring(orderdate,1,7) = '2017-04' group by name;

