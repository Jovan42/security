START TRANSACTION;

CREATE TABLE users
(
    id       bigint(20)   NOT NULL AUTO_INCREMENT,
    username varchar(20)  NOT NULL,
    password varchar(256) NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE scope
(
    id   bigint(20)  NOT NULL AUTO_INCREMENT,
    name varchar(20) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE table user_scope
(
    id       bigint(20) NOT NULL AUTO_INCREMENT,
    user_id  bigint(20) NOT NULL,
    scope_id bigint(20) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_user_scope_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT FK_user_scope_scope_id FOREIGN KEY (scope_id) REFERENCES scope (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE table authorization_code
(
    id      bigint(20)  NOT NULL AUTO_INCREMENT,
    user_id bigint(20)  NOT NULL,
    state  varchar(5) NOT NULL,
    value   varchar(128) NOT NULL,
    expires datetime    NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT FK_authorization_code_user_id FOREIGN KEY (user_id) REFERENCES users (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
COMMIT;
