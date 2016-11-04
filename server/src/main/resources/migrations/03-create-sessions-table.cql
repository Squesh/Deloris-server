CREATE TABLE IF NOT EXISTS deloris.sessions (
    login varchar,
    session varchar,
 PRIMARY KEY (login, session))
 WITH compaction = { 'class': 'LeveledCompactionStrategy' };