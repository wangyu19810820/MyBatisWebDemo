drop table  USER;

create table USER (
  `id` integer AUTO_INCREMENT,
  `name` varchar(100),
  `usertype` integer,
  `studentnumber` varchar(100),
  `teachernumber` varchar(100),
  `collegeid` integer,
  primary key(id)
);

create table COLLEGE (
  `id` integer AUTO_INCREMENT,
  `name` varchar(100),
  primary key(id)
);

create table SCORE (
  `id` integer AUTO_INCREMENT,
  `name` varchar(100),
  `score` decimal,
  `userid` integer,
  primary key(id)
);
