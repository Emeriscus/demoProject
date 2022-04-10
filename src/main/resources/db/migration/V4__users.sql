CREATE TABLE users (
id BIGINT AUTO_INCREMENT,
username VARCHAR(255),
email VARCHAR(255),
password VARCHAR(255) not null,
admin_rights BIT(1),
CONSTRAINT pk_users PRIMARY KEY (id)
);