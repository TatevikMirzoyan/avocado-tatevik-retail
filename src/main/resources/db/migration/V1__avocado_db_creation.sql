CREATE SEQUENCE IF NOT EXISTS address_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS customer_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS order_product_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS order_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS permission_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS product_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS role_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS shop_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS token_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE addresses
(
    id            BIGINT                NOT NULL,
    created       TIMESTAMP WITHOUT TIME ZONE,
    updated       TIMESTAMP WITHOUT TIME ZONE,
    deleted       BOOLEAN DEFAULT FALSE NOT NULL,
    country       VARCHAR(255)          NOT NULL,
    district      VARCHAR(255),
    city          VARCHAR(255),
    address_line1 VARCHAR(255),
    address_line2 VARCHAR(255),
    post_code     VARCHAR(255),
    CONSTRAINT pk_addresses PRIMARY KEY (id)
);

CREATE TABLE customers
(
    id           BIGINT                NOT NULL,
    created      TIMESTAMP WITHOUT TIME ZONE,
    updated      TIMESTAMP WITHOUT TIME ZONE,
    deleted      BOOLEAN DEFAULT FALSE NOT NULL,
    name         VARCHAR(255)          NOT NULL,
    phone_number VARCHAR(255)          NOT NULL,
    address_id   BIGINT,
    bonus        DECIMAL               NOT NULL,
    CONSTRAINT pk_customers PRIMARY KEY (id)
);

CREATE TABLE order_products
(
    id             BIGINT                NOT NULL,
    created        TIMESTAMP WITHOUT TIME ZONE,
    updated        TIMESTAMP WITHOUT TIME ZONE,
    deleted        BOOLEAN DEFAULT FALSE NOT NULL,
    product_id     BIGINT,
    order_id       BIGINT,
    amount         DECIMAL,
    comment        VARCHAR(255),
    original_price DECIMAL,
    discount       DECIMAL,
    total_price    DECIMAL,
    CONSTRAINT pk_order_products PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id              BIGINT                NOT NULL,
    created         TIMESTAMP WITHOUT TIME ZONE,
    updated         TIMESTAMP WITHOUT TIME ZONE,
    deleted         BOOLEAN DEFAULT FALSE NOT NULL,
    shop_id         BIGINT,
    customer_id     BIGINT,
    address_id      BIGINT,
    original_price  DECIMAL,
    total_price     DECIMAL,
    paid_from_bonus DECIMAL,
    order_discount  DECIMAL,
    payment_type    VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

CREATE TABLE permission
(
    id      BIGINT                NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE,
    updated TIMESTAMP WITHOUT TIME ZONE,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    type    VARCHAR(255)          NOT NULL,
    role_id BIGINT,
    CONSTRAINT pk_permission PRIMARY KEY (id)
);

CREATE TABLE products
(
    id          BIGINT                NOT NULL,
    created     TIMESTAMP WITHOUT TIME ZONE,
    updated     TIMESTAMP WITHOUT TIME ZONE,
    deleted     BOOLEAN DEFAULT FALSE NOT NULL,
    name        VARCHAR(255)          NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN,
    visible     BOOLEAN,
    price       DECIMAL,
    unit        VARCHAR(255)          NOT NULL,
    shop_id     BIGINT                NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id      BIGINT                NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE,
    updated TIMESTAMP WITHOUT TIME ZONE,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    role    VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE shops
(
    id      BIGINT                NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE,
    updated TIMESTAMP WITHOUT TIME ZONE,
    deleted BOOLEAN DEFAULT FALSE NOT NULL,
    name    VARCHAR(255)          NOT NULL,
    active  INTEGER,
    visible BOOLEAN,
    CONSTRAINT pk_shops PRIMARY KEY (id)
);

CREATE TABLE token
(
    id         BIGINT                NOT NULL,
    created    TIMESTAMP WITHOUT TIME ZONE,
    updated    TIMESTAMP WITHOUT TIME ZONE,
    deleted    BOOLEAN DEFAULT FALSE NOT NULL,
    user_id    BIGINT                NOT NULL,
    username   VARCHAR(255),
    token      VARCHAR(1000)         NOT NULL,
    token_type VARCHAR(255),
    expires    TIMESTAMP WITHOUT TIME ZONE,
    active     BOOLEAN               NOT NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user_role
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_user_role PRIMARY KEY (role_id, user_id)
);

CREATE TABLE users
(
    id            BIGINT                NOT NULL,
    created       TIMESTAMP WITHOUT TIME ZONE,
    updated       TIMESTAMP WITHOUT TIME ZONE,
    deleted       BOOLEAN DEFAULT FALSE NOT NULL,
    username      VARCHAR(255)          NOT NULL,
    password_hash VARCHAR(255)          NOT NULL,
    email         VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE permission
    ADD CONSTRAINT uc_permission_type UNIQUE (type);

ALTER TABLE products
    ADD CONSTRAINT uc_products_name UNIQUE (name);

ALTER TABLE shops
    ADD CONSTRAINT uc_shops_name UNIQUE (name);

ALTER TABLE token
    ADD CONSTRAINT uc_token_token UNIQUE (token);

ALTER TABLE customers
    ADD CONSTRAINT FK_CUSTOMERS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES addresses (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES addresses (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES customers (id);

ALTER TABLE orders
    ADD CONSTRAINT FK_ORDERS_ON_SHOP FOREIGN KEY (shop_id) REFERENCES shops (id);

ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDER_PRODUCTS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE permission
    ADD CONSTRAINT FK_PERMISSION_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_SHOP FOREIGN KEY (shop_id) REFERENCES shops (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_role_entity FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE user_role
    ADD CONSTRAINT fk_user_role_on_user_entity FOREIGN KEY (user_id) REFERENCES users (id);