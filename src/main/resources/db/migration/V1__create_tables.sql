CREATE TABLE tb_customers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    document VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address TEXT
);

CREATE TABLE tb_orders (
    id BIGSERIAL PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    distance_meters INTEGER,
    price NUMERIC(19, 2),
    scheduled_date TIMESTAMP,
    created_at TIMESTAMP DEFAULT NOW(),
    observations TEXT,

    CONSTRAINT fk_order_customer FOREIGN KEY (customer_id) REFERENCES tb_customers(id)
);
