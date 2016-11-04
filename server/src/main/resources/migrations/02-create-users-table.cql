CREATE TABLE IF NOT EXISTS deloris.users (
    login varchar,
    password varchar,
 PRIMARY KEY (login, password))
 WITH compaction = { 'class': 'LeveledCompactionStrategy' };