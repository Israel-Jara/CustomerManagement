CREATE DATABASE IF NOT EXISTS customermanagement;
USE customermanagement;

CREATE TABLE IF NOT EXISTS roles(
                                    id INT NOT NULL AUTO_INCREMENT,
                                    PRIMARY KEY (id),
    role VARCHAR (100) NOT NULL,
    created_date datetime DEFAULT CURRENT_TIMESTAMP,
    modified_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS users(
                                    id INT NOT NULL AUTO_INCREMENT,
                                    PRIMARY KEY (id),
    name VARCHAR (100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    document VARCHAR(100) NOT NULL,
    phone VARCHAR(100),
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_date datetime DEFAULT CURRENT_TIMESTAMP,
    modified_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT uq_user_document UNIQUE (document),
    CONSTRAINT uq_user_username UNIQUE (username),
    CONSTRAINT uq_user_email UNIQUE (email)

    );

CREATE TABLE IF NOT EXISTS users_roles (
                                           id int NOT NULL AUTO_INCREMENT,
                                           user_id int NOT NULL,
                                           role_id int NOT NULL,
                                           created_date datetime DEFAULT CURRENT_TIMESTAMP,
                                           modified_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                           PRIMARY KEY (id),
    UNIQUE KEY uq_roles_users (role_id, user_id),
    KEY idx_user_id (user_id),
    CONSTRAINT user_role_fk FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT role_user_fk FOREIGN KEY (role_id) REFERENCES roles(id)
    );

insert ignore into users (name, lastname, email, document, username, password) value ( 'Israel', 'Jara', 'israeljara@correo.com', '777777', 'user777777', '$2a$12$6Wwi5vMyEaGmRHWafQTc.OTsLvtHSB2SlBSmwsNg4nh8EmCpd2z/6
');

-- pasword = pass

insert ignore into roles (role) value ('ROLE_ADMIN');
insert ignore into roles (role) value ('ROLE_CUSTOMER');

insert ignore into users_roles(user_id, role_id) value (1, 1);