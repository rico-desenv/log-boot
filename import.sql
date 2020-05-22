CREATE SEQUENCE logSeq;

CREATE TABLE log (
    id integer NOT NULL DEFAULT nextval('logSeq') PRIMARY KEY,
    data date NULL,
    ip varchar(15) NULL,
    request varchar(30) NULL,
    status int NULL,
    user_agent varchar(255) NULL
);

--insert into log (DATA,IP,REQUEST,STATUS,USERAGENT) 
--values ('2019-01-01 00:00:11.763','192.168.234.82','GET / HTTP/1.1',200,'swcd (unknown version) CFNetwork/808.2.16 Darwin/15.6.0')
