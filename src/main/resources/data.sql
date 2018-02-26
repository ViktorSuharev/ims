INSERT INTO users (user_id, username, password, role, enabled) VALUES
	('1', 'admin', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', 'ROLE_ADMIN', true),
	('2', 'user', '$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri', 'ROLE_USER', true);

INSERT INTO products (id, name, brand, price, quantity) VALUES
  (1, 'prod1', 'brand1', 1, 1),
  (2, 'prod2', 'brand2', 2, 10),
  (3, 'prod3', 'brand3', 3, 100);