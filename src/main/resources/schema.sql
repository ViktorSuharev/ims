DROP TABLE IF EXISTS users;
CREATE TABLE users (
    user_id BIGINT PRIMARY KEY auto_increment,
    username VARCHAR(128) UNIQUE,
    password VARCHAR(256),
    role VARCHAR(20),
    enabled BOOL,
);

DROP TABLE IF EXISTS products;
CREATE TABLE products (
    id BIGINT PRIMARY KEY auto_increment,
    name VARCHAR(128),
    brand VARCHAR(128),
    price DECIMAL,
    quantity INT,
);