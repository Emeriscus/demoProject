CREATE TABLE users (
id BIGINT AUTO_INCREMENT,
email VARCHAR(255),
salt VARCHAR(255) not null,
secure_password VARCHAR(255) not null,
admin_rights BIT(1),
CONSTRAINT pk_users PRIMARY KEY (id)
);