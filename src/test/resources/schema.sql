DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS MEMBER;
DROP TABLE  IF EXISTS ROLE;

CREATE TABLE IF NOT EXISTS AUTHOR (
    ID_AUTHOR   VARCHAR(50) NOT NULL,
    FIRST_NAME  VARCHAR(50) NOT NULL,
    LAST_NAME   VARCHAR(50) NOT NULL,
    NICK_NAME   VARCHAR(50) NOT NULL,
    BIRTHDAY    TIMESTAMP   NOT NULL,
    ADRESSE     VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS BOOK (
    ID_BOOK     VARCHAR(50) NOT NULL,
    TITLE       VARCHAR(100) NOT NULL,
    AUTHOR_ID   VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS MEMBER (
    ID_MEMBER       VARCHAR(50) NOT NULL PRIMARY KEY,
    USERNAME        VARCHAR(50) NOT NULL,
    PASSWORD        VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS ROLE (
    ID              VARCHAR(50) NOT NULL,
    ID_MEMBER       VARCHAR(50),
    ROLE_NAME         VARCHAR(50) NOT NULL,
    FOREIGN KEY (ID_MEMBER) REFERENCES MEMBER(ID_MEMBER)
);

commit;