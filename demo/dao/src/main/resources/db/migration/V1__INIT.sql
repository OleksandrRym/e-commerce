-- Таблиця продуктів
CREATE TABLE products (
                          id UUID PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price DECIMAL(19, 2) NOT NULL,
                          count INT NOT NULL DEFAULT 0
);

-- Таблиця кошиків
CREATE TABLE carts (
                       id UUID PRIMARY KEY,
                       processed BOOLEAN DEFAULT FALSE
);

-- Таблиця користувачів
CREATE TABLE users (
                       uuid UUID PRIMARY KEY,
                       username VARCHAR(255),
                       tg_id BIGINT UNIQUE ,
                       cart_id UUID UNIQUE,
                       CONSTRAINT fk_user_cart FOREIGN KEY (cart_id) REFERENCES carts(id)
);

-- Проміжна таблиця для зв'язку Cart <-> Product (Many-to-Many)
CREATE TABLE cart_products (
                               cart_id UUID NOT NULL,
                               product_id UUID NOT NULL,
                               PRIMARY KEY (cart_id, product_id),
                               CONSTRAINT fk_cart FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
                               CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);